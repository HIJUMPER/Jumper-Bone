package cn.jumper.cloud.common.proxy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CglibSub {
    private static final Logger  LOGGER = LoggerFactory.getLogger(CglibSub.class);

    public void doSome(){
        LOGGER.info("Doing something...");
    }
}
