package com.chenshun.eshopcacheha.hystrix.command;

import com.chenshun.eshopcacheha.cache.local.BrandCache;
import com.netflix.hystrix.*;
import lombok.extern.slf4j.Slf4j;

/**
 * User: mew <p />
 * Time: 18/5/22 17:07  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
@Slf4j
public class GetBrandNameCommand extends HystrixCommand<String> {

    private Long brandId;

    public GetBrandNameCommand(Long brandId) {
        super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("BrandInfoService"))
                .andCommandKey(HystrixCommandKey.Factory.asKey("GetBrandNameCommand"))
                .andThreadPoolKey(HystrixThreadPoolKey.Factory.asKey("GetBrandInfoPool"))
                .andThreadPoolPropertiesDefaults(HystrixThreadPoolProperties.Setter()
                        .withCoreSize(15)
                        .withQueueSizeRejectionThreshold(10))
                .andCommandPropertiesDefaults(HystrixCommandProperties.Setter()
                        .withExecutionIsolationStrategy(HystrixCommandProperties.ExecutionIsolationStrategy.SEMAPHORE)
                        .withFallbackIsolationSemaphoreMaxConcurrentRequests(15)));
        this.brandId = brandId;
    }

    @Override
    protected String run() throws Exception {
        // 调用失败
        throw new Exception();
    }

    @Override
    protected String getFallback() {
        log.debug("从本地缓存获取过期的品牌数据，brandId = {}", brandId);
        return BrandCache.getBrandName(brandId);
    }

}
