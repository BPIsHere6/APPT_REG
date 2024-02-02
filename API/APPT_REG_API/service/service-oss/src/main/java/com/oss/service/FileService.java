package com.oss.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * description
 *
 * @author panyx
 * @since 2023-12-30 10:33:27
 */
public interface FileService {

    /**
     * 文件上传
     */
    String upload(MultipartFile file);
}
