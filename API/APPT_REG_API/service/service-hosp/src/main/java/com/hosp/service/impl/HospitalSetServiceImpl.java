package com.hosp.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.common.exception.RRException;
import com.common.result.ResultCodeEnum;
import com.hosp.dao.HospitalSetDao;
import com.hosp.service.HospitalSetService;
import com.model.hosp.HospitalSet;
import com.vo.order.SignInfoVo;
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

    @Override
    public SignInfoVo getSignInfoVo(String hoscode) {
        QueryWrapper<HospitalSet> wrapper = new QueryWrapper<>();
        wrapper.eq("hoscode",hoscode);
        HospitalSet hospitalSet = baseMapper.selectOne(wrapper);
        if(null == hospitalSet) {
            throw new RRException(ResultCodeEnum.HOSPITAL_OPEN);
        }
        SignInfoVo signInfoVo = new SignInfoVo();
        signInfoVo.setApiUrl(hospitalSet.getApiUrl());
        signInfoVo.setSignKey(hospitalSet.getSignKey());
        return signInfoVo;
    }

}
