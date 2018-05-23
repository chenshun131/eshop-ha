package com.chenshun.eshopcacheha;

import com.chenshun.eshopcacheha.util.http.HttpClientUtils;
import lombok.extern.slf4j.Slf4j;

/**
 * User: mew <p />
 * Time: 18/5/23 11:26  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
@Slf4j
public class RejectTest {

    public static void main(String[] args) throws Exception {
        for (int i = 0; i < 30; i++) {
            new TestThread(i).start();
        }
    }

    private static class TestThread extends Thread {

        private int index;

        public TestThread(int index) {
            this.index = index;
        }

        @Override
        public void run() {
            String response = HttpClientUtils.sendGetRequest("http://localhost:8081/getProductInfo1?productId=-2");
            log.debug("第" + (index + 1) + "次请求，结果为：" + response);
        }

    }

}
