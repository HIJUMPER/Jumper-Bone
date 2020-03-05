package cn.jumper.bone.singlenode.controller;

import cn.jumper.bone.singlenode.exceptions.SingleNodeException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * https://dzone.com/articles/global-exception-handling-with-controlleradvice <br/>
 */
@ControllerAdvice(basePackageClasses = SingleNodeController.class)
@Component
public class SinglenodeControllerAdvice {

    /**
     * 只处理从controller方法中抛出的异常。方法之外抛出的异常不会被处理。例如：MethodArgumentNotValidException;ConstraintViolationException
     *
     * @param singleNodeException
     * @return
     */
    @ExceptionHandler(SingleNodeException.class)
    public ResponseEntity<String> exception(SingleNodeException singleNodeException) {
        /**
         * JDK8 新功能
         */
//        Optional.of("")

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed\n");
    }


}
