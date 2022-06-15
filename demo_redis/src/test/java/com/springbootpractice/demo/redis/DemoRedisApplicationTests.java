package com.springbootpractice.demo.redis;

import com.springbootpractice.demo.redis.listener.RedisListenerConfig;
import com.springbootpractice.demo.redis.param.OrderVo;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisZSetCommands;
import org.springframework.data.redis.core.DefaultTypedTuple;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SessionCallback;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.util.Assert;
import org.springframework.util.StopWatch;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@SpringBootTest
class DemoRedisApplicationTests {

    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Test
    void stringRedisTest() {
        final ValueOperations valueOperations = redisTemplate.opsForValue();
        valueOperations
                .set("key1", "value1", Duration.ofMinutes(1));

        final Object value = valueOperations.get("key1");

        Assert.isTrue(Objects.equals("value1", value), "set失败");

        final HashOperations hashOperations = redisTemplate.opsForHash();

        hashOperations.put("hash1", "f1", "v1");
        hashOperations.put("hash1", "f2", "v2");

        hashOperations.values("hash1").forEach(System.out::println);
    }


    @Test
    void redisCallbackTest() {
        redisTemplate.execute((RedisCallback) connection -> {
            connection.set("rkey1".getBytes(), "rv1".getBytes());
            connection.set("rkey2".getBytes(), "rv2".getBytes());
            return null;
        });
    }

    @Test
    void sessionCallbackTest() {
        redisTemplate.execute(new SessionCallback() {
            @Override
            public Object execute(RedisOperations operations) throws DataAccessException {
                final ListOperations listOperations = operations.opsForList();
                listOperations.leftPush("sk1", "sv1");
                listOperations.leftPush("sk1", "sv2");
                listOperations.getOperations().expire("sk1", 1, TimeUnit.MINUTES);

                listOperations.range("sk1", 0, 2).forEach(System.out::println);
                return 1;
            }
        });
    }

    @Test
    void stringTest() {
        redisTemplate.opsForValue().set("stringKey1", "value1", 5, TimeUnit.MINUTES);

        //字符串类型的整数，不能进行数字运算；
        redisTemplate.opsForValue().set("stringKey2", "1", 5, TimeUnit.MINUTES);

        //进行数字运算，增加，减少
        redisTemplate.opsForValue().set("stringKey3", 1, 5, TimeUnit.MINUTES);
        redisTemplate.opsForValue().increment("stringKey3", 2);
        redisTemplate.opsForValue().decrement("stringKey3", 1);

        //其它操作方法
        final Long keySize = redisTemplate.opsForValue().size("stringKey1");
        System.out.println(keySize);

        //批量设置
        Map<String, Long> map = new HashMap<>(4);
        map.put("sk1", 1L);
        map.put("sk2", 2L);
        map.put("sk3", 3L);
        map.put("sk4", 4L);
        redisTemplate.opsForValue().multiSet(map);
        redisTemplate.opsForValue().multiSetIfAbsent(map);
        //批量获取
        redisTemplate.opsForValue().multiGet(map.keySet()).forEach(System.out::println);


        //getAndSet
        final Object sk5Value = redisTemplate.opsForValue().getAndSet("sk5", 100);
        System.out.println("sk5Value:" + sk5Value);

        redisTemplate.opsForValue().append("sk5", "hello redis");
        System.out.println("sk5Value2:" + redisTemplate.opsForValue().get("sk5"));

        //按照情况设置，可以省去了之前查询出来之后判断是否存在再操作的代码；
        redisTemplate.opsForValue().setIfAbsent("sk6", 1000, 5, TimeUnit.MINUTES);
        redisTemplate.opsForValue().setIfPresent("sk6", 100, 5, TimeUnit.MINUTES);

        redisTemplate.opsForValue().set("order",
                OrderVo.builder().id(1L).title("iphone11").price(new BigDecimal(5555)).created(LocalDateTime.now()).build(),
                Duration.ofMinutes(5)
        );

        OrderVo orderVo = (OrderVo) redisTemplate.opsForValue().get("order");
        System.out.println("order:" + orderVo);

        Assert.notNull(orderVo, "获取订单对象失败");

    }

    @Test
    void listTest() {

        stringRedisTemplate.opsForList().leftPush("lk1", "lkv1");
        stringRedisTemplate.opsForList().leftPushAll("lk2", "lk2v1", "lk2v2");
        stringRedisTemplate.opsForList().leftPushAll("lk2", Arrays.asList("lk2v3", "lk2v4"));
        stringRedisTemplate.opsForList().leftPushIfPresent("lk3", "lk3v1");

        final List<String> lk2ValuesList = stringRedisTemplate.opsForList().range("lk2", 0, 3);
        System.out.println(lk2ValuesList);
    }

