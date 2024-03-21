package com.sky.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * @author limei
 * @date 2024/3/21 13:18
 * @description DishMapper
 */

@Mapper
public interface DishMapper {

    /**
     * 根据id查菜品数量
     * @param categoryId
     * */
    @Select("select count(id) from dish where category_id = #{categoryId}")
    Integer countByCategoryId(Long categoryId);
}
