
package com.chill.token.exception;

import com.chill.token.exception.basic.TokenException;

/**
 * 一个异常：代表 Same-Token 校验未通过
 *
 * @author chill
 * @since 1.0
 */
public class SameTokenInvalidException extends TokenException {

    /**
     * 序列化版本号
     */
    private static final long serialVersionUID = 6806129545290130144L;

    /**
     * 一个异常：代表 Same-Token 校验未通过
     *
     * @param message 异常描述
     */
    public SameTokenInvalidException(String message) {
        super(message);
    }

}
