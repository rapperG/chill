
package com.chill.token.util;

import com.sun.org.slf4j.internal.Logger;
import com.sun.org.slf4j.internal.LoggerFactory;

import java.io.Serializable;

/**
 * 对请求接口返回 Json 格式数据的简易封装。
 *
 * <p>
 * 所有预留字段：<br>
 * code = 状态码 <br>
 * msg  = 描述信息 <br>
 * data = 携带对象 <br>
 * </p>
 *
 * @author chill
 * @since 1.0
 */
public class JsonResult<T> implements Serializable {
    private static final Logger log = LoggerFactory.getLogger(JsonResult.class);
    private static final long serialVersionUID = 1L;
    public static final boolean REQUEST_SUCCESS = true;
    public static final boolean REQUEST_FAIL = false;
    public static final String DEFAULT_ERROR_CODE = "500";
    public static final String DEFAULT_SUCCESS_CODE = "200";
    /**
     * 成功与否
     */
    private Boolean success;

    /**
     * 数据
     */
    private T data;

    /**
     * 响应编码 成功：200 失败：500或其他
     */
    private String code;

    /**
     * 消息体 通常是业务异常消息
     */
    private String msg;

    private JsonResult() {
    }

    public JsonResult(Boolean success, T data, String code, String message) {
        this.success = success;
        this.data = data;
        this.code = code;
        this.msg = message;
    }

    public static <T> JsonResult<T> buildSuccess() {
        return new JsonResult<>(REQUEST_SUCCESS, null, DEFAULT_SUCCESS_CODE, null);
    }

    public static <T> JsonResult<T> buildSuccess(T data) {
        return new JsonResult<>(REQUEST_SUCCESS, data, DEFAULT_SUCCESS_CODE, null);
    }

    public static <T> JsonResult<T> buildError(String errorMsg) {
        return new JsonResult<>(REQUEST_FAIL, null, DEFAULT_ERROR_CODE, errorMsg);
    }

    public static <T> JsonResult<T> buildError(String errorCode, String errorMsg) {
        return new JsonResult<>(REQUEST_FAIL, null, errorCode, errorMsg);
    }


    public Boolean getSuccess() {
        return this.success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public T getData() {
        return this.data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return this.msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "{"
            + "\"success\": " + this.getSuccess()
            + "\"code\": " + this.getCode()
            + ", \"msg\": " + transValue(this.getMsg())
            + ", \"data\": " + transValue(this.getData())
            + "}";
    }

    /**
     * 转换 value 值：
     * 如果 value 值属于 String 类型，则在前后补上引号
     * 如果 value 值属于其它类型，则原样返回
     *
     * @param value 具体要操作的值
     * @return 转换后的值
     */
    private String transValue(Object value) {
        if (value == null) {
            return null;
        }
        if (value instanceof String) {
            return "\"" + value + "\"";
        }
        return String.valueOf(value);
    }

}
