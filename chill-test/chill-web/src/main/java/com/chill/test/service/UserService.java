package com.chill.test.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.chill.test.domain.entity.UserDO;
import com.chill.test.domain.dto.UserDTO;

/**
 * <p>
 *
 * @author chill
 * @since 1.0
 */
public interface UserService extends IService<UserDO> {


    /**
     * 根据用户名和密码查找是否存在该用户
     *
     * @param userName 用户名
     * @param password 密码
     * @return chill.test.domain.dto.UserDTO
     * @author chill
     * @createTime 2023/7/7 17:01
     */
    UserDTO getUserByUserNameAndPassword(String userName, String password);

}
