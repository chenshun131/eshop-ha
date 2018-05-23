package com.chenshun.eshopcacheha.hystrix.command;

import com.chenshun.eshopcacheha.model.ProductInfo;
import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;

/**
 * User: mew <p />
 * Time: 18/5/23 17:33  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
public class GetProductInfoFromMySQLCommand extends HystrixCommand<ProductInfo> {

    private Long productId;

    public GetProductInfoFromMySQLCommand(Long productId) {
        super(HystrixCommandGroupKey.Factory.asKey("ProductInfoService"));
        this.productId = productId;
    }

    @Override
    protected ProductInfo run() throws Exception {
        ProductInfo productInfo = new ProductInfo();
        productInfo.setId(productId);
        productInfo.setName("iphone7手机");
        productInfo.setPrice(5599d);
        productInfo.setPictureList("a.jpg,b.jpg");
        productInfo.setSpecification("iphone7的规格");
        productInfo.setService("iphone7的售后服务");
        productInfo.setColor("红色,白色,黑色");
        productInfo.setSize("5.5");
        productInfo.setShopId(1L);
        return productInfo;
    }

}
