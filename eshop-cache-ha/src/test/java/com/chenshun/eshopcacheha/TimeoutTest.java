package com.chenshun.eshopcacheha;

import com.chenshun.eshopcacheha.util.http.HttpClientUtils;
import lombok.extern.slf4j.Slf4j;

/**
 * User: mew <p />
 * Time: 18/5/23 14:02  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
@Slf4j
public class TimeoutTest implements Runnable {

    private int index;

    public TimeoutTest(int index) {
        this.index = index;
    }

    public static void main(String[] args) throws Exception {
        for (int i = 1; i <= 50; i++) {
            new Thread(new TimeoutTest(i)).start();
        }
    }

    @Override
    public void run() {
        String result = HttpClientUtils.sendGetRequest("http://localhost:8081/getProductInfo1?productId=-2");
        log.debug("++++++++++[{}]+{}", index, result);
    }

}
