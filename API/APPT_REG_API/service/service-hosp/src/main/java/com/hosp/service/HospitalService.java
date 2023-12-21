package com.hosp.service;

import com.model.hosp.Hospital;

import java.util.Map;

/**
 * description
 *
 * @author panyx
 * @since 2023-12-15 16:18:14
 */
public interface HospitalService {

    /**
     * 上传医院信息
     */
    void save(Map<String,Object> paramMap);

    /**
     * 根据医院编号查询
     */
    Hospital getByHoscode(String hoscode);
}
