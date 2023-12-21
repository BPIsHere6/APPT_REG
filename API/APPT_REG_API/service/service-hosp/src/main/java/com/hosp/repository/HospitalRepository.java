package com.hosp.repository;

import com.model.hosp.Hospital;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * description
 *
 * @author panyx
 * @since 2023-12-15 16:16:47
 */
@Repository
public interface HospitalRepository extends MongoRepository<Hospital,String> {

    // mongodb不需要我们自己写实现方法，只需要我们按照Spring Data命名规范就会帮我自动实习方法
    Hospital getHospitalByHoscode(String hoscode);

}
