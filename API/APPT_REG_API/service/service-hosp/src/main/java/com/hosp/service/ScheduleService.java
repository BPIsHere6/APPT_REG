package com.hosp.service;

import com.model.hosp.Schedule;
import com.vo.hosp.ScheduleQueryVo;
import org.springframework.data.domain.Page;

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
}
