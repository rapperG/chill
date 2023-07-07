
package com.chill.token.exception;

import com.chill.token.exception.basic.TokenException;

/**
 * 一个异常：代表 JSON 转换失败
 *
 * @author chill
 * @since 1.0
 */
public class JsonConvertException extends TokenException {

    /**
     * 序列化版本号
     */
    private static final long serialVersionUID = 6806129545290134144L;

    /**
     * 一个异常：代表 JSON 转换失败
     *
     * @param cause 异常对象
     */
    public JsonConvertException(Throwable cause) {
        super(cause);
    }

}
