package com.chenshun.eshopcacheha.cache.local;

import java.util.HashMap;
import java.util.Map;

/**
 * User: mew <p />
 * Time: 18/5/21 17:11  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
public class LocationCache {

    private static Map<Long, String> cityMap = new HashMap<>();

    private static Map<Long, Long> productCityMap = new HashMap<>();

    static {
        cityMap.put(1L, "北京");
        cityMap.put(2L, "上海");
        cityMap.put(3L, "广州");
        cityMap.put(4L, "深圳");

        productCityMap.put(-1L, 1L);
    }

    public static String getCityName(Long cityId) {
        return cityMap.get(cityId);
    }

    public static Long getCityId(Long productId) {
        return productCityMap.get(productId);
    }

}
