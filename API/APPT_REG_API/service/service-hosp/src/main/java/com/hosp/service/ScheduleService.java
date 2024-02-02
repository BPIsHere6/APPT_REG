package com.hosp.service;

import com.model.hosp.Schedule;
import com.vo.hosp.ScheduleOrderVo;
import com.vo.hosp.ScheduleQueryVo;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;

/**
 * description
 *
 * @author panyx
 * @since 2023-12-21 9:27:25
 */
public interface ScheduleService {
    /**
     * 上传排班
     */
    void save(Map<String, Object> paramMap);

    /**
     * 查询排班
     */
    Page<Schedule> findPageSchedule(int page, Integer limit, ScheduleQueryVo scheduleQueryVo);

    /**
     * 删除排班
     */
    void remove(String hoscode, String hosScheduleId);

    /**
     * 查询排班规则数据
     */
    Map<String, Object> getRuleSchedule(Integer page, Integer limit, String hoscode, String depcode);

    /**
     * 查询排班详情信息
     */
    List<Schedule> getDetailSchedule(String hoscode, String depcode, String workDate);

    /**
     * 获取排班可预约日期数据
     */
    Map<String, Object> getBookingScheduleRule(int page, int limit, String hoscode, String depcode);


    /**
     * 根据id获取排班
     */
    Schedule getById(String id);


    /**
     * 根据排班id获取预约下单数据
     */
    ScheduleOrderVo getScheduleOrderVo(String scheduleId);

    /**
     * 获取排班id获取排班数据
     */
    Schedule getScheduleId(String scheduleId);

    /**
     * 修改排班
     */
    void update(Schedule schedule);


}
