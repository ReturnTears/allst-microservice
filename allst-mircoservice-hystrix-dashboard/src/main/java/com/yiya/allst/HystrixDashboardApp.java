package com.yiya.allst;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

/**
 * 启动类
 *
 */
@SpringBootApplication
@EnableHystrixDashboard
public class HystrixDashboardApp
{
    public static void main( String[] args )
    {
        SpringApplication.run(HystrixDashboardApp.class, args);
    }
}
