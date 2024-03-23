package com.sky.controller.admin;

import com.sky.dto.SetmealDTO;
import com.sky.dto.SetmealPageQueryDTO;
import com.sky.entity.Setmeal;
import com.sky.entity.SetmealDish;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.SetmealService;
import com.sky.vo.SetmealVO;
import io.swagger.annotations.Api;
import io.swagger.models.auth.In;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author limei
 * @date 2024/3/23 8:57
 * @description 套餐管理
 */
@RestController
@RequestMapping("admin/setmeal")
@Api("套餐相关接口")
public class SetmealContronller {

    @Autowired
    private SetmealService setmealService;

    /**
     * 新增套餐
     * */
    @PostMapping
    public Result save(@RequestBody SetmealDTO setmealDTO){
        setmealService.saveWithDish(setmealDTO);
        return Result.success();
    }

    /**
     * 套餐分页查询
     * */
    @GetMapping("/page")
    public Result<PageResult> page(SetmealPageQueryDTO setmealPageQueryDTO){
        PageResult pageResult = setmealService.pageQuery(setmealPageQueryDTO);
        return Result.success(pageResult);
    }

    /**
     * 删除套餐
     * */
    @DeleteMapping()
    public Result delete(@RequestParam List<Long> ids){
        setmealService.deleteBatch(ids);
        return Result.success();
    }

    /**
     * 根据id查套餐，用于修改时回显数据
     * */
    @GetMapping("/{id}")
    public Result<SetmealVO> getById(@PathVariable Long id){
        SetmealVO setmealVO = setmealService.getByIdWithDish(id);
        return  Result.success(setmealVO);
    }


    /**
     * 修改套餐
     * */
    @PutMapping
    public Result update(@RequestBody SetmealDTO setmealDTO){
        setmealService.update(setmealDTO);
        return Result.success();
    }

    /**
     * 启售、停售套餐
     * */
    @PostMapping("/status/{status}")
    public Result startOrStop(@PathVariable Integer status, Long id){
        setmealService.startOrStop(status, id);
        return Result.success();

    }
}
