package com.common.exception;

import com.common.result.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 全局异常处理类
 *
 * @author panyx
 * @since 2023-12-09 22:36:00
 */
// AOP 面向切面 不改变原代码增加功能
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result error(Exception e){
        e.printStackTrace();
        return Result.error(null);
    }

    /**
     * 自定义异常处理方法
     */
    @ExceptionHandler(RRException.class)
    @ResponseBody
    public Result error(RRException e){
        return Result.error(null);
    }
}
