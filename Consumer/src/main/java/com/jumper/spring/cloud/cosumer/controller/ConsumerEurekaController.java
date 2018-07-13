package com.jumper.spring.cloud.cosumer.controller;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * 验证 RestTemplate,EurekaClient,DiscoveryClient
 */
@RestController
@RequestMapping(path = "consumerEureka")
public class ConsumerEurekaController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ConsumerEurekaController.class);
    /**
     * 此类为Spring Cloud对Eureka的封装，在配置类上增加EnableDiscoveryClient注解使spring对此类进行初始化；初始化时会自动注册到Eureka，如需要手动注册可以设置属性为false,并实现相关的接口
     */
    @Autowired
    private DiscoveryClient discoveryClient;
    /**
     * RestTemplate不会自动被spring托管需要代码手动创建
     */
    @Autowired
    @Qualifier("restNormalTemplate")
    private RestTemplate restTemplate;
    /**
     * 一旦引入EurekaClient的相关maven以来，spring-boot会通过conditionalOnXXX注解自动托管eurekaClient
     */
    @Autowired
    private EurekaClient eurekaClient;

    @RequestMapping(path = "get")
    public String get() {
//        不使用Eureka直接调用
//        String res = restTemplate.getForObject("http://127.0.0.1:56439/producerOne/get", String.class);
//        通过Eureka客户端获取client instance 的metadata
        InstanceInfo instanceInfo = eurekaClient.getNextServerFromEureka("producer", false);
        LOGGER.info("Metadata of Instanceinfo : {}",instanceInfo.getMetadata());
//      获得Eureka上的说有服务.
//      Output:
//          producer
//          consumer
        List<String> services = discoveryClient.getServices();
        services.forEach(LOGGER::info);
//        获取Eureka Client 的描述信息.DiscoveryClient接口在Spring Cloud中有四个实现
//          EurekaDiscoverClient : 通过EurekaClient获取调用端信息，并封装为SrviceInstance
//          NoopDiscoveryClient: ClassPath中找不到Discovery的实现时初始化此类，实现接口中的方法返回空集合
//          SimpleDiscoveryClient : 通过yaml中配置的调用端信息返回ServiceInstance，详见源码
//          CompositeDiscoveryClient : 把以上实现整合
//        Output:
//          Composite Discovery Client
        String description = discoveryClient.description();
        LOGGER.info(description);
//        获取指定serverid的ServiceInstance
        List<ServiceInstance> serviceInstances = discoveryClient.getInstances("producer");
        serviceInstances.forEach(serviceInstance -> LOGGER.info(restTemplate.getForObject(serviceInstance.getUri().toString()+"/producerEureka/get",String.class)));
        return "Consumer One Success";
    }

}
