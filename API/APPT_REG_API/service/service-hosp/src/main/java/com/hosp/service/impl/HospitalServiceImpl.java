package com.hosp.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.cmn.client.DictFeignClient;
import com.hosp.repository.HospitalRepository;
import com.hosp.service.HospitalService;
import com.model.hosp.Hospital;
import com.vo.hosp.HospitalQueryVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * description
 *
 * @author panyx
 * @since 2023-12-15 16:18:48
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class HospitalServiceImpl implements HospitalService {

    private final HospitalRepository hospitalRepository;

    private final DictFeignClient dictFeignClient;

    @Override
    public void save(Map<String, Object> paramMap) {
        log.info(JSONObject.toJSONString(paramMap));
        Hospital hospital = JSONObject.parseObject(JSONObject.toJSONString(paramMap), Hospital.class);

        // 判断是否存在,存在进行修改,不存在就添加
        Hospital targetHospital = hospitalRepository.getHospitalByHoscode(hospital.getHoscode());
        if(null != targetHospital){
            hospital.setStatus(targetHospital.getStatus());
            hospital.setCreateTime(targetHospital.getCreateTime());
        }else{
            // 0:未上线 1:已上线
            hospital.setStatus(0);
            hospital.setCreateTime(new Date());
        }
        hospital.setUpdateTime(new Date());
        hospital.setIsDeleted(0);
        hospitalRepository.save(hospital);
    }

    @Override
    public Hospital getByHoscode(String hoscode) {
        return hospitalRepository.getHospitalByHoscode(hoscode);
    }

    @Override
    public Page<Hospital> selectHospPage(Integer page, Integer limit, HospitalQueryVo hospitalQueryVo) {
        Pageable pageable = PageRequest.of(page-1,limit);
        // 创建条件匹配器
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING)
                .withIgnoreCase(true);

        Hospital hospital = new Hospital();
        BeanUtils.copyProperties(hospitalQueryVo,hospital);

        Example<Hospital> example = Example.of(hospital,matcher);
        Page<Hospital> pages = hospitalRepository.findAll(example, pageable);

        // 获取查询list集合，遍历进行医院等级封装
        pages.getContent().forEach(this::setHospitalHosType);

        return pages;
    }

    @Override
    public void updateStatus(String id, Integer status) {
        // 根据id先查询医院信息
        Hospital hospital = hospitalRepository.findById(id).get();
        hospital.setStatus(status);
        hospital.setUpdateTime(new Date());
        hospitalRepository.save(hospital);
    }

    @Override
    public Map<String, Object> getHospById(String id) {

        Hospital hospital = this.setHospitalHosType(hospitalRepository.findById(id).get());
        HashMap<String, Object> result = new HashMap<>();

        result.put("bookingRule",hospital.getBookingRule());
        // 不需要重复返回
        hospital.setBookingRule(null);
        result.put("hospital",hospital);

        return result;
    }

    @Override
    public String getHospName(String hoscode) {
      Hospital hospital = hospitalRepository.getHospitalByHoscode(hoscode);
      if(hospital != null){
          return hospital.getHosname();
      }
      return null;
    }

    @Override
    public Object findByHosname(String hosname) {
        return hospitalRepository.findHospitalByHosnameLike(hosname);
    }

    @Override
    public Map<String, Object> findHospDetail(String hoscode) {
        Map<String, Object> result = new HashMap<>();

        Hospital hospital = this.setHospitalHosType(this.getByHoscode(hoscode));
        result.put("bookingRule",hospital.getBookingRule());
        hospital.setBookingRule(null);
        result.put("hospital",hospital);

        return result;
    }

    private Hospital setHospitalHosType(Hospital hospital) {
        // 根据dictCode和value获取医院等级名称
        String hostypeString = dictFeignClient.getName("Hostype",hospital.getHostype());
        // 查询 省 市 地区
        String provinceString = dictFeignClient.getName(hospital.getProvinceCode());
        String cityString = dictFeignClient.getName(hospital.getCityCode());
        String districtString = dictFeignClient.getName(hospital.getDistrictCode());

        hospital.getParam().put("hostypeString", hostypeString);
        hospital.getParam().put("fullAddress", provinceString+cityString+districtString);
        return hospital;
    }
}
