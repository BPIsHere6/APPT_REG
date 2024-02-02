package com.hosp.service;

import com.model.hosp.Hospital;
import com.vo.hosp.HospitalQueryVo;
import org.springframework.data.domain.Page;

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

    /**
     * 医院列表(条件查询分页)
     */
    Page<Hospital> selectHospPage(Integer page, Integer limit, HospitalQueryVo hospitalQueryVo);

    /**
     * 更新医院上线状态
     */
    void updateStatus(String id, Integer status);

    /**
     * 医院详情信息
     */
    Map<String, Object> getHospById(String id);

    /**
     * 获取医院名称
     */
    String getHospName(String hoscode);

    /**
     * 根据医院名称查询
     */
    Object findByHosname(String hosname);

    /**
     * 根据医院编号查询预约挂号详情信息
     */
    Map<String, Object> findHospDetail(String hoscode);
}
