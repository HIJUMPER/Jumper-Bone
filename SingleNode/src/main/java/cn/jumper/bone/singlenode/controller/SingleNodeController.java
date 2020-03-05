package cn.jumper.bone.singlenode.controller;

import cn.jumper.bone.singlenode.exceptions.SingleNodeException;
import cn.jumper.bone.singlenode.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.ConstraintViolation;
import javax.validation.Valid;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;
import java.util.Set;

@RestController
@Validated
public class SingleNodeController {
    private static final Logger LOGGER = LoggerFactory.getLogger(SingleNodeController.class);

    @RequestMapping("/test")
    @ResponseBody
    public ResponseEntity<String> test() throws InterruptedException {
        LOGGER.info("I am comming....");
        Thread.sleep(8000);
        LOGGER.info("Task has done");
        return ResponseEntity.ok().body("Success");
    }

    @RequestMapping("/validation")
    @ResponseBody
    public ResponseEntity<String> validation(User user)  {
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        Set<ConstraintViolation<User>> validate = validatorFactory.getValidator().validate(user);
        validate.forEach((cusumer)->{
            LOGGER.info(cusumer.getMessage());
        });
        return ResponseEntity.ok().body("Success");
    }

    @RequestMapping("/exception")
    @ResponseBody
    public ResponseEntity<String> exception() throws SingleNodeException {
        throw new SingleNodeException();
//        return  ResponseEntity.ok("Success");
    }
    @RequestMapping("/exceptionAuto")
    @ResponseBody
    public ResponseEntity<String> exceptionAuto( @Valid User user) {

        return  ResponseEntity.ok("Success\n");
    }
}
