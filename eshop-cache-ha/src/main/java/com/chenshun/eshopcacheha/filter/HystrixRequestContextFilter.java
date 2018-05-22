package com.chenshun.eshopcacheha.filter;

import com.netflix.hystrix.strategy.concurrency.HystrixRequestContext;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * User: mew <p />
 * Time: 18/5/22 15:46  <p />
 * Version: V1.0  <p />
 * Description: Hystrix请求上下文过滤器 <p />
 */
@WebFilter(filterName = "hystrixRequestContextFilter", urlPatterns = "/getProductInfo/**")
public class HystrixRequestContextFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        // 在一个请求执行之前，都必须先初始化一个 request context
        HystrixRequestContext context = HystrixRequestContext.initializeContext();
        try {
            chain.doFilter(request, response);
        } finally {
            // 然后在请求结束之后，需要关闭 request context
            context.shutdown();
        }
    }

    @Override
    public void destroy() {
    }

}
