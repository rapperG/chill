package com.chill.test.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.chill.test.domain.entity.base.BaseDO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 用户登录表
 *
 * @author chill
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("user")
public class UserDO extends BaseDO {

    private static final long serialVersionUID = 1L;

    /**
     * 第三方用户ID
     */
    private String thirdAccountId;

    /**
     * 账号类型标识，多账号体系时（一个系统多套用户表）用此值区分具体要校验的是哪套用户，比如：login、user、admin
     */
    private String loginType;

    /**
     * 是否禁用：0-未禁用 1=已禁用
     */
    private Integer disable;

    /**
     * 登录用户名
     */
    private String userName;

    /**
     * 登录密码，密文存储
     */
    private String password;

    /**
     * 登录手机号
     */
    private String phone;
}
