package com.hosp.service;


import com.model.hosp.Department;
import com.vo.hosp.DepartmentQueryVo;
import com.vo.hosp.DepartmentVo;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;

/**
 * description
 *
 * @author panyx
 * @since 2023-12-19 16:38:32
 */
public interface DepartmentService {
    /**
     * 上传科室接口
     */
    void save(Map<String, Object> paramMap);

    /**
     * 查询科室接口
     */
    Page<Department> findPageDepartment(int page, Integer limit, DepartmentQueryVo departmentQueryVo);

    /**
     * 删除科室
     */
    void remove(String hoscode, String depcode);

    /**
     * 查询医院所有科室列表
     */
    List<DepartmentVo> findDeptTree(String hoscode);

    /**
     * 根据科室编号、医院编号查询科室名称
     */
    String getDepName(String hoscode, String depcode);

    /**
     * 获取部门
     */
    Department getDepartment(String hoscode, String depcode);
}
