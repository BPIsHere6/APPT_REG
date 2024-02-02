package com.oss.controller;

import com.common.result.Result;
import com.oss.service.FileService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * description
 *
 * @author panyx
 * @since 2023-12-30 10:30:14
 */
@Api(tags = "文件上传")
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/oss/file")
public class FileApiController {

    private final FileService fileService;

    @ApiOperation(value = "上传文件到阿里云oss")
    @PostMapping("fileUpload")
    public Result<?> fileUpload(MultipartFile file){
        // 获取上传文件
        String url = fileService.upload(file);
        return Result.ok(url);
    }

}
