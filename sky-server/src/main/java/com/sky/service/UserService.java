package com.sky.service;

import com.sky.dto.UserLoginDTO;
import com.sky.entity.User;
import org.springframework.stereotype.Service;

/**
 * @author limei
 * @date 2024/3/27 11:12
 * @description userService
 */

public interface UserService {

    /**
     * 微信登录
     * */
    User wxLogin(UserLoginDTO userLoginDTO);
}
