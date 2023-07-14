package com.chill.test.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chill.test.domain.entity.UserDO;
import com.chill.test.domain.dto.UserDTO;
import com.chill.test.mapper.UserMapper;
import com.chill.test.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * <p>
 *
 * @author chill
 * @since 1.0
 */
@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, UserDO> implements UserService {
    @Override
    public UserDTO getUserByUserNameAndPassword(String username, String password) {

        return BeanUtil.copyProperties(this.getOne(Wrappers.lambdaQuery(UserDO.class).eq(StrUtil.isNotEmpty(username), UserDO::getUserName, username).eq(StrUtil.isNotEmpty(password), UserDO::getPassword, password)), UserDTO.class);
    }
}
