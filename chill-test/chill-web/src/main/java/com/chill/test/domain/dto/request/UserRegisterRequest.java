package com.chill.test.domain.dto.request;

import lombok.Data;

/**
 * <p>
 *
 * @author chill
 * @since 1.0
 */
@Data
public class UserRegisterRequest {

    /**
     * 登录用户名
     */
    private String username;

    /**
     * 登录密码，密文存储
     */
    private String password;

    /**
     * 登录手机号
     */
    private String phone;

    /**
     * 登录手机验证码
     */
    private String verifyCode;
}
