package com.sky.controller.admin;

import com.github.pagehelper.PageHelper;
import com.sky.dto.DishDTO;
import com.sky.dto.DishPageQueryDTO;
import com.sky.entity.Dish;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.DishService;
import com.sky.vo.DishVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author limei
 * @date 2024/3/22 15:56
 * @description 菜品管理
 */

@RestController
@RequestMapping("/admin/dish")
@Api("菜品管理接口")
@Slf4j
public class DishController {

    @Autowired
    private DishService dishService;


    @PostMapping
    @ApiOperation("新增菜品")
    public Result save(@RequestBody DishDTO dishDTO){
        log.info("新增菜品:{}", dishDTO);
        dishService.saveWithFlavor(dishDTO);
        return Result.success();

    }

    @GetMapping("/page")
    @ApiOperation("菜品分页")
    public Result<PageResult> pageQuery(DishPageQueryDTO dishPageQueryDTO){
        log.info("菜品分页查询：{}", dishPageQueryDTO);

        PageResult pageResult = dishService.pageQuery(dishPageQueryDTO);
        return Result.success(pageResult);
    }


    @RequestMapping
    @ApiOperation("菜品批量删除")
    public Result delete(@RequestParam List<Long> ids){
        log.info("菜品删除：{}", ids);
        dishService.deleteBatch(ids);
        return Result.success();

    }

    @GetMapping("/{id}")
    @ApiOperation("根据id查菜品")
    public Result<DishVO> getById(@PathVariable Long id){
        log.info("菜品查询id:{}", id);
        DishVO dishVO = dishService.getByIdWithFlavor(id);
        return Result.success(dishVO);
    }

    @PutMapping
    @ApiOperation("修改菜品")
    public Result update(@RequestBody DishDTO dishDTO){
        log.info("修改菜品：{}",dishDTO);
        //修改菜品
        dishService.updateWithFlavor(dishDTO);
        //和其对应的口味
        return Result.success();
    }

    @GetMapping("/list")
    @ApiOperation("根据分类id查菜品")
    public Result<List<Dish>> list(Long categoryId){
        log.info("查询的菜品的分类id：{}",categoryId);
        List<Dish> list = dishService.list(categoryId);
        return Result.success(list);
    }

    // TODO 菜品停售启售

    @PostMapping("/status/{status}")
    @ApiOperation("菜品停售启售")
    public Result<String> startOrStop(@PathVariable Integer status, Long id){
        dishService.startOrStop(status,id);
        return Result.success();
    }
}
