package com.chill.test.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import com.chill.test.common.enums.error.IBzErrorEnum;
import com.chill.test.common.enums.user.DisableEnum;
import com.chill.test.domain.dto.UserDTO;
import com.chill.test.domain.dto.request.UserLoginRequest;
import com.chill.test.domain.dto.request.UserRegisterRequest;
import com.chill.test.domain.entity.UserDO;
import com.chill.test.service.UserService;
import com.chill.token.stp.AuthLoginUtil;
import com.chill.token.util.JsonResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 * 登录控制层
 *
 * @author chill
 * @since 1.0
 */
@RestController
@RequestMapping("/api/user")
public class LoginController {
    @Resource
    private UserService userService;

    @PostMapping("/login")
    public JsonResult<Object> login(@RequestBody UserLoginRequest param) {
        UserDTO user = userService.getUserByUserNameAndPassword(param.getUsername(), null);

        if (ObjectUtil.isNull(user)) {
            return JsonResult.buildError(IBzErrorEnum.USER_NOT_EXISTS.getErrorCode(), IBzErrorEnum.USER_NOT_EXISTS.getErrorMessage());
        }

        if (user.getDisable() == DisableEnum.ALREADY_DISABLED.getCode()) {
            return JsonResult.buildError(IBzErrorEnum.USER_NOT_EXISTS.getErrorCode(), IBzErrorEnum.USER_NOT_EXISTS.getErrorMessage());
        }

//        if (StrUtil.isEmpty(user.getPhone())) {
//            return JsonResult.buildError(IBzErrorEnum.USER_NOT_BIND_PHONE.getErrorCode(), IBzErrorEnum.USER_NOT_BIND_PHONE.getErrorMessage());
//        }

        user = userService.getUserByUserNameAndPassword(param.getUsername(), param.getPassword());

        if (ObjectUtil.isNull(user)) {
            return JsonResult.buildError(IBzErrorEnum.USER_PASSWORD_ERROR.getErrorCode(), IBzErrorEnum.USER_PASSWORD_ERROR.getErrorMessage());
        }

        AuthLoginUtil.login(user.getId());

        return JsonResult.buildSuccess();
    }

    @PostMapping("/register")
    public JsonResult<Object> register(@RequestBody UserRegisterRequest param) {
        UserDTO user = userService.getUserByUserNameAndPassword(param.getUsername(), null);

        if (ObjectUtil.isNotNull(user)) {
            return JsonResult.buildError(IBzErrorEnum.USERNAME_EXISTS.getErrorCode(), IBzErrorEnum.USERNAME_EXISTS.getErrorMessage());
        }

        user = new UserDTO(param);

        userService.save(BeanUtil.copyProperties(user, UserDO.class));

        return JsonResult.buildSuccess();
    }
}
