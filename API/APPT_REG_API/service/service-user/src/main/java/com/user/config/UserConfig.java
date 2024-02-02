package com.user.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * description
 *
 * @author panyx
 * @since 2023-12-23 13:06:43
 */
@Configuration
@MapperScan(basePackages = {"com.user.dao"})
public class UserConfig {




}
