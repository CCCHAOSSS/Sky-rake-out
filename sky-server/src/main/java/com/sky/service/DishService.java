package com.sky.service;

import com.sky.dto.DishDTO;
import com.sky.dto.DishPageQueryDTO;
import com.sky.entity.Dish;
import com.sky.result.PageResult;
import com.sky.vo.DishVO;

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

    /**
     * 根据id查菜品和对应口味数据
    * */
    DishVO getByIdWithFlavor(Long id);

    /**
     * 根据id修改菜品和对应口味数据
     * */
    void updateWithFlavor(DishDTO dishDTO);

    /**
     * 根据分类id查询菜品
     * */
    List<Dish> list(Long categoryId);

    /**
     * 菜品停售启售
     * */
    void startOrStop(Integer status, Long id);
}
