package com.springbootpractice.demo.demo_jdbc_tx;

import com.springbootpractice.demo.demo_jdbc_tx.biz.NamedJdbcBiz;
import com.springbootpractice.demo.demo_jdbc_tx.biz.SimpleJdbcInsertBiz;
import com.springbootpractice.demo.demo_jdbc_tx.biz.TxJdbcBiz;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.TransactionManager;
import org.springframework.util.Assert;

import java.util.Map;

@Slf4j
@SpringBootTest
class DemoJdbcTxApplicationTests {

    @Autowired
    private NamedJdbcBiz namedJdbcBiz;

    @Autowired
    private SimpleJdbcInsertBiz simpleJdbcInsertBiz;

    @Autowired
    private TxJdbcBiz txJdbcBiz;

    @Autowired
    private TransactionManager transactionManager;

    @Test
    void testGetByNameParam() {
        final Map map = namedJdbcBiz.getById(1);
        log.info("数据：{}",map);
    }

    @Test
    void testSimpleInsert() {
        final long id = simpleJdbcInsertBiz.insertUserLogin("lixile", "abcabc", 2, "my daughter");
        Assert.isTrue(id>0,"插入失败");
        log.info("插入之后生成的id：{}",id);
    }

    @Test
    void testInsertUserTest() {

        final int result = txJdbcBiz.insertUserLogin("monika.smith", "xxxx");

        Assert.isTrue(result > 0, "插入失败");


    }

    @Test
    void insertUserLoginTransactionTest() {
        final int result = txJdbcBiz.insertUserLoginTransaction("stefan.li", "hello transaction");
        Assert.isTrue(result > 0, "插入失败");

    }

    @Test
    void transactionManagerTest() {
        System.out.println(transactionManager.getClass().getName());
    }
}
