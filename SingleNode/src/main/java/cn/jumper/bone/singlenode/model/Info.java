package cn.jumper.bone.singlenode.model;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * @author Jumper
 * 8/20/18
 */
public class Info {
    @NotNull
    private String firstName;
    private String lastName;
}
