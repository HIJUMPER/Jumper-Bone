package cn.jumper.bone.singlenode.model;

import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * 测试validation
 */
public class User {

    @Valid
    private Info info;

//    @NotNull
    public String name ;


}
