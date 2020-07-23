package com.ling.hr;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * 支付服务启动类
 */
@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan(basePackages = {"com.ling"})
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}