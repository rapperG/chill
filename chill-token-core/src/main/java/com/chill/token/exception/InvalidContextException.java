
package com.chill.token.exception;

/**
 * 一个异常：代表框架未能获取有效的上下文
 *
 * @author chill
 * @since 1.33.0
 */
public class InvalidContextException extends SaTokenException {

    /**
     * 序列化版本号
     */
    private static final long serialVersionUID = 6806129545290130144L;

    /**
     * 一个异常：代表框架未能获取有效的上下文
     *
     * @param message 异常描述
     */
    public InvalidContextException(String message) {
        super(message);
    }

}
