package com.springx.bootdubbo.zookeeper.lock.core;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.Comparator;
import java.util.Objects;

/*
 * 说明：节点对象
 * @author  carter
 * 创建时间： 2019年07月29日 11:29
 */

@Slf4j
class ZNodeName implements Comparable<ZNodeName> {
    @Getter
    private final String name;
    @Getter
    private String prefix;
    @Getter
    private int sequence = -1;

    public ZNodeName(String name) {

        this.name = Objects.requireNonNull(name, "id cannot be null");
        this.prefix = name;
        int idx = name.lastIndexOf('-');
        if (idx < 0) {
            return;
        }
        this.prefix = name.substring(0, idx);
        try {
            this.sequence = Integer.parseInt(name.substring(idx + 1));
        } catch (NumberFormatException e1) {
            log.info("number formate exception for {}", idx, e1);
        } catch (ArrayIndexOutOfBoundsException e2) {
            log.info("array out of bounds exception for {}", idx, e2);
        }
    }

    @Override
    public String toString() {
        return Objects.toString(name);
    }

    @Override
    public boolean equals(Object that) {
        if (Objects.equals(this, that)) {
            return true;
        }

        if (Objects.isNull(that) || getClass() != that.getClass()) {
            return false;
        }
        ZNodeName zNodeName = (ZNodeName) that;
        return Objects.equals(this.name, zNodeName.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode() + 37;
    }

    public int getZnodeName() {
        return sequence;
    }

    @Override
    public int compareTo(ZNodeName that) {
        return Comparator.comparing(ZNodeName::getPrefix)
                .thenComparing(ZNodeName::getSequence)
                .thenComparing(ZNodeName::getName)
                .compare(this, that);

    }
}
