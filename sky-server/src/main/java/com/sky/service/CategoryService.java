package com.sky.service;

import com.sky.dto.CategoryDTO;
import com.sky.dto.CategoryPageQueryDTO;
import com.sky.entity.Category;
import com.sky.result.PageResult;

import java.util.List;

/**
 * @author limei
 * @date 2024/3/21 13:16
 * @description 菜品管理service接口
 */
public interface CategoryService {
    /**
     * 新增菜品
     * */
    void save(CategoryDTO categoryDTO);

    PageResult page(CategoryPageQueryDTO categoryPageQueryDTO);

    void deleteById(Long id);

    /**
     * 修改分类
     * */
    void update(CategoryDTO categoryDTO);

    void startOrStop(Integer status, Long id);

    List<Category> list(Integer type);
}
