package com.sky.mapper;

import com.github.pagehelper.Page;
import com.sky.annotation.AutoFill;
import com.sky.dto.SetmealPageQueryDTO;
import com.sky.entity.Setmeal;
import com.sky.entity.SetmealDish;
import com.sky.enumeration.OperationType;
import com.sky.vo.SetmealVO;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

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

    /**
     * 新增套餐
     * */
    @AutoFill(value = OperationType.INSERT)
    void insert(Setmeal setmeal);

    /**
     * 套餐分页查询
     * */
    Page<SetmealVO> pageQuery(SetmealPageQueryDTO setmealPageQueryDTO);

    /**
     * 根据id查询套餐
     * */
    @Select("select * from setmeal where id = #{id}")
    Setmeal getById(Long id);

    @Delete("delete from setmeal where id = #{id}")
    void deleteById(Long setmealId);

    @AutoFill(value = OperationType.UPDATE)
    void update(Setmeal setmeal);
}
