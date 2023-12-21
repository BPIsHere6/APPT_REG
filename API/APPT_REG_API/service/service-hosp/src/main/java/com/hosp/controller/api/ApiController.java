package com.hosp.controller.api;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.common.exception.RRException;
import com.common.helper.HttpRequestHelper;
import com.common.result.Result;
import com.common.result.ResultCodeEnum;
import com.common.utils.MD5;
import com.hosp.service.DepartmentService;
import com.hosp.service.HospitalService;
import com.hosp.service.HospitalSetService;
import com.hosp.service.ScheduleService;
import com.model.hosp.Department;
import com.model.hosp.Hospital;
import com.model.hosp.Schedule;
import com.vo.hosp.DepartmentQueryVo;
import com.vo.hosp.ScheduleQueryVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * description
 *
 * @author panyx
 * @since 2023-12-15 16:21:28
 */

@Api(tags = "医院管理API接口")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/hosp")
public class ApiController {

    private final HospitalService hospitalService;
    private final HospitalSetService hospitalSetService;
    private final DepartmentService departmentService;

    private final ScheduleService scheduleService;

    @ApiOperation(value = "上传医院")
    @PostMapping("saveHospital")
    public Result<?> saveHospital(HttpServletRequest request){
        Map<String,Object> paramMap = HttpRequestHelper.switchMap(request.getParameterMap());
        checkSign(paramMap);
        // 传输中的图片转换异常处理
        String logoData = (String)paramMap.get("logoData");
        logoData = logoData.replaceAll(" ","+");
        paramMap.put("logoData",logoData);

        hospitalService.save(paramMap);
        return Result.ok();
    }

    @ApiOperation(value = "查询医院")
    @PostMapping("hospital/show")
    public Result<Hospital> getHospital(HttpServletRequest request){
        Map<String,Object> paramMap = HttpRequestHelper.switchMap(request.getParameterMap());
        boolean b = checkSign(paramMap);
        String hoscode = "";
        if(b){
             hoscode = (String) paramMap.get("hoscode");
        }
        // 根据医院编号进行查询
        return Result.ok(hospitalService.getByHoscode(hoscode));
    }

    @ApiOperation(value = "上传科室")
    @PostMapping("saveDepartment")
    public Result<?> getDepartment(HttpServletRequest request){
        Map<String,Object> paramMap = HttpRequestHelper.switchMap(request.getParameterMap());
        checkSign(paramMap);
        departmentService.save(paramMap);
        return Result.ok();
    }

    @ApiOperation(value = "查询科室")
    @PostMapping("department/list")
    public Result<?> findDepartment(HttpServletRequest request){
        Map<String,Object> paramMap = HttpRequestHelper.switchMap(request.getParameterMap());
        int page = StringUtils.isNotBlank((CharSequence) paramMap.get("page")) ? Integer.parseInt((String) paramMap.get("page")) : 1;
        Integer limit = StringUtils.isNotBlank((CharSequence) paramMap.get("limit")) ? Integer.parseInt((String) paramMap.get("limit")) : 1;
        boolean b = checkSign(paramMap);
        String hoscode = "";
        if(b){
            hoscode = (String) paramMap.get("hoscode");
        }
        DepartmentQueryVo departmentQueryVo = new DepartmentQueryVo();
        departmentQueryVo.setHoscode(hoscode);

        Page<Department> pageModel = departmentService.findPageDepartment(page,limit,departmentQueryVo);

        return Result.ok(pageModel);
    }

    @ApiOperation(value = "删除科室")
    @PostMapping("department/remove")
    public Result<?> removeDepartment(HttpServletRequest request){
        Map<String,Object> paramMap = HttpRequestHelper.switchMap(request.getParameterMap());
        checkSign(paramMap);
        String hoscode = (String) paramMap.get("hoscode");
        String depcode = (String) paramMap.get("depcode");
        departmentService.remove(hoscode,depcode);
        return Result.ok();
    }

    @ApiOperation(value = "上传排班")
    @PostMapping("saveSchedule")
    public Result<?> saveSchedule(HttpServletRequest request){
        Map<String,Object> paramMap = HttpRequestHelper.switchMap(request.getParameterMap());
        checkSign(paramMap);
        scheduleService.save(paramMap);
        return Result.ok();
    }

    @ApiOperation(value = "查询排班")
    @PostMapping("schedule/list")
    public Result<?> findSchedule(HttpServletRequest request){
        Map<String,Object> paramMap = HttpRequestHelper.switchMap(request.getParameterMap());
        int page = StringUtils.isNotBlank((CharSequence) paramMap.get("page")) ? Integer.parseInt((String) paramMap.get("page")) : 1;
        Integer limit = StringUtils.isNotBlank((CharSequence) paramMap.get("limit")) ? Integer.parseInt((String) paramMap.get("limit")) : 1;
        String hoscode = (String) paramMap.get("hoscode");
        String depcode = (String) paramMap.get("depcode");

        checkSign(paramMap);

        ScheduleQueryVo scheduleQueryVo = new ScheduleQueryVo();
        scheduleQueryVo.setHoscode(hoscode);
        scheduleQueryVo.setDepcode(depcode);

        Page<Schedule> pageModel = scheduleService.findPageSchedule(page,limit,scheduleQueryVo);

        return Result.ok(pageModel);
    }

    @ApiOperation(value = "删除排班")
    @PostMapping("schedule/remove")
    public Result<?> removeSchedule(HttpServletRequest request){
        Map<String,Object> paramMap = HttpRequestHelper.switchMap(request.getParameterMap());
        String hoscode = (String) paramMap.get("hoscode");
        String hosScheduleId = (String) paramMap.get("hosScheduleId");
        checkSign(paramMap);
        scheduleService.remove(hoscode,hosScheduleId);
        return Result.ok();

    }

    public boolean checkSign(Map<String,Object> paramMap){
        // 1. 获取医院系统传递过来的签名,签名经过MD5加密
        String hospSign = (String)paramMap.get("sign");
        // 2. 根据传递的医院编号查询数据库比较签名
        String hoscode = (String) paramMap.get("hoscode");
        String signKey =  hospitalSetService.getSignKey(hoscode);
        // 3. 把数据库查出来的签名进行MD5加密
        String singKeyMd5 = MD5.encrypt(signKey);
        // 4. 判断签名是否一致
        if(!hospSign.equals(singKeyMd5)){
            throw new RRException(ResultCodeEnum.SIGN_ERROR);
        }
        return true;
    }
}
