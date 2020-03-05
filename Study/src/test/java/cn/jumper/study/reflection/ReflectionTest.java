package cn.jumper.study.reflection;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author Jumper
 * 2020/3/5
 */
public class ReflectionTest {

    @Test
    public void overwriteMethodTest() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Father child = new Child();
        Assertions.assertEquals(1, child.action());
        Method action = Father.class.getDeclaredMethod("action");
        Assertions.assertEquals(1, action.invoke(child));
    }
}
