package com.hosp.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.model.hosp.HospitalSet;

/**
 * 医院设置
 *
 * @author panyx
 * @since 2023-12-15 9:11:11
 */
public interface HospitalSetService extends IService<HospitalSet> {
    /**
     * 根据传递过来的医院编码查询数据库，查询签名
     * @param hoscode 医院编码
     * @return 签名
     */
    String getSignKey(String hoscode);
}
