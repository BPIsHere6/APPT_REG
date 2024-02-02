package com.order.service;

import java.util.Map;

/**
 * description
 *
 * @author panyx
 * @since 2024-01-01 12:34:56
 */
public interface WeixinService {

    /**
     * 根据订单号下单，生成支付链接
     */
    Map<String,Object> createNative(Long orderId);

    /**
     * 根据订单号去微信第三方查询支付状态
     */
    Map<String,String> queryPayStatus(Long orderId, String paymentType);

    /***
     * 退款
     */
    Boolean refund(Long orderId);


}
