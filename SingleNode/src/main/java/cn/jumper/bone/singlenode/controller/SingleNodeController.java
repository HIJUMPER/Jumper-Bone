package cn.jumper.bone.singlenode.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
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
}
