package com.java.exception.common.exceptiondemo;

/**
 * 三目运算错误.
 *
 * <p>expression ? S : T <br/>
 * 日常开发中[必须]要确保S和T的数据类型一致 <br/>
 * 当基本数据类型-S遇到 包装类型-T 时，会默认将包装类型转为基本类型。那么此时包装类型为null，就会报出空指针</p>
 */
public class TrinocularOperationException {
    
    public static void main(String[] args) {
        Integer firstParam = null;
        int secondParam = 5;
        System.out.println(false ? secondParam : firstParam);
    }
}
