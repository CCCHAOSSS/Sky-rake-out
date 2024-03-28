package com.sky.service;

import com.sky.dto.ShoppingCartDTO;
import com.sky.entity.ShoppingCart;

import java.util.List;

/**
 * @author limei
 * @date 2024/3/28 13:10
 * @description 购物车接口
 */
public interface ShoppingCartService {

    void addShoppingCart(ShoppingCartDTO shoppingCartDTO);

    List<ShoppingCart> showShoppingCart();

    void cleanShoppingCart();

    void subShoppingCart(ShoppingCartDTO shoppingCartDTO);
}
