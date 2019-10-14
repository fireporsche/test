package com.haishu.common.components;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * InitComponent
 * @author zhb
 * @date 2019/07/25
 */

@Component
@Order(value = 2)
public class InitComponent implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
    }
}
