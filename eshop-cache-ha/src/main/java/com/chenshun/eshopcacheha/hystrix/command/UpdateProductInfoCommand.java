package com.chenshun.eshopcacheha.hystrix.command;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;

/**
 * User: mew <p />
 * Time: 18/5/23 14:51  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
public class UpdateProductInfoCommand extends HystrixCommand<Boolean> {

    private Long productId;

    protected UpdateProductInfoCommand(Long productId) {
        super(HystrixCommandGroupKey.Factory.asKey("UpdateProductInfoGroup"));
        this.productId = productId;
    }

    @Override
    protected Boolean run() throws Exception {
        // 执行一次商品信息的更新
        GetProductInfoCommand.flushCache(productId);
        return true;
    }

}
