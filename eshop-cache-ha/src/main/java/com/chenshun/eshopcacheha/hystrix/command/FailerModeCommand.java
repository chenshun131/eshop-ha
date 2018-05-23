package com.chenshun.eshopcacheha.hystrix.command;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import lombok.extern.slf4j.Slf4j;

/**
 * User: mew <p />
 * Time: 18/5/23 16:32  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
@Slf4j
public class FailerModeCommand extends HystrixCommand<Boolean> {

    private boolean failure;

    public FailerModeCommand(boolean failure) {
        super(HystrixCommandGroupKey.Factory.asKey("FailerModeGroup"));
        this.failure = failure;
    }

    @Override
    protected Boolean run() throws Exception {
        if (failure) {
            throw new Exception();
        }
        return true;
    }

    @Override
    protected Boolean getFallback() {
        log.debug("异常触发服务降级");
        return false;
    }

}
