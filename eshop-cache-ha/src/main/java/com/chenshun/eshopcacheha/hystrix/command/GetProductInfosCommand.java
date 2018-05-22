package com.chenshun.eshopcacheha.hystrix.command;

import com.alibaba.fastjson.JSONObject;
import com.chenshun.eshopcacheha.model.ProductInfo;
import com.chenshun.eshopcacheha.util.http.HttpClientUtils;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixObservableCommand;
import rx.Observable;
import rx.Subscriber;
import rx.schedulers.Schedulers;

import java.util.HashMap;
import java.util.Map;

/**
 * User: mew <p />
 * Time: 18/5/21 16:14  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
public class GetProductInfosCommand extends HystrixObservableCommand<ProductInfo> {

    private String[] productIds;

    public GetProductInfosCommand(String[] productIds) {
        super(HystrixCommandGroupKey.Factory.asKey("GetProductInfoGroup"));
        this.productIds = productIds;
    }

    @Override
    protected Observable<ProductInfo> construct() {
        return Observable.unsafeCreate(new Observable.OnSubscribe<ProductInfo>() {
            @Override
            public void call(Subscriber<? super ProductInfo> subscriber) {
                for (String productId : productIds) {
                    Map<String, String> map = new HashMap<>(1);
                    map.put("productId", productId);
                    String response = HttpClientUtils.sendPostRequest("http://127.0.0.1:8082/getProductInfo", map);
                    subscriber.onNext(JSONObject.parseObject(response, ProductInfo.class));
                }
                subscriber.onCompleted();
            }
        }).subscribeOn(Schedulers.io());
    }

}
