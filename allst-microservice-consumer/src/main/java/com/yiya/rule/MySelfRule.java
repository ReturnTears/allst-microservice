package com.yiya.rule;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 自定义Ribbon
 * @author YiYa
 * @since 2019-10-29 下午 11:27
 */
@Configuration
public class MySelfRule {

    /**
     * 自定义为随机算法
     * @return
     */
    @Bean
    public IRule mySelfRule() {
        /*return new RandomRule();*/
        // 根据轮询算法自定义算法规则
        return new MySelfRuleBySources();
    }

}
