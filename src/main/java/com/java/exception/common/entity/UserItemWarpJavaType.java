package com.java.exception.common.entity;

import lombok.Data;

/**
 * java包装类是引用类型，也就是对象的形式，占用的空间也更多，并且引用类型是没有默认值的.都是null
 * 引用类型都是4个字节
 * 4 * 8 = 32 + 12 = 44 以8字节对齐， 48
 *
 * java基本类型、引用类型扫盲篇<a href="https://www.cnblogs.com/xiaomengyuan/articles/9045270.html"></a>
 * @author jacksparrow414
 * @date 2020/11/21 13:56
 */
@Data
public class UserItemWarpJavaType {
    private Byte defaultByte;
    private Short defaultShort;
    private Integer defaultInteger;
    private Long defaultLong;
    private Float defaultFloat;
    private Character defaultCharacter;
    private Boolean defaultBoolean;
    private Double defaultDouble;
}
