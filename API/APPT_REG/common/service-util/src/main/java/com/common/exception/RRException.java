package com.common.exception;

import com.common.result.ResultCodeEnum;
import lombok.Data;

/**
 * 自定义异常类
 *
 * @author panyx
 * @since 2023-12-09 22:39:51
 */
@Data
public class RRException extends RuntimeException{

    //异常状态码
    private Integer code;

    /**
     * 通过状态码和错误消息创建异常对象
     * @param message 异常信息
     * @param code 状态码
     */
    public RRException(String message, Integer code) {
        super(message);
        this.code = code;
    }

    /**
     * 接收枚举类型对象
     * @param resultCodeEnum 枚举
     */
    public RRException(ResultCodeEnum resultCodeEnum) {
        super(resultCodeEnum.getMessage());
        this.code = resultCodeEnum.getCode();
    }

    @Override
    public String toString() {
        return "GuliException{" +
                "code=" + code +
                ", message=" + this.getMessage() +
                '}';
    }

}
