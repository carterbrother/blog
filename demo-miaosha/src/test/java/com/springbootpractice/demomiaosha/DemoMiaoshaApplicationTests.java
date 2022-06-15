package com.springbootpractice.demomiaosha;

import com.springbootpractice.demomiaosha.bean.PurchaseRequestParam;
import com.springbootpractice.demomiaosha.bean.ResponseBean;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.stream.LongStream;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class DemoMiaoshaApplicationTests {

    @Autowired
    private TestRestTemplate testRestTemplate;


    /**
     *
     insert into product(id, product_name, store_qty, product_price, version, memo)
     VALUES (1,'乐心5SE手环',50000,99.0,0,'乐心5SE秒杀活动');
     */
    @Test
    public void testNormalBuy() {
        LongStream.rangeClosed(1,20000).parallel().forEach(item->{

            PurchaseRequestParam purchaseRequestParam = new PurchaseRequestParam();
            purchaseRequestParam.setProductId(1L);
            final Long qty = item%2;
            purchaseRequestParam.setQty(qty.intValue()+1);
            purchaseRequestParam.setUserId(item);

            final ResponseBean responseBean = testRestTemplate.postForObject("/purchase", purchaseRequestParam, ResponseBean.class);

            log.info("{} --->  秒杀参数：{} ,  秒杀结果：{} " , item, purchaseRequestParam.toString(), responseBean.toString());

        });
    }

    /**
     *
     insert into product(id, product_name, store_qty, product_price, version, memo)
     VALUES (1,'乐心5SE手环',50000,99.0,0,'乐心5SE秒杀活动');
     */
    @Test
    public void testPurchaseByPessimisticLock() {
        LongStream.rangeClosed(1,50000).parallel().forEach(item->{

            PurchaseRequestParam purchaseRequestParam = new PurchaseRequestParam();
            purchaseRequestParam.setProductId(2L);
            final Long qty = item%2;
            purchaseRequestParam.setQty(qty.intValue()+1);
            purchaseRequestParam.setUserId(item);
            purchaseRequestParam.setMethodType("PessimisticLock");

            final ResponseBean responseBean = testRestTemplate.postForObject("/purchase", purchaseRequestParam, ResponseBean.class);

            log.info("{} --->  秒杀参数：{} ,  秒杀结果：{} " , item, purchaseRequestParam.toString(), responseBean.toString());

        });
    }


    /**
     *
     insert into product(id, product_name, store_qty, product_price, version, memo)
     VALUES (1,'乐心5SE手环',50000,99.0,0,'乐心5SE秒杀活动');
     */
    @Test
    public void testPurchaseByOptimisticLock() {
        LongStream.rangeClosed(1,30000).parallel().forEach(item->{

            PurchaseRequestParam purchaseRequestParam = new PurchaseRequestParam();
            purchaseRequestParam.setProductId(3L);
            purchaseRequestParam.setQty(1);
            purchaseRequestParam.setUserId(item*3);
            purchaseRequestParam.setMethodType("OptimisticLock");

            final ResponseBean responseBean = testRestTemplate.postForObject("/purchase", purchaseRequestParam, ResponseBean.class);

            log.info("{} --->  秒杀参数：{} ,  秒杀结果：{} " , item, purchaseRequestParam.toString(), responseBean.toString());

        });
    }


}
