package com.order.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.model.order.OrderInfo;
import com.model.order.PaymentInfo;

import java.util.Map;

/**
 * description
 *
 * @author panyx
 * @since 2024-01-01 12:30:56
 */
public interface PaymentService extends IService<PaymentInfo> {

    /**
     * 保存交易记录
     * @param paymentType 支付类型（1：微信 2：支付宝）
     */
    void savePaymentInfo(OrderInfo order, Integer paymentType);

    /**
     * 支付成功
     */
    void paySuccess(String outTradeNo, Integer paymentType, Map<String, String> paramMap);

    /**
     * 获取支付记录
     */
    PaymentInfo getPaymentInfo(Long orderId, Integer paymentType);


}
