package com.sky.controller.user;

import com.sky.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

/**
 * @author limei
 * @date 2024/3/26 14:56
 * @description
 */

@RestController("userShopController")
@Slf4j
@Api("店铺相关接口")
@RequestMapping("/user/shop")
public class ShopController {

    public static final String KEY = "SHOP_STATUS";

    @Autowired
    private RedisTemplate redisTemplate;


    /**
     * 查询店铺当前状态
     * */
    @GetMapping("/status")
    @ApiOperation("获取店铺的营业状态")
    public  Result<Integer> getStatus(){
        Integer status = (Integer) redisTemplate.opsForValue().get(KEY);
        //因为redis中value都是string类型 所以取出的时候要强制类型转换（放进去时候 是什么类取出来就是什么类
        log.info("获取店铺状态为：{}", status == 1? "营业中":"打烊中");
        return Result.success(status);
    }
}
