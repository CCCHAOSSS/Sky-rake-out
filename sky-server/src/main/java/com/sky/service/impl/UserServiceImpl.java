package com.sky.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sky.dto.UserLoginDTO;
import com.sky.entity.User;
import com.sky.mapper.UserMapper;
import com.sky.properties.WeChatProperties;
import com.sky.service.UserService;
import com.sky.utils.HttpClientUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * @author limei
 * @date 2024/3/27 12:19
 * @description
 */


@Service
@Slf4j
public class UserServiceImpl implements UserService {


    public static final String WX_LOGIN = "https://api.weixin.qq.com/sns/jscode2session";

    @Autowired
    private WeChatProperties weChatProperties;

    @Autowired
    private UserMapper userMapper;

    /**
     * 微信登录
     * */
    @Override
    public User wxLogin(UserLoginDTO userLoginDTO) {
        String openid = getOpenId(userLoginDTO.getCode());

        // openid是否为空，空则为登录失败
//        if(openid == null){
//            throw new LoginFailedException(MessageConstant.LOGIN_FAILED);
//        }

        //判断当前用户是否为外卖小程序的新用户，是则自动完成注册登录
        User user = userMapper.getByOpenId(openid);
        if(user == null){
            user = User.builder()
                    .openid(openid)
                    .createTime(LocalDateTime.now())
                    .build();
            userMapper.insert(user);
        }

        return user;
    }


    /**
     * 调用微信接口服务，获取微信用户的openid
     * */
    private String getOpenId(String code){
        //调用微信接口服务 获得当前用户openid
        Map<String, String> map = new HashMap<>();
        map.put("appid", weChatProperties.getAppid());
        map.put("secret", weChatProperties.getSecret());
        map.put("js_code", code);
        map.put("grant_type", "authorization_code");

        String json = HttpClientUtil.doGet(WX_LOGIN, map);

        JSONObject jsonObject = JSON.parseObject(json);
        String openid = jsonObject.getString("openid");

        return openid;
    }
}
