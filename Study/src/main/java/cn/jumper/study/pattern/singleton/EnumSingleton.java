package cn.jumper.study.pattern.singleton;

/**
 * @author Jumper
 * 2020/2/28
 */
public enum EnumSingleton {
    ENUM_SINGLETON("Test");
    private String value;

    EnumSingleton(String val){
        this.value = val;
    }
}
