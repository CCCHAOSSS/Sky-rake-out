package com.sky.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author limei
 * @date 2024/3/22 20:03
 * @description
 */
@Mapper
public interface SetmealDishMapper {

    /**
     * 根据菜品id查其对应套餐id
     * 看是否关联套餐
     * */

    //select setmealId from setmeal_dish where dishid in (1,2,3)  ===> dishIds
    List<Long> getSetmealIdsByDishId(List<Long> dishIds);
}
