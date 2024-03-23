package com.sky.mapper;

import com.github.pagehelper.Page;
import com.sky.annotation.AutoFill;
import com.sky.dto.DishPageQueryDTO;
import com.sky.entity.Dish;
import com.sky.enumeration.OperationType;
import com.sky.vo.DishVO;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

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

    @AutoFill(value = OperationType.INSERT)
    void insert(Dish dish);

    /**
     * 菜品分页查询
     * */
    Page<DishVO> pageQuery(DishPageQueryDTO dishPageQueryDTO);

    @Select("select * from dish where id = #{id}")
    Dish getById(Long id);

    /**
     * 根据主键删除菜品
     * */
    @Delete("delete from dish where id=#{id}")
    void deleteById(Long id);
/**
 * 根据菜品ids批量删除
 * */
    void deleteByIds(List<Long> ids);

    /**
     * 根据id修改dish
     * */
    @AutoFill(value = OperationType.UPDATE)
    void update(Dish dish);
}
