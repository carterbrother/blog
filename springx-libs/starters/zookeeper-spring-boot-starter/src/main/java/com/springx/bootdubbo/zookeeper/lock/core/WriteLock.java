package com.springx.bootdubbo.zookeeper.lock.core;
/*
 * 说明：一个实现独占的排它锁的协议或者选举一个leader;
 * 你调用 lock()来获取锁的操作，你可能拿到锁或者稍后拿到。
 * 你可以注册一个回调器，这样当你拿到锁的时候会被调用，
 * 否则你必须询问是否持有锁通过调用  isOwner方法；
 * @author  carter
 * 创建时间： 2019年07月29日 13:38
 */

import lombok.Getter;
import lombok.Setter;
import lombok.Synchronized;
import lombok.extern.slf4j.Slf4j;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.ACL;
import org.apache.zookeeper.data.Stat;

import java.util.List;
import java.util.Objects;
import java.util.SortedSet;
import java.util.TreeSet;

import static org.apache.zookeeper.CreateMode.EPHEMERAL_SEQUENTIAL;

@Slf4j
public class WriteLock extends ProtocolSupport {

    private final String dir;
    @Getter
    private String id;
    private ZNodeName idName;
    @Getter
    private String ownerId;
    private String lastChildId;

    private byte[] data = {0x12, 0x34};
    @Getter
    @Setter
    private ILockListener lockListener;
    private ILockZooKeeperOperation zop;

    public WriteLock(ZooKeeper zooKeeper, String dir, List<ACL> aclList, ILockListener lockListener) {
        this(zooKeeper, dir, aclList);
        this.lockListener = lockListener;
    }

    public WriteLock(ZooKeeper zooKeeper, String dir, List<ACL> aclList) {
        super(zooKeeper);
        this.dir = dir;
        if (Objects.nonNull(aclList)) {
            setAclList(aclList);
        }
        this.zop = new InnerZop();
    }

    private boolean isOwner() {
        return false;
    }

    @Synchronized
    public void unLock() throws RuntimeException {
        if (isClosed() || Objects.isNull(id)) {
            return;
        }

        try {
            new ILockZooKeeperOperation() {
                /**
                 * 执行操作，可能会被调用多次如果操作的时候zk链接关闭
                 *
                 * @return 操作的结果或者null
                 * @throws KeeperException
                 * @throws InterruptedException
                 */
                @Override
                public boolean execute() throws KeeperException, InterruptedException {
                    zooKeeper.delete(id, -1);
                    return true;
                }
            }.execute();
        } catch (InterruptedException e1) {

        } catch (KeeperException.NoNodeException e2) {

        } catch (KeeperException e3) {

        } finally {
            if (Objects.nonNull(lockListener)) {
                lockListener.lockReleased();
            }
            id = null;
        }
    }

    @Synchronized
    public boolean lock() throws KeeperException, InterruptedException {
        if (isClosed()) {
            return false;
        }
        ensurePathExists(dir);
        return (Boolean) retryOperation(zop);
    }


    private class InnerZop implements ILockZooKeeperOperation {

        /**
         * find if we have been created earler if not create our node
         *
         * @param prefix    the prefix node
         * @param zookeeper teh zookeeper client
         * @param dir       the dir paretn
         * @throws KeeperException
         * @throws InterruptedException
         */
        private void findPrefixInChildren(String prefix, ZooKeeper zookeeper, String dir)
                throws KeeperException, InterruptedException {
            List<String> names = zookeeper.getChildren(dir, false);
            for (String name : names) {
                if (name.startsWith(prefix)) {
                    id = name;
                    if (log.isDebugEnabled()) {
                        log.debug("Found id created last time: " + id);
                    }
                    break;
                }
            }
            if (id == null) {
                id = zookeeper.create(dir + "/" + prefix, data, getAclList(), EPHEMERAL_SEQUENTIAL);

                if (log.isDebugEnabled()) {
                    log.debug("Created id: " + id);
                }
            }

        }

        /**
         * the command that is run and retried for actually
         * obtaining the lock
         *
         * @return if the command was successful or not
         */
        @Override
        public boolean execute() throws KeeperException, InterruptedException {
            do {
                if (id == null) {
                    long sessionId = zooKeeper.getSessionId();
                    String prefix = "x-" + sessionId + "-";
                    // lets try look up the current ID if we failed
                    // in the middle of creating the znode
                    findPrefixInChildren(prefix, zooKeeper, dir);
                    idName = new ZNodeName(id);
                } else {
                    List<String> names = zooKeeper.getChildren(dir, false);
                    if (names.isEmpty()) {
                        log.warn("No children in: " + dir + " when we've just " +
                                "created one! Lets recreate it...");
                        // lets force the recreation of the id
                        id = null;
                    } else {
                        // lets sort them explicitly (though they do seem to come back in order ususally :)
                        SortedSet<ZNodeName> sortedNames = new TreeSet<ZNodeName>();
                        for (String name : names) {
                            sortedNames.add(new ZNodeName(dir + "/" + name));
                        }
                        ownerId = sortedNames.first().getName();
                        SortedSet<ZNodeName> lessThanMe = sortedNames.headSet(idName);
                        if (!lessThanMe.isEmpty()) {
                            ZNodeName lastChildName = lessThanMe.last();
                            lastChildId = lastChildName.getName();
                            if (log.isDebugEnabled()) {
                                log.debug("watching less than me node: " + lastChildId);
                            }
                            Stat stat = zooKeeper.exists(lastChildId, new Watcher() {
                                @Override
                                public void process(WatchedEvent event) {
                                    // lets either become the leader or watch the new/updated node
                                    log.debug("Watcher fired on path: " + event.getPath() + " state: " +
                                            event.getState() + " type " + event.getType());
                                    try {
                                        lock();
                                    } catch (Exception e) {
                                        log.warn("Failed to acquire lock: " + e, e);
                                    }
                                }
                            });
                            if (stat != null) {
                                return Boolean.FALSE;
                            } else {
                                log.warn("Could not find the" +
                                        " stats for less than me: " + lastChildName.getName());
                            }
                        } else {
                            if (isOwner()) {
                                if (lockListener != null) {
                                    lockListener.lockAcquired();
                                }
                                return Boolean.TRUE;
                            }
                        }
                    }
                }
            } while (id == null);
            return Boolean.FALSE;
        }
    }


}
