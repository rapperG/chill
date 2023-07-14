package com.chill.test.common.enums.error;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * <p>
 *
 * @author chill
 * @since 1.0
 */
@Getter
@AllArgsConstructor
public enum IBzErrorEnum {

    /**
     * >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> 用户模块 10开头 <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
     */
    USER_NOT_EXISTS("1001", "无该用户！请先去注册！"),
    USER_PASSWORD_ERROR("1002", "用户登录密码错误！请检查并重试！如忘记密码请点击下方忘记密码进行修改！"),
    USERNAME_EXISTS("1003", "用户名已存在！请修改并重试！"),
    USER_DISABLED("1004", "用户已被禁用！无法登录！"),
    USER_NOT_BIND_PHONE("1005", "用户未绑定手机号！请先绑定手机号！"),


    ;

    private final String errorCode;

    private final String errorMessage;
}
