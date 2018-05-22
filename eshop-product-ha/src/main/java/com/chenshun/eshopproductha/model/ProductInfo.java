package com.chenshun.eshopproductha.model;

import lombok.Data;

/**
 * User: mew <p />
 * Time: 18/5/10 10:33  <p />
 * Version: V1.0  <p />
 * Description: 商品信息 <p />
 */
@Data
public class ProductInfo {

    private Long id;

    private String name;

    private Double price;

    private String pictureList;

    private String specification;

    private String service;

    private String color;

    private String size;

    private Long shopId;

    private String modifiedTime;

    private Long cityId;

    private String cityName;

    private Long brandId;

    private String brandName;

}
