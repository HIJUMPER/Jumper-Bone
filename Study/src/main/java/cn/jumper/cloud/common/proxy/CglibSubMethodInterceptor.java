package cn.jumper.cloud.common.proxy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class CglibSubMethodInterceptor implements MethodInterceptor {
    private static final Logger LOGGER = LoggerFactory.getLogger(CglibSubMethodInterceptor.class);

    private Object cglibSub;
    public CglibSubMethodInterceptor(Object cjlibSub) {
        this.cglibSub =cjlibSub;
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        LOGGER.info("Before invoke...");
        Object obj = null;
//      obj = method.invoke(cglibSub,objects);
        obj = methodProxy.invokeSuper(o,objects);
//        method.invoke(o,objects);
        LOGGER.info("After invoke...");
        return obj;
    }
}
