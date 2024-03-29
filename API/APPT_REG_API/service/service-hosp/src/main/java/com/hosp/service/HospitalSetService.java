package com.hosp.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.model.hosp.HospitalSet;
import com.vo.order.SignInfoVo;

/**
 * 医院设置
 *
 * @author panyx
 * @since 2023-12-15 9:11:11
 */
public interface HospitalSetService extends IService<HospitalSet> {

    /**
     * 根据传递过来的医院编码查询数据库，查询签名
     */
    String getSignKey(String hoscode);

    /**
     * 获取医院签名信息
     */
    SignInfoVo getSignInfoVo(String hoscode);



}
