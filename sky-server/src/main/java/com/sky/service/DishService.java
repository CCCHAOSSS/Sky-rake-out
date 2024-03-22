package com.sky.service;

import com.sky.dto.DishDTO;
import com.sky.dto.DishPageQueryDTO;
import com.sky.result.PageResult;

import java.util.List;

/**
 * @author limei
 * @date 2024/3/22 15:59
 * @description 管理菜品相关接口
 */
public interface DishService {

    /**
     * 新增菜品和对应的口味
     * */
    public void saveWithFlavor(DishDTO dishDTO);


    /**
     * 菜品分页查询
     * */
    PageResult pageQuery(DishPageQueryDTO dishPageQueryDTO);

    /**
     * 批量删除
     * */
    void deleteBatch(List<Long> ids);
}
