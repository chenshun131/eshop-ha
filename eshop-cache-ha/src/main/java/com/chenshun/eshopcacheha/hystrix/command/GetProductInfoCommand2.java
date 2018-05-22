package com.chenshun.eshopcacheha.hystrix.command;

import com.alibaba.fastjson.JSONObject;
import com.chenshun.eshopcacheha.model.ProductInfo;
import com.chenshun.eshopcacheha.util.http.HttpClientUtils;
import com.netflix.hystrix.*;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

/**
 * User: mew <p />
 * Time: 18/5/21 16:09  <p />
 * Version: V1.0  <p />
 * Description: 获取商品信息 <p />
 */
@Slf4j
public class GetProductInfoCommand2 extends HystrixCommand<ProductInfo> {

    private Long productId;

    private static final Setter cachedSetter =
            Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("GetProductInfoGroup")) // 设置组名
                    .andCommandKey(HystrixCommandKey.Factory.asKey("GetProductInfo")) // 设置command名称
                    .andThreadPoolPropertiesDefaults(HystrixThreadPoolProperties.Setter()
                            .withCoreSize(10) // 设置线程池大小
                            .withQueueSizeRejectionThreshold(5)) // 队列大小
                    .andCommandPropertiesDefaults(HystrixCommandProperties.Setter()
                            .withExecutionIsolationStrategy(HystrixCommandProperties.ExecutionIsolationStrategy.THREAD) // 隔离策略，默认使用线程池隔离
                            .withExecutionIsolationSemaphoreMaxConcurrentRequests(10) // 信号量大小默认是 10
                            .withCircuitBreakerRequestVolumeThreshold(30)
                            .withCircuitBreakerErrorThresholdPercentage(40)
                            .withCircuitBreakerSleepWindowInMilliseconds(3000));

    public GetProductInfoCommand2(Long productId) {
        super(cachedSetter);
        this.productId = productId;
    }

    @Override
    protected ProductInfo run() throws Exception {
        log.debug("GetProductInfoCommand 线程名 : {}", Thread.currentThread().getName());
        Map<String, String> map = new HashMap<>(1);
        map.put("productId", productId.toString());
        String response = HttpClientUtils.sendPostRequest("http://127.0.0.1:8082/getProductInfo", map);
        return JSONObject.parseObject(response, ProductInfo.class);
    }

    @Override
    protected ProductInfo getFallback() {
        ProductInfo productInfo = new ProductInfo();
        productInfo.setName("降级产品");
        return productInfo;
    }

}
