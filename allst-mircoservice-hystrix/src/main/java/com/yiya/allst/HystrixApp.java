package com.yiya.allst;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * Hystrix服务启动类
 *
 */
@SpringBootApplication
@EnableEurekaClient // 本地服务启动后会自动注册进eureka服务中
@EnableDiscoveryClient  // 服务发现
@EnableCircuitBreaker   // Hystrix熔断机制
public class HystrixApp
{
    public static void main( String[] args )
    {
        SpringApplication.run(HystrixApp.class, args);
    }
}
