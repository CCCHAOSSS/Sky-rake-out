package com.sky.mapper;

import com.sky.entity.Orders;
import com.sky.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.Map;

/**
 * @author limei
 * @date 2024/3/27 12:32
 * @description 用户mapper
 */

@Mapper
public interface UserMapper {

    /**
     * 根据openId去User表查用户
     * */
    @Select("select * from user where openid = #{openId}")
    User getByOpenId(String openId);

    /**
     * 插入
     * */
    void insert(User user);


    /**
     * 根据id查用户
     * */
    @Select("select * from user where id = #{userId}")
    User getById(Long userId);

    Integer countByMap(Map map);
}
