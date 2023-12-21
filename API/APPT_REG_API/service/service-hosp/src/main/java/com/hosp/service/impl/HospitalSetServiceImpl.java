package com.hosp.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hosp.dao.HospitalSetDao;
import com.hosp.service.HospitalSetService;
import com.model.hosp.HospitalSet;
import org.springframework.stereotype.Service;

/**
 * 医院设置Impl
 *
 * @author panyx
 * @since 2023-12-15 9:12:11
 */
@Service
public class HospitalSetServiceImpl extends ServiceImpl<HospitalSetDao, HospitalSet> implements HospitalSetService {
    @Override
    public String getSignKey(String hoscode) {
        HospitalSet hospitalSet = baseMapper.selectOne(new LambdaQueryWrapper<HospitalSet>().eq(HospitalSet::getHoscode, hoscode));
        return hospitalSet.getSignKey();
    }
}
