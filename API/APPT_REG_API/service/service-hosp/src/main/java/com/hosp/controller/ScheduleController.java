package com.hosp.controller;

import com.common.result.Result;
import com.hosp.service.ScheduleService;
import com.model.hosp.Schedule;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * description
 *
 * @author panyx
 * @since 2023-12-21 20:51:54
 */
@Api(tags = "排班管理")
@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/hosp/schedule")
public class ScheduleController {

    private final ScheduleService scheduleService;

    @ApiOperation(value = "查询排班规则数据")
    @GetMapping("getScheduleRule/{page}/{limit}/{hoscode}/{depcode}")
    public Result<?> getScheduleRule(@PathVariable("page") Integer page,
                                     @PathVariable("limit") Integer limit,
                                     @PathVariable("hoscode") String hoscode,
                                     @PathVariable("depcode") String depcode){
        Map<String,Object> map = scheduleService.getRuleSchedule(page,limit,hoscode,depcode);
        return Result.ok(map);
    }

    @ApiOperation(value = "查询排班详情信息")
    @GetMapping("getScheduleDetail/{hoscode}/{depcode}/{workDate}")
    public Result<?> getScheduleDetail(@PathVariable("hoscode") String hoscode,
                                     @PathVariable("depcode") String depcode,
                                     @PathVariable("workDate") String workDate){
        List<Schedule> list = scheduleService.getDetailSchedule(hoscode,depcode,workDate);
        return Result.ok(list);
    }


}
