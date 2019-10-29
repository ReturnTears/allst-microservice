package com.yiya.allst;

import com.yiya.rule.MySelfRule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;

/**
 * Hello world!
 *
 */
@SpringBootApplication
@EnableEurekaClient
@RibbonClient(name = "ALLST-MICROSERVICECLOUD-DEPT", configuration = MySelfRule.class)
public class DeptConsumerApp
{
    public static void main( String[] args )
    {
        SpringApplication.run(DeptConsumerApp.class, args);
    }
}
