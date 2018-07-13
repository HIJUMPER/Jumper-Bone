package com.jumper.spring.cloud.cosumer.config;

import com.jumper.spring.cloud.cosumer.rule.RandomLoadBalancerRule;
import com.jumper.spring.cloud.cosumer.rule.SerinusCanariaLoadBalancerRule;
import com.netflix.loadbalancer.IRule;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
@RibbonClient(name = "producer")
public class ConsumerConfiguration {

    /**
     * RibbonClient依赖在RibbonClientAutoConfig中配置；
     * RibbonClient在RibbonAutoConfig中配置
     */

    @LoadBalanced
    @Bean(name="restRibbonTemplate")
    public RestTemplate restRibbonTemplate(){
        return  new RestTemplate();
    }

    @Bean(name  ="restNormalTemplate")
    public RestTemplate restNormalTemplate(){
        return new RestTemplate();
    }

    /**
     * 此类在第一次请求的时候实例化
     */
//    @Bean
    public IRule randomLoadBalancerRule(){
        return new RandomLoadBalancerRule();
    }

    @Bean
    public IRule serinusCanariaLoadBlancerRule(){
        return new SerinusCanariaLoadBalancerRule();
    }
}


