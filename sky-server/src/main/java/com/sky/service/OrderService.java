package com.sky.service;

import com.sky.dto.*;
import com.sky.result.PageResult;
import com.sky.vo.OrderPaymentVO;
import com.sky.vo.OrderStatisticsVO;
import com.sky.vo.OrderSubmitVO;
import com.sky.vo.OrderVO;

/**
 * @author limei
 * @date 2024/3/28 16:38
 * @description 用户端下单管理
 */

public interface OrderService {
    OrderSubmitVO submitOrder(OrdersSubmitDTO ordersSubmitDTO);

    /**
     * 订单支付
     * @param ordersPaymentDTO
     * @return
     */
    OrderPaymentVO payment(OrdersPaymentDTO ordersPaymentDTO) throws Exception;

    /**
     * 支付成功，修改订单状态
     * @param outTradeNo
     */
    void paySuccess(String outTradeNo);

    /**
     * 订单分页查询
     * */
    PageResult page(int page, int pageSize, Integer status);

    /**
     * 根据id查询订单详情
     * */
    OrderVO details(Long id);

    /**
     * 用户取消订单
     * */
    void userCancelById(Long id) throws Exception;

    /**
     * 再来一单
     * */
    void repetition(Long id);

    /**
     * 订单搜索
     * */
    PageResult conditionSearch(OrdersPageQueryDTO ordersPageQueryDTO);

    /**
     * 根据查询各个状态订单数量
     * */
    OrderStatisticsVO statistics();

    /**
     * 接单
     * */
    void confirm(OrdersConfirmDTO ordersConfirmDTO);

    /**
     * 拒单
     * */
    void rejection(OrdersRejectionDTO ordersRejectionDTO);

    void cancel(OrdersCancelDTO ordersCancelDTO) throws Exception;

    void delivery(Long id);

    void complete(Long id);
}
