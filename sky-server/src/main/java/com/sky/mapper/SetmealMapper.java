package com.sky.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * @author limei
 * @date 2024/3/21 13:18
 * @description
 */
@Mapper
public interface SetmealMapper {

    /**
     * 根据分类id查套餐数量
     * @param categoryId
     * */
    @Select("select count(id) from setmeal where category_id = #{categoryId} ")
    Integer countByCategoryId(Long categoryId);
}
