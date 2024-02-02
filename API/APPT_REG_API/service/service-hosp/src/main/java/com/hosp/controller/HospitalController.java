package com.hosp.controller;

import com.hosp.service.HospitalService;
import com.model.hosp.Hospital;
import com.vo.hosp.HospitalQueryVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import com.common.result.Result;

import java.util.Map;

/**
 * description
 *
 * @author panyx
 * @since 2023-12-21 15:09:12
 */
@Api(tags = "医院管理")
@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/hosp/hospital")
public class HospitalController {

    private final HospitalService hospitalService;

    @ApiOperation(value = "医院列表(条件查询分页)")
    @GetMapping("list/{page}/{limit}")
    public Result<?> listHosp(@PathVariable("page") Integer page,
                              @PathVariable("limit") Integer limit,
                              HospitalQueryVo hospitalQueryVo){
       Page<Hospital> pageModel = hospitalService.selectHospPage(page,limit,hospitalQueryVo);
       return Result.ok(pageModel);
    }

    @ApiOperation(value = "更新医院上线状态")
    @GetMapping("updateHospStatus/{id}/{status}")
    public Result<?> updateHospStatus(@PathVariable("id") String id,
                                      @PathVariable("status") Integer status){
        hospitalService.updateStatus(id,status);
        return Result.ok();
    }

    @ApiOperation(value = "医院详情信息")
    @GetMapping("showHospDetail/{id}")
    public Result<?> showHospDetail(@PathVariable String id){
        Map<String, Object> map = hospitalService.getHospById(id);
        return Result.ok(map);
    }
}
