package cn.jumper.study.pattern;

import cn.jumper.study.pattern.singleton.BasicSingleton;
import cn.jumper.study.pattern.singleton.EnumSingleton;
import cn.jumper.study.pattern.singleton.InnerSingleton;
import cn.jumper.study.pattern.singleton.Singleton;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * @author Jumper
 * 2020/2/28
 */
public class SingletonTest {

    @Test
    public void aInnerSingleton() {
        Assertions.assertEquals(0, InnerSingleton.initializingCount);
        Assertions.assertNotNull(InnerSingleton.getInstance());
        Assertions.assertEquals(1, InnerSingleton.initializingCount);
        Assertions.assertNotNull(InnerSingleton.getInstance());
        Assertions.assertEquals(1, InnerSingleton.initializingCount);
    }


    @Test
    public void reflectSingletonTest() throws IllegalAccessException, InvocationTargetException, InstantiationException {
        //reflection issue for singleton
        Class<BasicSingleton> basicSingletonClass = BasicSingleton.class;
        Constructor<?>[] declaredConstructors = basicSingletonClass.getDeclaredConstructors();
        Constructor<?>[] constructors = basicSingletonClass.getConstructors();
        Constructor<?> declaredConstructor = declaredConstructors[0];
        declaredConstructor.setAccessible(true);
        Object o = declaredConstructor.newInstance();
        Assertions.assertNotNull(o);
    }

    @Test
    public void isSingleton() {
        //Check Enum singleton
        Assertions.assertTrue(Singleton.isSingleton(() -> {
            return EnumSingleton.ENUM_SINGLETON;
        }));
        Assertions.assertFalse(Singleton.isSingleton(() -> new ModelWithoutConstructor()));
        Assertions.assertTrue(Singleton.isSingleton(() -> BasicSingleton.getInstance()));
        Assertions.assertTrue(Singleton.isSingleton(() -> InnerSingleton.getInstance()));
    }

    class ModelWithoutConstructor {

    }
}
