package com.order.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.model.order.PaymentInfo;
import com.model.order.RefundInfo;

/**
 * description
 *
 * @author panyx
 * @since 2024-01-01 12:54:20
 */
public interface RefundInfoService extends IService<RefundInfo> {

    /**
     * 保存退款记录
     */
    RefundInfo saveRefundInfo(PaymentInfo paymentInfo);

}
