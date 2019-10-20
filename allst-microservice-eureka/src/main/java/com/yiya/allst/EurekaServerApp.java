package com.yiya.allst;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * Hello world!
 *
 */
@SpringBootApplication
@EnableEurekaServer         // EurekaServer服务端启动类, 接受其他微服务注册进来
public class EurekaServerApp
{
    public static void main( String[] args )
    {
        SpringApplication.run(EurekaServerApp.class, args);
    }
}
