package com.jumper.spring.cloud.cosumer.rule;

import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.AbstractLoadBalancerRule;
import com.netflix.loadbalancer.Server;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Random;
/**
 *  实现一个随机的LoadBalancer。
 *  于Ribbon实现的RandomRule比较：
 *      Thread.yeid(),防止循环的时候始终占用资源
 *      While获取Server
 */
public class RandomLoadBalancerRule extends AbstractLoadBalancerRule {

    private static final Logger LOGGER = LoggerFactory.getLogger(RandomLoadBalancerRule.class);
    private Random random = new Random();


    @Override
    public Server choose(Object key) {
        List<Server> allServers = getLoadBalancer().getAllServers();
        Integer index = random.nextInt(allServers.size());
        LOGGER.info("Total Producer Count is :{},Random index is :{}",allServers.size(),index);
        return allServers.get(index);
    }

    @Override
    public void initWithNiwsConfig(IClientConfig clientConfig) {
        //TODO Print clientConfig see what it is
        LOGGER.info("=========================\n{}",clientConfig);
    }
}
