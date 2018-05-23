package com.chenshun.eshopcacheha;

import com.chenshun.eshopcacheha.util.http.HttpClientUtils;

/**
 * User: mew <p />
 * Time: 18/5/23 15:45  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
public class RequestCollapserTest {

    public static void main(String[] args) {
        HttpClientUtils.sendGetRequest("http://localhost:8081/getProductInfos1?productIds=1,2,2,3,3,4,5,6");
    }

}
