
package com.chill.token.exception;

import com.chill.token.stp.StpUtil;

/**
 * 一个异常：代表会话未能通过角色认证校验
 *
 * @author chill
 * @since 1.10.0
 */
public class NotRoleException extends SaTokenException {

    /**
     * 序列化版本号
     */
    private static final long serialVersionUID = 8243974276159004739L;

    /**
     * 角色标识
     */
    private final String role;

    /**
     * @return 获得角色标识
     */
    public String getRole() {
        return role;
    }

    /**
     * 账号类型
     */
    private final String loginType;

    /**
     * 获得账号类型
     *
     * @return 账号类型
     */
    public String getLoginType() {
        return loginType;
    }

    public NotRoleException(String role) {
        this(role, StpUtil.stpLogic.loginType);
    }

    public NotRoleException(String role, String loginType) {
        super("无此角色：" + role);
        this.role = role;
        this.loginType = loginType;
    }

}
