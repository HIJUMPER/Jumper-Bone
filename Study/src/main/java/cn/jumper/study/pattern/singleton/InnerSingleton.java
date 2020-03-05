package cn.jumper.study.pattern.singleton;

/**
 * @author Jumper
 * 2020/2/27
 */
public class InnerSingleton {

    private InnerSingleton() {
        System.out.println("InnerSingletonIn initializing");
    }

    public static int initializingCount = 0;

    private static class Imp {

        static {
            ++initializingCount;
        }

        static final InnerSingleton INNER_SINGLETON = new InnerSingleton();
    }

    public static InnerSingleton getInstance() {
        return Imp.INNER_SINGLETON;
    }


}
