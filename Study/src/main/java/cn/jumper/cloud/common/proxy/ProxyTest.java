package cn.jumper.cloud.common.proxy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cglib.core.ClassGenerator;
import org.springframework.cglib.core.DebuggingClassWriter;
import org.springframework.cglib.proxy.Enhancer;
import sun.misc.ProxyGenerator;

import java.io.IOException;
import java.lang.reflect.Proxy;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * JDK 的动态代理</br>
 * CGLIB代理</br>
 *
 * @author jumper
 */
public class ProxyTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(ProxyTest.class);

    public static void main(String[] args) {
        Object proxyInstance = getJDKProxyInstanceTow();
        SubInterface subInterface = (SubInterface) proxyInstance;
        subInterface.action();
//        LOGGER.info(subInterface.toString());
//        saveJDKProxyClass();
    }

    /**
     * 测试CGLIB
     */
    private static void testCGLIB(){
        saveCJLIBClass();
        Object cjlibProxyInstance = getCJLIBProxyInstance();
        CglibSub cglibSub =  (CglibSub)cjlibProxyInstance;
        cglibSub.doSome();
    }


    private static void saveCJLIBClass(){
        System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY,"/Users/jumper/WorkSpace");
    }

    /**
     * 测试CGLIB代理<br/>
     * CGLIB代理是通过继承的方式实现代理
     * @return
     */
    private static  Object getCJLIBProxyInstance(){
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(CglibSub.class);
        enhancer.setCallback(new CglibSubMethodInterceptor(new CglibSub()));
        return enhancer.create();
    }


    /**
     * 测试JDK Proxy
     *
     * @return
     */
    private static Object getJDKProxyInstanceTow() {
        //Proxy 根据接口生成代理类，当执行代理类的方法时，会调用Invocation接口的invoke方法，以实现代理控制
        return Proxy.newProxyInstance(SubInterface.class.getClassLoader(), new Class[]{SubInterface.class}, new SubInvocationHandler(new SubImpl()));
    }

    /**
     * 把生成的代理类字节码文件保存到硬盘上
     * >此代理类继承Proxy，在构造是传入InvocationHandler
     * >使用　在static代码块中使用class.forname.getMethod获取调用
     * >在对应的方法调用中把获取到的method调用传入invocationHandler中的invoke 方法执行
     */
    private static void saveJDKProxyClass() {
        byte[] proxy001s = ProxyGenerator.generateProxyClass("Proxy001", new Class[]{SubInterface.class});
        try {
            Files.write(Paths.get("/Users/jumper/WorkSpace/Proxy001.class"), proxy001s);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
