package com.chenshun.eshopcacheha.hystrix.command;

import com.chenshun.eshopcacheha.degrade.IsDegrade;
import com.chenshun.eshopcacheha.model.ProductInfo;
import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;
import com.netflix.hystrix.HystrixCommandProperties;

/**
 * User: mew <p />
 * Time: 18/5/23 17:25  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
public class GetProductInfoFacadeCommand extends HystrixCommand<ProductInfo> {

    private Long productId;

    public GetProductInfoFacadeCommand(Long productId) {
        super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("ProductInfoService"))
                .andCommandKey(HystrixCommandKey.Factory.asKey("GetProductInfoFacadeCommand"))
                .andCommandPropertiesDefaults(HystrixCommandProperties.Setter()
                        .withExecutionIsolationStrategy(HystrixCommandProperties.ExecutionIsolationStrategy.SEMAPHORE)
                        .withExecutionIsolationSemaphoreMaxConcurrentRequests(15)));
        this.productId = productId;
    }

    @Override
    protected ProductInfo run() throws Exception {
        // 根据标志位采用不同的处理方式，可以修改标志位直接进行手动降级调用 降级 command
        if (IsDegrade.isDegrade()) {
            return new GetProductInfoFromMySQLCommand(productId).execute();
        } else {
            return new GetProductInfoCommand2(productId).execute();
        }
    }

    @Override
    protected ProductInfo getFallback() {
        return new ProductInfo();
    }

}
