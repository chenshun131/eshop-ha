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

    static {
        brandMap.put(1L, "iphone");
        brandMap.put(2L, "huawei");
        brandMap.put(3L, "xiaomi");
        brandMap.put(4L, "vivo");
        brandMap.put(5L, "oppo");
    }

    public static String getBrandName(Long brandId) {
        return brandMap.get(brandId);
    }

}
