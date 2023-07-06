
package com.chill.token.exception;

/**
 * 一个异常：代表停止路由匹配，进入 Controller （框架内部专属异常，一般情况下开发者无需关注）
 *
 * @author chill
 * @since 1.20.0
 */
public class StopMatchException extends SaTokenException {

    /**
     * 序列化版本号
     */
    private static final long serialVersionUID = 6806129545290130143L;

    /**
     * 构造
     */
    public StopMatchException() {
        super("stop match");
    }

}
