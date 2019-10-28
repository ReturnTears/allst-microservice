package com.yiya.allst;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * Hello world!
 *
 */
@SpringBootApplication
@EnableEurekaClient // 本地服务启动后会自动注册进eureka服务中
@EnableDiscoveryClient  // 服务发现
public class ProviderApp2
{
    public static void main( String[] args )
    {
        SpringApplication.run(ProviderApp2.class, args);
    }

}
