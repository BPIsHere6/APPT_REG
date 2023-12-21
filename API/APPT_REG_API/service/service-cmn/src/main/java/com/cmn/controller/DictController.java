package com.cmn.controller;

import com.baomidou.mybatisplus.extension.api.R;
import com.cmn.service.DictService;
import com.common.result.Result;
import com.model.cmn.Dict;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 数据字典管理层
 *
 * @author panyx
 * @since 2023-12-15 14:16:23
 */
@Api(tags = "数据字典接口")
@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/cmn/dict")
@CrossOrigin
public class DictController {

    private final DictService dictService;

    @ApiOperation(value = "根据数据id查询子数据列表")
    @GetMapping("findChildData/{id}")
    public Result<?> findChildData(@PathVariable Long id){
        List<Dict> list = dictService.findChildData(id);
        return Result.ok(list);
    }

    @ApiOperation(value = "导出")
    @GetMapping(value = "/exportData")
    public void exportData(HttpServletResponse response){
        dictService.exportData(response);
    }

    @ApiOperation(value = "导入")
    @PostMapping("importData")
    public Result<?> importData(MultipartFile file){
        dictService.importDictData(file);
        return Result.ok();
    }
}
