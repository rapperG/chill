package com.chill.test.domain.dto;

import com.chill.test.common.enums.user.DisableEnum;
import com.chill.test.domain.dto.request.UserRegisterRequest;
import lombok.Data;

import java.util.Date;

/**
 * <p>
 *
 * @author chill
 * @since 1.0
 */
@Data
public class UserDTO {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Long id;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

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

    public UserDTO() {
    }

    public UserDTO(UserRegisterRequest param) {
        this.userName = param.getUsername();
        this.password = param.getPassword();
        this.disable = DisableEnum.NOT_DISABLED.getCode();
        this.loginType = "login";

        this.createTime = new Date();
        this.updateTime = new Date();
    }
}
