package com.yiya.allst.cfgbeans;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
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

    /**
     * 默认时轮询算法, 下面是我们自定义的随机算法代替轮询
     * @return
     *          算法
     */
    @Bean
    public IRule myRule() {
        return new RandomRule();
    }
}
