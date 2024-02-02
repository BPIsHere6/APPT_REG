package com.cmn.client;

import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * description
 *
 * @author panyx
 * @since 2023-12-21 16:20:31
 */
@FeignClient("service-cmn")
@Repository
public interface DictFeignClient {

    @ApiOperation(value = "根据dictCode和value查询")
    @GetMapping("/admin/cmn/dict/getName/{dictCode}/{value}")
    String getName(@PathVariable("dictCode") String dictCode, @PathVariable("value") String value);


    @ApiOperation(value = "根据value查询")
    @GetMapping("/admin/cmn/dict/getName/{value}")
    String getName(@PathVariable("value") String value);

}
