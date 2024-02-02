package com.hosp.controller;

import com.common.result.Result;
import com.hosp.service.DepartmentService;
import com.vo.hosp.DepartmentVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * description
 *
 * @author panyx
 * @since 2023-12-21 20:18:23
 */
@Api(tags = "科室管理")
@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/hosp/department")
public class DepartmentController {

    private final DepartmentService departmentService;

    @ApiOperation(value = "查询医院所有科室列表")
    @GetMapping("getDeptList/{hoscode}")
    public Result<?> getDeptList(@PathVariable String hoscode){
       List<DepartmentVo> list = departmentService.findDeptTree(hoscode);
       return Result.ok(list);
    }
}
