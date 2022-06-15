package com.springbootpractice.demomiaosha.web;

import com.springbootpractice.demomiaosha.bean.PurchaseRequestParam;
import com.springbootpractice.demomiaosha.bean.ResponseBean;
import com.springbootpractice.demomiaosha.dao.model.ProductEntity;
import com.springbootpractice.demomiaosha.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author <a href="mailto:505847426@qq.com">carterbrother</a>
 * @description Rest接口
 * @date 2019年06月25日 17:25
 * @Copyright (c) carterbrother
 */
@RestController
public class PurchaseController {

    @Autowired
    private PurchaseService purchaseService;

    @PostMapping(path = "/purchase")
    public ResponseBean purchase(@RequestBody PurchaseRequestParam param){

        AtomicBoolean success = new AtomicBoolean(false);

        switch (param.getMethodType()){
            case "PessimisticLock":{
                success.set(purchaseService.purchaseByPessimisticLock(param.getUserId(),param.getProductId(),param.getQty()));
                break;
            }
            case "OptimisticLock":{
                AtomicInteger i=new AtomicInteger(1);
                while (i.getAndIncrement()<=3 && Objects.equals(success.get(),false)){
                    try {
                        success.set(purchaseService.purchaseByOptimisticLock(param.getUserId(), param.getProductId(), param.getQty()));
                    }catch (Exception ex){
                        success.set(false);
                    }
                }
                break;
            }
            default:{
                success.set(purchaseService.purchaseNormal(param.getUserId(),param.getProductId(),param.getQty()));
                break;
            }


        }

        ResponseBean responseBean = new ResponseBean();
        responseBean.setSuccess(success.get());
        responseBean.setMessage(success.get()?"秒杀成功":"秒杀失败");

        responseBean.setData(System.currentTimeMillis());

        return responseBean;

    }

    @GetMapping(path = "/product/{productId}")
    public ProductEntity getProductById(@PathVariable("productId") Long productId){
        return  purchaseService.getProductById(productId);
    }



}
