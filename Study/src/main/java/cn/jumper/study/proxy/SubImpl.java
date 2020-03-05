package cn.jumper.study.proxy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SubImpl implements SubInterface {
    private static final Logger  LOGGER = LoggerFactory.getLogger(SubImpl.class);
    @Override
    public void action() {
        LOGGER.info("{}","I am in action.");
    }

    @Override
    public String toString() {
        return "SubImpl{} "+ Thread.currentThread().getId();
    }
}
