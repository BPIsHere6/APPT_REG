package com.cmn.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.model.cmn.Dict;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * description
 *
 * @author panyx
 * @since 2023-12-15 14:02:19
 */
public interface DictService extends IService<Dict> {

    /**
     * 根据数据id查询子数据列表
     * @param id id
     * @return 数据列表
     */
    List<Dict> findChildData(Long id);

    /**
     * 导出
     */
    void exportData(HttpServletResponse response);

    /**
     * 导入
     */
    void importDictData(MultipartFile file);

    /**
     * 根据dictCode和value查询
     */
    String getDictName(String dictCode, String value);

    /**
     * 根据dictCode获取下级结点
     */
    List<Dict> findByDictCode(String dictCode);
}
