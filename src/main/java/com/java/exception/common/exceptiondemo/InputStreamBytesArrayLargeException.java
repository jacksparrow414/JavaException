package com.java.exception.common.exceptiondemo;

import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.time.Duration;
import java.time.LocalDateTime;

/**
 * 一次性将InputStream全部字节读入到内存中，引起内存溢出.<br/>
 * <br/>
 * 当InputStream流很大时，这时如果将全部字节读入到内存中，<br/>
 * 并且JVM内存此时已经不够全部字节的内存大小，则直接报出内存溢出<br/>
 * <br/>
 * 或者JVM内存满足一个InputStream流的全部字节，但是当多个并发请求过来时，必定会撑爆JVM内存<br/>
 * <br/>
 * 视频文件大小200M<br/>
 * JVM最大内存40M，JVM配置：-XX:MaxHeapSize=40m -XX:InitialHeapSize=40m
 */
@Slf4j
public class InputStreamBytesArrayLargeException {
    
    /**
     * 错误使用姿势.
     *
     * @param args
     * @throws Exception
     */
//    public static void main(String[] args) throws Exception{
//        File file = new File("/Users/jacksparrow414/Downloads/480P_2000K_326434702.mp4");
//        InputStream inputStream = new FileInputStream(file);
//        byte[] bytes = new byte[inputStream.available()];
//        int start = 0;
//        LocalDateTime startTime = LocalDateTime.now();
//        // 一个一个字节读取，非常耗时
//        while (-1 != inputStream.read()) {
//            bytes[start] = (byte) inputStream.read();
//            start++;
//        }
//        LocalDateTime endTime = LocalDateTime.now();
//        Duration duration = Duration.between(startTime, endTime);
//        // 耗时一分半
//        log.info("读取文件完毕，耗时{}毫秒", duration.toMillis());
//    }
    
    /**
     * 正确使用姿势.
     *
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception{
        File sourceFile = new File("/Users/jacksparrow414/Downloads/480P_2000K_326434702.mp4");
        File targetFile = new File("/Users/jacksparrow414/Downloads/target.mp4");
        InputStream inputStream = new FileInputStream(sourceFile);
        OutputStream outputStream = new FileOutputStream(targetFile);
        byte[] bytes = new byte[4096];
        LocalDateTime startTime = LocalDateTime.now();
        // 每次会将InputStream中的流读入到固定的bytes字节数组中
        while (-1 != inputStream.read(bytes, 0, bytes.length)) {
            outputStream.write(bytes, 0, bytes.length);
        }
        LocalDateTime endTime = LocalDateTime.now();
        Duration duration = Duration.between(startTime, endTime);
        log.info("读取文件完毕, 耗时{}毫秒", duration.toMillis());
    }
}
