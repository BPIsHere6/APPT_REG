package com.order.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * description
 *
 * @author panyx
 * @since 2023-12-18 13:50:44
 */
@MapperScan(basePackages = {"com.order"})
@Configuration
public class OrderConfig {

    /**
     * 分页插件
     */
    public PaginationInterceptor paginationInterceptor(){
        return new PaginationInterceptor();
    }
}
