package cn.jumper.study.pattern.singleton;

import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;
import java.util.function.Supplier;

/**
 * @author Jumper
 * 2020/2/26
 */
public class Singleton {


    public static boolean isSingleton(Supplier<Object> func) {
        Object o = func.get();
        if (o == null) {
            return false;
        }
        Class<?> oClz = o.getClass();
        if (oClz.isEnum()) {
            return true;
        }
        Constructor<?>[] declaredConstructors = oClz.getDeclaredConstructors();
        for (Constructor constructor : declaredConstructors) {
            int modifiers = constructor.getModifiers();
            if (modifiers == Modifier.PRIVATE) {
                return true;
            }
        }
        return false;
    }


    public static boolean isSingletonV2(Supplier<Object> func) {
        Object o1 = func.get();
        Object o2 = func.get();
        return o1 == o2;

    }
}
