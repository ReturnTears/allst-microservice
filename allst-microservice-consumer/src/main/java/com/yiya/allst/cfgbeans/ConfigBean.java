package com.yiya.allst.cfgbeans;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author YiYa
 * @since 2019-10-20 下午 08:26
 */
@Configuration
public class ConfigBean {

    @Bean
    @LoadBalanced   // 开启负责均衡注解
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }

}
