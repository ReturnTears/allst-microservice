package com.yiya.allst;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * Hello world!
 *
 */
@SpringBootApplication
@EnableEurekaClient
public class DeptConsumerApp
{
    public static void main( String[] args )
    {
        SpringApplication.run(DeptConsumerApp.class, args);
    }
}
