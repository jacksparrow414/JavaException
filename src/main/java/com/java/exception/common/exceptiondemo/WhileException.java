package com.java.exception.common.exceptiondemo;

import lombok.extern.slf4j.Slf4j;

/**
 * @author jacksparrow414
 * @date 2021/1/11
 */
@Slf4j
public class WhileException {
    public static void main(String[] args) {
        int target = 1;
        while (target < 5) {
            log.info("执行之前{}", target);
            target++;
            log.info("执行之后{}", target);
        }
        int secondTarget = 1;
        while (secondTarget < 5) {
            log.info("++执行之前{}", secondTarget);
            ++secondTarget;
            log.info("++执行之后{}", secondTarget);
        }
    }
}
