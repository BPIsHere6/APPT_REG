package com.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.model.user.Patient;

import java.util.List;


/**
 * description
 *
 * @author panyx
 * @since 2023-12-30 12:37:05
 */
public interface PatientService extends IService<Patient> {
    /**
     * 获取就诊人列表
     */
    List<Patient> findAllUserId(Long userId);

    /**
     * 根据id获取就诊人信息
     */
    Patient getPatientId(Long id);
}
