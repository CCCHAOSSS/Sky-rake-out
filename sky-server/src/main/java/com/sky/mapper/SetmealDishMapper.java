package com.sky.mapper;

import com.sky.entity.SetmealDish;
import org.apache.ibatis.annotations.Delete;
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

    /**
     * 保存套餐和菜品关系
     * */
    void insertBatch(List<SetmealDish> setmealDishes);

    /**
     * 根据套餐，删除套餐菜品
     * */
    @Delete("delete from setmeal_dish where setmeal_id = #{setmealId}")
    void deleteBySetmealId(Long setmealId);

    /**
     * 根据id获取setmeal
     * */
    @Select("select * from setmeal_dish where setmeal_id=#{setmealId}")
    List<SetmealDish> getBySetmealId(Long setmealId);

}
