package com.java.exception.common.exceptiondemo;

import com.java.exception.common.entity.UserItemBasicJavaType;
import com.java.exception.common.entity.UserItemWarpJavaType;
import org.openjdk.jol.info.ClassLayout;

/**
 * 这里的基本类型和包装类型的对象大小一样，都是48.
 * 但是现实中更多的情况不像现在这样，所以对于一些基本类型占空间小的类型，short、byte尽量使用基本类型而不使用包装类型
 * @author jacksparrow414
 * @date 2020/11/21 14:00
 */
public class JavaBasicAndWarpTypeSize {
    public static void main(String[] args) {
        UserItemBasicJavaType basicJavaType = new UserItemBasicJavaType();
        System.out.println("输出java基本类型的默认值:");
        System.out.println(basicJavaType.getDefaultbyte());
        System.out.println(basicJavaType.getDefaultshort());
        System.out.println(basicJavaType.getDefaultint());
        System.out.println(basicJavaType.getDefaultfloat());
        System.out.println(basicJavaType.getDefaultlong());
        System.out.println(basicJavaType.getDefaultchar());
        System.out.println(basicJavaType.getDefaultdouble());
        System.out.println("由【基本类型】组成的类占用字节数是："+ ClassLayout.parseClass(UserItemBasicJavaType.class).toPrintable());
        UserItemWarpJavaType warpJavaType = new UserItemWarpJavaType();
        System.out.println("输出java包装类型的默认值:");
        System.out.println(warpJavaType.getDefaultByte());
        System.out.println(warpJavaType.getDefaultShort());
        System.out.println(warpJavaType.getDefaultInteger());
        System.out.println(warpJavaType.getDefaultFloat());
        System.out.println(warpJavaType.getDefaultLong());
        System.out.println(warpJavaType.getDefaultDouble());
        System.out.println(warpJavaType.getDefaultCharacter());
        System.out.println(warpJavaType.getDefaultBoolean());
        System.out.println("由【包装类型】组成的类占用字节数是："+ ClassLayout.parseClass(UserItemWarpJavaType.class).toPrintable());
    }
}
