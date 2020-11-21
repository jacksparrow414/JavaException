package com.java.exception.common.entity;

import lombok.Data;

/**
 * java的8种基本类型.都是有默认值的。并且占用的空间都是固定的
 * 1+2+4+4+8+8+2+1+12(Markword 8 + this引用 4) = 42 以8字节对齐48
 * @author jacksparrow414
 * @date 2020/11/21 13:51
 */
@Data
public class UserItemBasicJavaType {
    /**
     * 默认值0,占1个字节
     */
    private byte defaultbyte;
    /**
     * 默认值0,占2个字节
     */
    private short defaultshort;
    /**
     * 默认值0,占4个字节
     */
    private int defaultint;
    /**
     * 默认值0.0,占4个字节
     */
    private float defaultfloat;
    /**
     * 默认值0,占8个字节
     */
    private long defaultlong;
    /**
     * 默认值0.0,占8个字节
     */
    private double defaultdouble;
    /**
     * 默认值 \u0000,占2个字节
     */
    private char defaultchar;
    /**
     * 默认值false,占1个字节
     */
    private boolean defaultboolean;
}