    @Test
    void setTest() {
        stringRedisTemplate.opsForSet().add("sk1", "sk1v1", "sk1v2", "sk1v3");
        stringRedisTemplate.opsForSet().add("sk2", "sk1v1", "sk2v2", "sk2v3");

        final Set<String> sk1 = stringRedisTemplate.opsForSet().members("sk1");
        final Set<String> sk2 = stringRedisTemplate.opsForSet().members("sk2");

        System.out.println("sk1: " + sk1);
        System.out.println("sk2: " + sk2);

        final Set<String> intersect = stringRedisTemplate.opsForSet().intersect("sk1", "sk2");
        System.out.println("交集是：" + intersect);

        final Set<String> union = stringRedisTemplate.opsForSet().union("sk1", "sk2");
        System.out.println("并集：" + union);

        final Set<String> difference = stringRedisTemplate.opsForSet().difference("sk1", "sk2");
        System.out.println("差集：" + difference);

        final Long size = stringRedisTemplate.opsForSet().size("sk1");

        System.out.println("size for sk1 : " + size);

        stringRedisTemplate.delete("sk1");
        stringRedisTemplate.delete("sk2");

    }

    @Test
    void zsetTest() {
        IntStream.rangeClosed(1, 100).forEach(i -> {
            stringRedisTemplate.opsForZSet().add("zsk1", String.valueOf(i), i * 10);
        });
        final Set<ZSetOperations.TypedTuple<String>> typedTupleSet = IntStream.rangeClosed(1, 100).mapToObj(i -> new DefaultTypedTuple<String>(String.valueOf(i), (double) i * 11)).collect(Collectors.toSet());
        stringRedisTemplate.opsForZSet().add("zsk2", typedTupleSet);

        final Set<String> zsk1 = stringRedisTemplate.opsForZSet().rangeByLex("zsk1", RedisZSetCommands.Range.range().gte(20).lte(100));
        System.out.println("范围内的集合：" + zsk1);

    }

    @Test
    void hashTest() {
        stringRedisTemplate.opsForHash().put("hashk1", "k1", "v1");
        stringRedisTemplate.opsForHash().put("hashk1", "k2", "v1");
        stringRedisTemplate.opsForHash().put("hashk1", "k3", "v1");

        stringRedisTemplate.opsForHash().putIfAbsent("hashk1", "k4", "new V1");

        final List<Object> multiGet = stringRedisTemplate.opsForHash().multiGet("hashk1", Arrays.asList("k1", "k2"));
        System.out.println("一次获取多个：" + multiGet);

    }


    @Test
    void transactionTest() {
        final String ttk1 = "ttk1";
        stringRedisTemplate.opsForValue().set(ttk1, "ttk1v1");
        final List list = stringRedisTemplate.execute(new SessionCallback<List>() {
            @Override
            public List execute(RedisOperations operations) throws DataAccessException {
                System.out.println("监听" + ttk1);
                //如果ttk1的值发生了变化，重新set一样的值也是发生了变化，则回滚事务，否则正常执行
                operations.watch(ttk1);

                //开启事务
                System.out.println("开启事务");
                operations.multi();
                operations.opsForList().leftPushAll("xxx_lk1", "v1", "v2", "v3");
                final List xxx_lk1 = operations.opsForList().range("xxx_lk1", 0, 2);
                System.out.println(xxx_lk1);

                operations.opsForSet().add("xxx_sk1", "v1", "v2", "v3");
                final Set xxx_sk1 = operations.opsForSet().members("xxx_sk1");
                System.out.println(xxx_sk1);
                //提交事务
                final List list = operations.exec();
                System.out.println("提交事务");
                return list;
            }
        });

        System.out.println("执行结果：" + list);


    }


    @Test
    void pipelineTest() {

        StopWatch stopWatch = new StopWatch("pipelineTest");
        stopWatch.start();
        final List<Object> objectList = stringRedisTemplate.executePipelined(new SessionCallback<Object>() {
            @Override
            public Object execute(RedisOperations operations) throws DataAccessException {

                for (int i = 1; i <= 10000; i++) {
                    operations.opsForValue().set("pk" + i, "pkv" + i, 5, TimeUnit.MINUTES);
                }

                return null;
            }
        });

        stopWatch.stop();
        System.out.println(stopWatch.prettyPrint());
    }


    @Test
    void messageQueueTest() {
        redisTemplate.convertAndSend(RedisListenerConfig.MY_CHANNEL,"hello world");
        redisTemplate.convertAndSend(RedisListenerConfig.MY_CHANNEL,"hello redis");
        redisTemplate.convertAndSend(RedisListenerConfig.MY_CHANNEL,"hello redis queue");
    }

    @AfterAll
    static void afterAll() {
        try {
            TimeUnit.MINUTES.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
