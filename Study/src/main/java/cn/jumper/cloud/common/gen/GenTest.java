package cn.jumper.cloud.common.gen;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class GenTest {
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Father child = new Child();
        child.action();
        Method action = Father.class.getDeclaredMethod("action");
        action.invoke(child);
    }
}
