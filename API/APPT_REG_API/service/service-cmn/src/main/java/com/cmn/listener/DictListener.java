package com.cmn.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.cmn.dao.DictDao;
import com.model.cmn.Dict;
import com.vo.cmn.DictEeVo;
import org.springframework.beans.BeanUtils;

/**
 * 导入回调监听器
 *
 * @author panyx
 * @since 2023-12-15 15:04:51
 */
public class DictListener extends AnalysisEventListener<DictEeVo> {

    private final DictDao dictDao;
    public DictListener(DictDao dictDao){
        this.dictDao = dictDao;
    }

    @Override
    public void invoke(DictEeVo dictEeVo, AnalysisContext analysisContext) {
        Dict dict = new Dict();
        BeanUtils.copyProperties(dictEeVo,dict);
        dictDao.insert(dict);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}
