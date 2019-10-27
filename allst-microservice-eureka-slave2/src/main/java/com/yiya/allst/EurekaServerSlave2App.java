package com.yiya.allst;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * Hello world!
 *
 */
@SpringBootApplication
@EnableEurekaServer
public class EurekaServerSlave2App
{
    public static void main( String[] args )
    {
        SpringApplication.run(EurekaServerSlave2App.class, args);
    }
}
