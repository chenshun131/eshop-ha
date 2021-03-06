package com.chenshun.eshopcacheha;

import com.chenshun.eshopcacheha.util.http.HttpClientUtils;
import lombok.extern.slf4j.Slf4j;

/**
 * User: mew <p />
 * Time: 18/5/22 18:02  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
@Slf4j
public class CircuitBreakerTest {

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 15; i++) {
            String response = HttpClientUtils.sendGetRequest("http://localhost:8081/getProductInfo1?productId=1");
            log.debug("+++++++a+ 第" + (i + 1) + "次请求，结果为：" + response);
        }
        for (int i = 0; i < 25; i++) {
            String response = HttpClientUtils.sendGetRequest("http://localhost:8081/getProductInfo1?productId=-1");
            log.debug("+++++++b+ 第" + (i + 1) + "次请求，结果为：" + response);
        }
        Thread.sleep(5000);
        // 等待了5s后，时间窗口统计了，发现异常比例太多，就短路了
        for (int i = 0; i < 10; i++) {
            String response = HttpClientUtils.sendGetRequest("http://localhost:8081/getProductInfo1?productId=-1");
            log.debug("+++++++c+ 第" + (i + 1) + "次请求，结果为：" + response);
        }
        // 统计单位，有一个时间窗口的，我们必须要等到那个时间窗口过了以后，才会说，hystrix看一下最近的这个时间窗口
        // 比如说，最近的10秒内，有多少条数据，其中异常的数据有没有到一定的比例
        // 如果到了一定的比例，那么才会去短路
        log.debug("尝试等待3秒钟。。。。。。");
        Thread.sleep(3000);
        for (int i = 0; i < 10; i++) {
            String response = HttpClientUtils.sendGetRequest("http://localhost:8081/getProductInfo1?productId=1");
            log.debug("+++++++d+ 第" + (i + 1) + "次请求，结果为：" + response);
        }
    }

}
