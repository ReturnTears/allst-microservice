package com.yiya.allst;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author YiYa
 * @since 2019-10-27 下午 09:23
 */
@SpringBootApplication
@EnableEurekaServer
public class EurekaServerSlave1App {
    public static void main(String[] args) {
        SpringApplication.run(EurekaServerSlave1App.class, args);
    }
}
