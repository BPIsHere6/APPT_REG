package com.hosp.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.hosp.repository.DepartmentRepository;
import com.hosp.service.DepartmentService;
import com.model.hosp.Department;
import com.vo.hosp.DepartmentQueryVo;
import com.vo.hosp.DepartmentVo;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * description
 *
 * @author panyx
 * @since 2023-12-19 16:38:56
 */
@Service
@AllArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;


    @Override
    public void save(Map<String, Object> paramMap) {
        // 将Map转换为department对象
        Department department = JSONObject.parseObject(JSONObject.toJSONString(paramMap),Department.class);

        Department departmentExist = departmentRepository
                .getDepartmentByHoscodeAndDepcode(department.getHoscode(),department.getDepcode());

        if(null != departmentExist){
            BeanUtils.copyProperties(department,departmentExist);
            departmentExist.setUpdateTime(new Date());
            departmentExist.setIsDeleted(0);
            departmentRepository.save(departmentExist);
        }else{
            department.setCreateTime(new Date());
            department.setUpdateTime(new Date());
            department.setIsDeleted(0);
            departmentRepository.save(department);
        }
    }

    @Override
    public Page<Department> findPageDepartment(int page, Integer limit, DepartmentQueryVo departmentQueryVo) {
        // 创建Pageable对象，设置当前页和每页记录数
        PageRequest pageable = PageRequest.of(page-1, limit);

        // 创建Example对象
        Department department = new Department();
        BeanUtils.copyProperties(departmentQueryVo,department);
        department.setIsDeleted(0);
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING)
                .withIgnoreCase(true);

        Example<Department> example = Example.of(department, matcher);

        return departmentRepository.findAll(example, pageable);
    }

    @Override
    public void remove(String hoscode, String depcode) {
        Department department = departmentRepository.getDepartmentByHoscodeAndDepcode(hoscode, depcode);
        if(null != department) {
            // departmentRepository.delete(department);
            departmentRepository.deleteById(department.getId());
        }

    }

    @Override
    public List<DepartmentVo> findDeptTree(String hoscode) {
        // 创建一个list集合用于最终数据封装
        ArrayList<DepartmentVo> result = new ArrayList<>();

        // 根据医院编号，查询所有科室信息
        Department departmentQuery = new Department();
        departmentQuery.setHoscode(hoscode);
        Example<Department> example = Example.of(departmentQuery);

        List<Department> departmentList = departmentRepository.findAll(example);

        // 根据大科室编号bigCode分组，获取子科室
        Map<String, List<Department>> departmentMap = departmentList.stream()
                .collect(Collectors.groupingBy(Department::getBigcode));

        // 遍历departmentMap
        for(Map.Entry<String,List<Department>> entry: departmentMap.entrySet()){
            // 大科室编号
            String bigCode = entry.getKey();

            // 得到大科室编号对应得数据
            List<Department> value = entry.getValue();

            // 封装大科室
            DepartmentVo departmentVo = new DepartmentVo();
            departmentVo.setDepcode(bigCode);
            departmentVo.setDepname(value.get(0).getBigname());

            // 封装小科室
            List<DepartmentVo> children = new ArrayList<>();
            for(Department department : departmentList){
                DepartmentVo departmentSunVo = new DepartmentVo();
                departmentSunVo.setDepcode(department.getDepcode());
                departmentSunVo.setDepname(department.getDepname());

                children.add(departmentSunVo);
            }
            // 把小科室list集合放到大科室children中
            departmentVo.setChildren(children);
            result.add(departmentVo);
        }
        return result;
    }

    @Override
    public String getDepName(String hoscode, String depcode) {
        Department department = departmentRepository.getDepartmentByHoscodeAndDepcode(hoscode, depcode);
        if(department != null){
            return department.getDepname();
        }
        return null;
    }

    @Override
    public Department getDepartment(String hoscode, String depcode) {
        return departmentRepository.getDepartmentByHoscodeAndDepcode(hoscode,depcode);
    }
}
