package com.chenshun.eshopcacheha.controller;

import com.chenshun.eshopcacheha.hystrix.command.*;
import com.chenshun.eshopcacheha.model.ProductInfo;
import com.chenshun.eshopcacheha.service.CacheService;
import com.chenshun.eshopcacheha.util.http.HttpClientUtils;
import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixObservableCommand;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import rx.Observable;
import rx.Observer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * User: mew <p />
 * Time: 18/5/21 15:15  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
@Slf4j
@RestController
public class CacheController {

    @Autowired
    private CacheService cacheService;

    @RequestMapping(value = "change/product", method = RequestMethod.POST)
    public String changeProduct(Long productId) {
        if (productId == null) {
            return "fail";
        }
        Map<String, String> map = new HashMap<>(1);
        map.put("productId", productId.toString());
        String response = HttpClientUtils.sendPostRequest("http://127.0.0.1:8082/getProductInfo", map);
        log.info(response);

        return "success";
    }

    @RequestMapping("getProductInfo")
    public String getProductInfo(Long productId) {
        if (productId == null) {
            return "fail";
        }
        HystrixCommand<ProductInfo> productInfoHystrixCommand = new GetProductInfoCommand(productId);
        ProductInfo productInfo = productInfoHystrixCommand.execute();
        log.info(productInfo.toString());
        log.info(productInfoHystrixCommand.isResponseFromCache() ? "从缓存获取数据" : "不是从缓存获取数据");
        log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>><<<<<<<<<<<<<<<<<<<<<<<");

        flushCache(productId);

        productInfoHystrixCommand = new GetProductInfoCommand(productId);
        productInfo = productInfoHystrixCommand.execute();
        log.info(productInfo.toString());
        log.info(productInfoHystrixCommand.isResponseFromCache() ? "从缓存获取数据" : "不是从缓存获取数据");
        return "success";
    }

    @RequestMapping("flushCache")
    public String flushCache(Long productId) {
        GetProductInfoCommand.flushCache(productId);
        return "success";
    }

    @RequestMapping("getProductInfo1")
    public ProductInfo getProductInfo1(Long productId) {
        HystrixCommand<ProductInfo> productInfoHystrixCommand = new GetProductInfoCommand2(productId);
        ProductInfo productInfo = productInfoHystrixCommand.execute();
        if (productInfo != null) {
            log.info(productInfo.toString());
        }
        return productInfo;
    }

    @RequestMapping("getProductInfo2")
    public String getProductInfo2(Long productId) {
        if (productId == null) {
            return "fail";
        }
        ProductInfo productInfo = cacheService.getProductInfo(productId);
        log.info(productInfo.toString());
        return "success";
    }

    @RequestMapping("getProductInfos")
    public String getProductInfos(String productIds) {
        if (productIds == null) {
            return "fail";
        }
        HystrixObservableCommand<ProductInfo> getProductInfosCommand = new GetProductInfosCommand(productIds.split(","));
        Observable<ProductInfo> observable = getProductInfosCommand.observe();
        observable.subscribe(new Observer<ProductInfo>() {
            @Override
            public void onCompleted() {
                log.info("获取完了所有的商品数据");
            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
            }

            @Override
            public void onNext(ProductInfo productInfo) {
                log.info(productInfo.toString());
            }
        });
        return "success";
    }

    @RequestMapping("getProductInfos1")
    public String getProductInfos1(String productIds) {
        if (productIds == null) {
            return "fail";
        }
        List<Future<ProductInfo>> futures = new ArrayList<>();
        for (String productId : productIds.split(",")) {
            GetProductInfosCollapser getProductInfosCollapser = new GetProductInfosCollapser(Long.valueOf(productId));
            futures.add(getProductInfosCollapser.queue());
        }
        for (Future<ProductInfo> future : futures) {
            try {
                log.debug(future.get().toString());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
        return "success";
    }

    @RequestMapping("getBrandName")
    public String getBrandName(Long brandId) {
        GetBrandNameCommand command = new GetBrandNameCommand(brandId);
        return command.execute();
    }

}
