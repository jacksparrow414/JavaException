package com.java.exception.common.exceptiondemo;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;

import java.io.IOException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * @author jacksparrow414
 * @date 2020/11/15
 */
public class JavaHeapException {
    
    public static void main(String[] args) throws Exception {
        for (int i = 0; i < 200; i++) {
            Executor executor = Executors.newFixedThreadPool(5);
            executor.execute(() -> {
                try {
                    
                    Resource resource = new UrlResource("https://www.baidus.com");
                    resource.getInputStream();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }
    }
}
