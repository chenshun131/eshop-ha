package com.chenshun.eshopcacheha.hystrix.command;

import com.chenshun.eshopcacheha.cache.local.LocationCache;
import com.netflix.hystrix.*;

/**
 * User: mew <p />
 * Time: 18/5/21 17:18  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
public class GetCityNameCommand extends HystrixCommand<String> {

    private Long cityId;

    protected GetCityNameCommand(Long cityId) {
        super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("GetCityNameGroup"))
                .andCommandKey(HystrixCommandKey.Factory.asKey("GetCityNameCommand"))
                .andThreadPoolKey(HystrixThreadPoolKey.Factory.asKey("GetCityNamePool"))
                .andCommandPropertiesDefaults(HystrixCommandProperties.Setter()
                        .withExecutionIsolationStrategy(HystrixCommandProperties.ExecutionIsolationStrategy.SEMAPHORE)
                        .withExecutionIsolationSemaphoreMaxConcurrentRequests(15)));
        this.cityId = cityId;
    }

    @Override
    protected String run() throws Exception {
        return LocationCache.getCityName(cityId);
    }

}
