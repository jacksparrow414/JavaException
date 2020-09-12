package com.java.exception.common.exceptiondemo;

import com.java.exception.common.entity.User;
import java.util.LinkedList;
import java.util.List;
import org.springframework.util.CollectionUtils;

/**
 * List元素为空.
 *
 * <p>List是可以add 空元素的,当不能百分百确定拿到的元素不为空时，取元素的属性之前一定对该元素进行判空</p>
 */
public class ListElementNullException {
    
    public static void main(String[] args) {
        List<User> list = new LinkedList<>();
        list.add(null);
        if (!CollectionUtils.isEmpty(list)) {
            User user = list.get(0);
            user.getName();
        }
    }
}
