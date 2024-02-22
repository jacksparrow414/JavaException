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
 *
 * 如何理解Stream和 数据data 呢？数据就像水流，要么处于一直不停的流动中, 要么会被某个[容器]储存起来-对应文件, 要么被泼掉-对应文件被删除。
 * 数据从源头流到目的地，最终必须被消费或储存。而stream像是一个管道，连接着源、目的两端。
 *
 * 以FileInputStream为例，数据从文件流到程序中开辟的byte[]中，这些流进来的数据需要消费掉，所以我们经常在read()方法的下一行看到outputStream.write(bytes, 0, bytes.length);这样的代码。
 * 将这些数据流要写到文件中，否则，其会一直留在内存中，byte[]越来越大了, 直到内存溢出。
 *
 * 有几个问题思考：
 * Q1: read(byte b[], 0, b.length)方法将流读入到字节数组中，如果后续不通过write方法将字节数组b写入到文件中，那么这些字节数组会被下次读取的字节数组覆盖吗？
 * A1: 会，因为每次方法调用，读取的字节都会放到该数组中,每次的位置都是从0开始，读取字节数是b.length
 *
 * Q2: 文件流FileInputStream读取可以每次读取b个字节，是因为文件就在磁盘上，可以随时读取。但是如果是Socket网络传输的话，InputStream的源头的数据发送速率高于OutputStream的消费速率，会怎么样？
 * A2: 到最底层，以TCP/IP为例，TCP/IP是以字节流方式发送数据的(计算机网络中说的), TCP/IP报文中有滑动窗口的概念, 接收方在ACK的时候会把当前可用缓冲区的大小告诉发送方，发送方根据这个大小来发送对应数据量的数据。
 *     如果接收方没有可用的窗口，发送方会停止发送数据，等待接收方的ACK，这样就不会出现发送方发送的数据比接收方消费的数据快的情况。
 * Q3: 那如果发送方一直等待呢？
 * A3: 连接有超时限制，超时之后可能直接断开连接也肯能重传。(这个回答为猜测，还没找到书籍佐证)
 *
 * Q4: 有了上面的几个QA，还可以知道/在实际编码中要注意什么?
 * A4: 在实际编码中， 要注意InputStream的目的地OutputStream是什么。如果目的地是File则不需要担心，因为Q1的方式使用了缓冲区，不会造成OOM。
 *     如果是目的地是内存，则要当心，例如ByteArrayOutputStream/StringWriter等，因为这些类的底层的byte[]/char[]是在内存中的, 数据量太大，会造成OOM。它们的close方法也是空的。
 *
 * Q5: 既然这样，它们没法关闭，那它们底层的byte[]/char[]什么时候被释放? 它们岂不是很容易造成OOM?
 * A5: 也不是，它们用完之后，如果没被其他对象引用，则可以通过GC回收掉，随之的byte[]/char[]也会被回收掉。
 *     stackoverflow回答: https://stackoverflow.com/questions/2330569/closing-a-bytearrayoutputstream-has-no-effect
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
