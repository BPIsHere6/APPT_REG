package com.cmn.service.Impl;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cmn.dao.DictDao;
import com.cmn.listener.DictListener;
import com.cmn.service.DictService;
import com.model.cmn.Dict;
import com.sun.deploy.net.URLEncoder;
import com.vo.cmn.DictEeVo;
import org.springframework.beans.BeanUtils;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * description
 *
 * @author panyx
 * @since 2023-12-15 14:04:52
 */
@Service
public class DictServiceImpl extends ServiceImpl<DictDao, Dict> implements DictService {

    @Cacheable(value = "dict", keyGenerator = "keyGenerator")
    @Override
    public List<Dict> findChildData(Long id) {
       List<Dict> dictList = baseMapper.selectList(new LambdaQueryWrapper<Dict>()
               .eq(Dict::getParentId,id));
       // 向list集合每个dict对象中设置hasChildren
       for(Dict dict : dictList){
           Long dictId = dict.getId();
           boolean isChild = this.isChildren(dictId);
           dict.setHasChildren(isChild);
       }
       return dictList;
    }

    @Override
    public void exportData(HttpServletResponse response) {
        try {
            response.setContentType("application/vnd.ms-excel");
            response.setCharacterEncoding("utf-8");
            // 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
            String fileName = URLEncoder.encode("数据字典", "UTF-8");
            response.setHeader("Content-disposition", "attachment;filename="+ fileName + ".xlsx");

            List<Dict> dictList = baseMapper.selectList(null);
            List<DictEeVo> dictVoList = new ArrayList<>(dictList.size());

            for(Dict dict : dictList) {
                DictEeVo dictVo = new DictEeVo();
                BeanUtils.copyProperties(dict, dictVo, DictEeVo.class);
                dictVoList.add(dictVo);
            }

            EasyExcel.write(response.getOutputStream(), DictEeVo.class).sheet("数据字典").doWrite(dictVoList);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @CacheEvict(value = "dict",allEntries = true)
    @Override
    public void importDictData(MultipartFile file) {
        try{
            EasyExcel.read(file.getInputStream(),DictEeVo.class,
                    new DictListener(baseMapper)).sheet().doRead();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    // 判断id下是否有子节点
    private boolean isChildren(Long id){
        Integer count = baseMapper.selectCount(new LambdaQueryWrapper<Dict>()
                .eq(Dict::getParentId, id));
        return count > 0;
    }


}
