package com.sky.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sky.constant.MessageConstant;
import com.sky.constant.StatusConstant;
import com.sky.dto.DishDTO;
import com.sky.dto.DishPageQueryDTO;
import com.sky.entity.Dish;
import com.sky.entity.DishFlavor;
import com.sky.exception.DeletionNotAllowedException;
import com.sky.mapper.DishFlavorMapper;
import com.sky.mapper.DishMapper;
import com.sky.mapper.SetmealDishMapper;
import com.sky.result.PageResult;
import com.sky.service.DishService;
import com.sky.vo.DishVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author limei
 * @date 2024/3/22 15:59
 * @description 实现类
 */

@Service
public class DishServiceImpl implements DishService {

    @Autowired
    private DishMapper dishMapper;

    @Autowired
    private DishFlavorMapper dishFlavorMapper;

    @Autowired
    private SetmealDishMapper setmealDishMapper;

    /**
     * 新增菜品和对应的口味
     * 涉及到两个表dish和dish_flavor
     * 要保证这个方法的事务性（加事务注解
     * */
    @Override
    @Transactional
    public void saveWithFlavor(DishDTO dishDTO) {
        //向dish表插入一条数据
        Dish dish = new Dish();
        BeanUtils.copyProperties(dishDTO, dish);
        dishMapper.insert(dish);

        Long dishId = dish.getId();     //获取当前插入的dish的主键值
        //但是当前是获取不到的， 还要从xml文件中代码写一下useGeneratedKeys
        //最总insert语句执行后插入的这个dish的id可以正常得到（MP好像可以自动得到


        //向dish_flavor插入多条数据（因为可以有多个口味
        List<DishFlavor> flavors = dishDTO.getFlavors();
        if(flavors != null && flavors.size()>0){
            flavors.forEach(dishFlavor -> {
                dishFlavor.setDishId(dishId);
            });
            //向口味表插入n条数据
            dishFlavorMapper.insertBatch(flavors);

        }
    }

    /**
     * 菜品分页查询
     * */
    @Override
    public PageResult pageQuery(DishPageQueryDTO dishPageQueryDTO) {

        PageHelper.startPage(dishPageQueryDTO.getPage(), dishPageQueryDTO.getPageSize());
        Page<DishVO> page = dishMapper.pageQuery(dishPageQueryDTO);
        return new PageResult(page.getTotal(), page.getResult());
    }


    /**
     * 菜品批量删除
     * 被套餐关联的菜品不能删除，起售中的菜品也不能删除
     * 删除时候菜品关联的口味也要删除
     * */
    @Override
    @Transactional   // 涉及多个表 加上事务注解
    public void deleteBatch(List<Long> ids) {
        // 启售中的菜品不能删除
        for (Long id : ids){
            Dish dish = dishMapper.getById(id);
            if(dish.getStatus() == StatusConstant.ENABLE){
                throw new DeletionNotAllowedException(MessageConstant.DISH_ON_SALE);
            }
        }

        //被套餐关联的菜品也不能删除
        List<Long> setmealIds = setmealDishMapper.getSetmealIdsByDishId(ids);
        if(setmealIds != null && setmealIds.size() > 0){
            throw new DeletionNotAllowedException(MessageConstant.DISH_BE_RELATED_BY_SETMEAL);
        }

        //删除菜品表中的菜品数据dish
//        for(Long id : ids){
////            dishMapper.deleteById(id);
////            //删除菜品关联的口味数据dish_flavor
////            dishFlavorMapper.deleteById(id);
////        }
        // 根据菜品id批量删除菜品（dish）和批量删除关联的口味数据dish_flavor
        dishMapper.deleteByIds(ids);
        dishFlavorMapper.deleteByDishIds(ids);


    }

    /**
     * 根据id查菜品id和口味
     * dish 和 dish_Flavor
     * */
    @Override
    public DishVO getByIdWithFlavor(Long id) {

        //根据菜品id查
        Dish dish = dishMapper.getById(id);

        //根据口味查
        List<DishFlavor> dishFlavors = dishFlavorMapper.getByDishId(id);

        DishVO dishVO = new DishVO();

        BeanUtils.copyProperties(dish, dishVO);
        dishVO.setFlavors(dishFlavors);

        return dishVO;
    }

    /**
     * 根据id修改菜品和对应口味数据
     * */
    @Override
    @Transactional
    public void updateWithFlavor(DishDTO dishDTO) {
        //dish
        Dish dish = new Dish();
        BeanUtils.copyProperties(dishDTO, dish);

        //直接传dishDTO也可以，但是这里不需要用到dishDTO中的口味数据
        //但是从设计层面来说更合理
        dishMapper.update(dish);

        // 和 dish_flavor，删除原有的再插入修改的
        dishFlavorMapper.deleteByDishId(dishDTO.getId());

        //重新插入新口味数据
        List<DishFlavor> flavors = dishDTO.getFlavors();
        if(flavors != null && flavors.size()>0){
            flavors.forEach(dishFlavor -> {
                dishFlavor.setDishId(dishDTO.getId());
            });
            dishFlavorMapper.insertBatch(flavors);
        }


    }
}
