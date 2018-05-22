package com.chenshun.eshopcacheha.service;

import com.chenshun.eshopcacheha.model.ProductInfo;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * User: mew <p />
 * Time: 18/5/22 10:34  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
@Slf4j
@Service
public class CacheService {

    @Autowired
    private RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "getProductInfoFallback", groupKey = "GetProductInfoGroup")
    public ProductInfo getProductInfo(Long productId) {
        log.info("GetProductInfoCommand 线程名 : {}", Thread.currentThread().getName());
        Map<String, Long> map = new HashMap<>(1);
        map.put("productId", productId);
        ProductInfo productInfo = new ProductInfo();
        ResponseEntity<ProductInfo> responseEntity =
                restTemplate.postForEntity("http://127.0.0.1:8082/getProductInfo", productInfo, ProductInfo.class, map);
        return responseEntity.getBody();
    }

    public ProductInfo getProductInfoFallback() {
        return new ProductInfo();
    }

}
