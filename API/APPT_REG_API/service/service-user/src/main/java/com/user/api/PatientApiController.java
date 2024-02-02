package com.user.api;

import com.common.result.Result;
import com.common.utils.AuthContextHolder;
import com.model.user.Patient;
import com.user.service.PatientService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * description
 *
 * @author panyx
 * @since 2023-12-30 12:37:05
 */

@Api(value = "就诊人管理接口")
@AllArgsConstructor
@RestController
@RequestMapping("/api/user/patient")
public class PatientApiController {

    private final PatientService patientService;

    @ApiOperation(value = "获取就诊人列表")
    @GetMapping("auth/findAll")
    public Result<?> findAll(HttpServletRequest request) {
        //获取当前登录用户id
        Long userId = AuthContextHolder.getUserId(request);
        List<Patient> list = patientService.findAllUserId(userId);
        return Result.ok(list);
    }

    @ApiOperation(value = "添加就诊人")
    @PostMapping("auth/save")
    public Result<?> savePatient(@RequestBody Patient patient, HttpServletRequest request) {
        //获取当前登录用户id
        Long userId = AuthContextHolder.getUserId(request);
        patient.setUserId(userId);
        patientService.save(patient);
        return Result.ok();
    }

    @ApiOperation(value = "根据id获取就诊人信息")
    @GetMapping("auth/get/{id}")
    public Result<?> getPatient(@PathVariable Long id) {
        Patient patient = patientService.getPatientId(id);
        return Result.ok(patient);
    }

    @ApiOperation(value = "修改就诊人")
    @PostMapping("auth/update")
    public Result<?> updatePatient(@RequestBody Patient patient) {
        patientService.updateById(patient);
        return Result.ok();
    }

    @ApiOperation(value = "删除就诊人")
    @DeleteMapping("auth/remove/{id}")
    public Result<?> removePatient(@PathVariable Long id) {
        patientService.removeById(id);
        return Result.ok();
    }

    @ApiOperation(value = "获取就诊人")
    @GetMapping("inner/get/{id}")
    public Patient getPatientOrder(
            @ApiParam(name = "id", value = "就诊人id", required = true)
            @PathVariable("id") Long id) {
        return patientService.getById(id);
    }

}
