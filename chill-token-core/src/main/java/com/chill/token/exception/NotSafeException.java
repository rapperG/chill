
package com.chill.token.exception;

import com.chill.token.exception.basic.TokenException;

/**
 * 一个异常：代表会话未能通过二级认证校验
 *
 * @author chill
 * @since 1.0
 */
public class NotSafeException extends TokenException {

    /**
     * 序列化版本号
     */
    private static final long serialVersionUID = 6806129545290130144L;

    /**
     * 异常提示语
     */
    public static final String BE_MESSAGE = "二级认证校验失败";

    /**
     * 账号类型
     */
    private final String loginType;

    /**
     * 未通过校验的 Token 值
     */
    private final Object tokenValue;

    /**
     * 未通过校验的服务
     */
    private final String service;

    /**
     * 获取：账号类型
     *
     * @return /
     */
    public String getLoginType() {
        return loginType;
    }

    /**
     * 获取: 未通过校验的 Token 值
     *
     * @return /
     */
    public Object getTokenValue() {
        return tokenValue;
    }

    /**
     * 获取: 未通过校验的服务
     *
     * @return /
     */
    public Object getService() {
        return service;
    }

    /**
     * 一个异常：代表会话未能通过二级认证校验
     *
     * @param loginType  账号类型
     * @param tokenValue 未通过校验的 Token 值
     * @param service    未通过校验的服务
     */
    public NotSafeException(String loginType, String tokenValue, String service) {
        super(BE_MESSAGE + "：" + service);
        this.tokenValue = tokenValue;
        this.loginType = loginType;
        this.service = service;
    }

}
