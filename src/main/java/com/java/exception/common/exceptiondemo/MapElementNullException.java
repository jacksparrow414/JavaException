package com.java.exception.common.exceptiondemo;

import com.java.exception.common.entity.User;
import java.util.HashMap;
import java.util.Map;

/**
 * Map 的Key,Value为空.
 *
 * <p>Map的key,value均可以为空。
 * 在不能百分百确定元素一定不为空时，使用之前一定要判空。
 * 可以在map.getOrDefault(key, null)时，给一个默认值null，
 * 然后对value进行Optional.ofNullable(value).ifPresent()这样处理更好<p/>
 */
public class MapElementNullException {
    
    public static void main(String[] args) {
        Map<Integer, User> map = new HashMap<>(1);
        map.put(null, null);
        User value = map.get(1);
        value.getName();
    }
}
