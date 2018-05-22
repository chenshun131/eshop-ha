package com.chenshun.eshopproductha.controller;

import com.chenshun.eshopproductha.model.ProductInfo;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * User: mew <p />
 * Time: 18/5/21 15:27  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
@RestController
public class ProductController {

    @RequestMapping("getProductInfo")
    public ProductInfo getProductInfo(Long productId) {
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
