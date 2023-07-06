
package com.chill.token.exception;

/**
 * 一个异常：代表 JSON 转换失败
 *
 * @author chill
 * @since 1.30.0
 */
public class SaJsonConvertException extends SaTokenException {

    /**
     * 序列化版本号
     */
    private static final long serialVersionUID = 6806129545290134144L;

    /**
     * 一个异常：代表 JSON 转换失败
     *
     * @param cause 异常对象
     */
    public SaJsonConvertException(Throwable cause) {
        super(cause);
    }

}
