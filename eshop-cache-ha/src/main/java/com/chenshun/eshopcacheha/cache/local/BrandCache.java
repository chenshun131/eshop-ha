package com.chenshun.eshopcacheha.cache.local;

import java.util.HashMap;
import java.util.Map;

/**
 * User: mew <p />
 * Time: 18/5/22 17:03  <p />
 * Version: V1.0  <p />
 * Description: 品牌缓存 <p />
 */
public class BrandCache {

    private static Map<Long, String> brandMap = new HashMap<>();

    private static Map<Long, Long> productBrandMap = new HashMap<>();

    static {
        brandMap.put(1L, "iphone");
        brandMap.put(2L, "huawei");
        brandMap.put(3L, "xiaomi");
        brandMap.put(4L, "vivo");
        brandMap.put(5L, "oppo");

        productBrandMap.put(-1L, 1L);
    }

    public static String getBrandName(Long brandId) {
        return brandMap.get(brandId);
    }

    public static Long getBrandId(Long productId) {
        return productBrandMap.get(productId);
    }

}
