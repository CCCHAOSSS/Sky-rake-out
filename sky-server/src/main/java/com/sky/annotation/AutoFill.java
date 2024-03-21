package com.sky.annotation;

import com.sky.enumeration.OperationType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author limei
 * @date 2024/3/21 16:05
 * @description 用于标识某个方法需要进行功能字段的自动填充
 */


@Target(ElementType.METHOD)     //指定该注解只能加在方法上
@Retention(RetentionPolicy.RUNTIME)
public @interface AutoFill {

    //数据操作类型    UPDATE INSERT
    OperationType value();
}
