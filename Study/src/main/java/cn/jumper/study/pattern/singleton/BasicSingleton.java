package cn.jumper.study.pattern.singleton;

/**
 * @author Jumper
 * 2020/2/26
 */
public class BasicSingleton {

    private static final BasicSingleton basicSingleton
            = new BasicSingleton();

    private BasicSingleton() {

    }

    public static BasicSingleton getInstance(){
        return basicSingleton;
    }
}
