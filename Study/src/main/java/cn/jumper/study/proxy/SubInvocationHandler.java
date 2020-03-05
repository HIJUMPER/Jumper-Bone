package cn.jumper.study.proxy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class SubInvocationHandler implements InvocationHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(SubInterface.class);
    private Object object;

    public SubInvocationHandler(Object object) {
        this.object = object;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        LOGGER.info("Before invoke....");
        Object invoke = method.invoke(object,args);
        LOGGER.info("After invoke...");
        return invoke;
    }
}
