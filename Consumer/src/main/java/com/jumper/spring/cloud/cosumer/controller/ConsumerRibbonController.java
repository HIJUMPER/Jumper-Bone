package com.jumper.spring.cloud.cosumer.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * EurekaRibbonClientAutoconfiguration:
 * IPing;ServerList;
 * LoadBalancerAutoConfiguration:
 * Inteceptor
 * <p>
 * <p>
 * IServerBalancer:
 * ServerListUpdater:更新ServerList的策略(定时器，Eureka客户端事件)
 * IServerList:从Eureka注册中心获取的Servers列表中那些需要放入IServerBalancer的Server列表中
 * ServerListFilter:过滤获取到的Server
 * PrimeConnections:挨个调用Server，检查是否可以联通
 * <t/>Primeconnection:怎样的操作认为Server是可以联通的
 * IPing:检查于Server连接是否正常(更具Eureka状态，真实的请求UR，直接返回true)，在BaseLoadBalancer中实现；新增的放入upServerList,不可达的更改Server状态active为false
 */
@RestController
@RequestMapping(path = "comsumerRibbonWithoutFeign")
public class ConsumerRibbonController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ConsumerRibbonController.class);
    @Autowired
    @Qualifier("restRibbonTemplate")
    RestTemplate restTemplate;

    @RequestMapping(path = "get")
    public String balanceRequest() {
        /**
         * 默认值：
         * ILoadBalancer:DynamicServerListLoadBalancer
         *      IRule:ZoneAvoidanceRule
         *      IFilter:ZonePreferenceServerListFilter
         *      ServerList:DomainExtractiongServerList
         *      ServerListUpdater:PollingServerListUpdater
         *      IPing:NIWSDiscoveryPing
         *
         *
         *      IClientConfig ribbonClientConfig: DefaultClientConfigImpl
         *      IRule ribbonRule: ZoneAvoidanceRule
         *      ServerListFilter<Server> ribbonServerListFilter: ZonePreferenceServerListFilter
         *      ILoadBalancer ribbonLoadBalancer: ZoneAwareLoadBalancer
         *      ServerListUpdater ribbonServerListUpdater: PollingServerListUpdater
         */
        LOGGER.info("Test for Default auto configuration.");
        String res = restTemplate.getForObject("http://producer/producerEureka/get", String.class);
        LOGGER.info(res);
        return res + "\n";
    }
}
