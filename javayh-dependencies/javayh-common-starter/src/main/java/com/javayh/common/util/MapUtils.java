package com.javayh.common.util;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;


/**
 * Map工具类
 */
public class MapUtils extends HashMap<String, Object> {

    @Override
    public MapUtils put(String key, Object value) {
        super.put(key, value);
        return this;
    }

    public static <K, V extends Comparable<? super V>> Map<K, V> sortByValue(Map<K, V> map) {
        Map<K, V> result = new LinkedHashMap<>();

        map.entrySet().stream()
                .sorted(Entry.<K, V>comparingByValue()
                        .reversed()).forEachOrdered(e -> result.put(e.getKey(), e.getValue()));
        return result;
    }

    public static <K extends Comparable<? super K>, V> Map<K, V> sortByKey(Map<K, V> map) {
        Map<K, V> result = new LinkedHashMap<>();
        map.entrySet().stream()
                .sorted(Entry.<K, V>comparingByKey()
                        .reversed()).forEachOrdered(e -> result.put(e.getKey(), e.getValue()));
        return result;
    }


    //将map中的key和val互换
    public static Map<String, String> exchangeKeyVal(Map<String, String> oldMap){
        Map<String, String> newMap = new HashMap<>();
        if(null!=oldMap&&oldMap.size()>0){
            for(String key: oldMap.keySet()){
                newMap.put(oldMap.get(key),key);
            }
        }
        return newMap;
    }

    /**
     * <p>
     *       求两个Map的差集
     * </p>
     * @version 1.0.0
     * @author Dylan-haiji
     * @since 2020/4/24
     * @param bigMap
     * @param smallMap
     * @return java.util.Map<java.lang.String,java.lang.Object>
     */
    public static Map<String, Object> getDifferenceSetByGuava(Map<String, Object> bigMap, Map<String, Object> smallMap) {
        Set<String> bigMapKey = bigMap.keySet();
        Set<String> smallMapKey = smallMap.keySet();
        Set<String> differenceSet = Sets.difference(bigMapKey, smallMapKey);
        Map<String, Object> result = Maps.newHashMap();
        for (String key : differenceSet) {
            result.put(key, bigMap.get(key));
        }
        return result;
    }

}
