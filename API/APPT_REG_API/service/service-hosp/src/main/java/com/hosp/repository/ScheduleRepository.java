package com.hosp.repository;

import com.model.hosp.Schedule;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * description
 *
 * @author panyx
 * @since 2023-12-21 9:21:53
 */
@Repository
public interface ScheduleRepository extends MongoRepository<Schedule,String> {

    /**
     * 根据医院编号和排班编号查询
     */
    Schedule getScheduleByHoscodeAndHosScheduleId(String hoscode, String hosScheduleId);
}
