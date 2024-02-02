package com.order.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.model.order.OrderInfo;
import com.vo.order.OrderCountQueryVo;
import com.vo.order.OrderQueryVo;

import java.util.Map;

/**
 * description
 *
 * @author panyx
 * @since 2023-12-31 20:30:49
 */
public interface OrderService extends IService<OrderInfo> {

    /**
     * 保存订单
     */
    Long saveOrder(String scheduleId,Long patientId);


    /**
     * 分页列表
     */
    IPage<OrderInfo> selectPage(Page<OrderInfo> pageParam, OrderQueryVo orderQueryVo);

    /**
     * 获取订单详情
     */
    OrderInfo getOrderInfo(String id);

    /**
     * 订单详情
     */
    Map<String,Object> show(Long orderId);

    /**
     * 取消订单
     */
    Boolean cancelOrder(Long orderId);

    /**
     * 就诊提醒
     */
    void patientTips();

    /**
     * 订单统计
     */
    Map<String, Object> getCountMap(OrderCountQueryVo orderCountQueryVo);


}
