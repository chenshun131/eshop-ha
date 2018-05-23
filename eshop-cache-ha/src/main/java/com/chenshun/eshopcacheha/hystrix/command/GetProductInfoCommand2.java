package com.chenshun.eshopcacheha.hystrix.command;

import com.alibaba.fastjson.JSONObject;
import com.chenshun.eshopcacheha.cache.local.BrandCache;
import com.chenshun.eshopcacheha.cache.local.LocationCache;
import com.chenshun.eshopcacheha.model.ProductInfo;
import com.chenshun.eshopcacheha.util.http.HttpClientUtils;
import com.netflix.hystrix.*;
import lombok.extern.slf4j.Slf4j;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

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
                            .withMaxQueueSize(12)
                            .withQueueSizeRejectionThreshold(15)) // 队列大小
                    .andCommandPropertiesDefaults(HystrixCommandProperties.Setter()
                            .withExecutionIsolationStrategy(HystrixCommandProperties.ExecutionIsolationStrategy.THREAD) // 隔离策略，默认使用线程池隔离
                            .withExecutionIsolationSemaphoreMaxConcurrentRequests(10) // 信号量大小默认是 10
                            .withExecutionTimeoutEnabled(true) // 是否打开 timeout 机制
                            .withExecutionTimeoutInMilliseconds(1000) // 执行超时时间
                            .withCircuitBreakerEnabled(true)
                            .withCircuitBreakerRequestVolumeThreshold(10) // 最少要有多少个请求时，才触发开启短路
                            .withCircuitBreakerErrorThresholdPercentage(40) // 设置异常请求量的百分比，当异常请求达到这个百分比时，就触发打开短路器
                            .withCircuitBreakerSleepWindowInMilliseconds(10000) // 在短路之后，需要在多长时间内直接reject请求，然后在这段时间之后，再重新导holf-open状态
                            .withExecutionTimeoutInMilliseconds(20000)
                            .withFallbackIsolationSemaphoreMaxConcurrentRequests(30));

    public GetProductInfoCommand2(Long productId) {
        super(cachedSetter);
        this.productId = productId;
    }

    @Override
    protected ProductInfo run() throws Exception {
        log.debug("GetProductInfoCommand 线程名 : {}", Thread.currentThread().getName());
        if (-1 == productId) {
            throw new RuntimeException();
        }
        if (-2 == productId) {
            TimeUnit.SECONDS.sleep(5);
        }
        Map<String, String> map = new HashMap<>(1);
        map.put("productId", productId.toString());
        String response = HttpClientUtils.sendPostRequest("http://127.0.0.1:8082/getProductInfo", map);
        return JSONObject.parseObject(response, ProductInfo.class);
    }

    @Override
    protected ProductInfo getFallback() {
        log.debug("服务降级 -> {}", getExecutionException().toString());

        ProductInfo productInfo = new ProductInfo();
        // 从请求参数中获取到的唯一条数据
        productInfo.setId(productId);
        // 从本地缓存中获取一些数据
        productInfo.setBrandId(BrandCache.getBrandId(productId));
        productInfo.setBrandName(BrandCache.getBrandName(productInfo.getBrandId()));
        productInfo.setCityId(LocationCache.getCityId(productId));
        productInfo.setCityName(LocationCache.getCityName(productInfo.getCityId()));
        // 手动填充一些默认的数据
        productInfo.setColor("默认颜色");
        productInfo.setModifiedTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        productInfo.setName("默认商品");
        productInfo.setPictureList("default.jpg");
        productInfo.setPrice(0.0);
        productInfo.setService("默认售后服务");
        productInfo.setShopId(-1L);
        productInfo.setSize("默认大小");
        productInfo.setSpecification("默认规格");
        return productInfo;
    }

}
