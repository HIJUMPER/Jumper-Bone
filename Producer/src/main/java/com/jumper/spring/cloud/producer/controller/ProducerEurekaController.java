package com.jumper.spring.cloud.producer.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.eureka.EurekaInstanceConfigBean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
@RequestMapping(path = "producerEureka")
public class ProducerEurekaController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ProducerEurekaController.class);
    @Autowired
    private EurekaInstanceConfigBean eurekaInstanceConfigBean;
    @RequestMapping(path = "get")
    public String get() {
        Random random  = new Random();
        int randomId = random.nextInt();
        String res = eurekaInstanceConfigBean.getInstanceId()+"-"+randomId;
        LOGGER.info("I am called......{}",res);
        return res;
    }
}
