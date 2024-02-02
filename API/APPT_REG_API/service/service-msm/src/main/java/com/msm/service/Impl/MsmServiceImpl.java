package com.msm.service.Impl;

import com.cloopen.rest.sdk.BodyType;
import com.cloopen.rest.sdk.CCPRestSmsSDK;
import com.msm.service.MsmService;
import com.msm.utils.MsmProperties;
import com.vo.msm.MsmVo;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Set;

/**
 * description
 *
 * @author panyx
 * @since 2023-12-24 12:37:04
 */
@Slf4j
@Service
@AllArgsConstructor
public class MsmServiceImpl implements MsmService {
    
    @Override
    public boolean send(String phone, String templateId, String[] params) {

        // 判断手机号是否为空
        if(!StringUtils.isNotBlank(phone)){
            return false;
        }

        //创建sdk客户端对象
        CCPRestSmsSDK sdk = new CCPRestSmsSDK();
        //初始化客户端对象
        sdk.init(MsmProperties.SERVER_IP, MsmProperties.SERVER_PORT);
        //属性赋值
        sdk.setAccount(MsmProperties.ACCOUNT_ID, MsmProperties.ACCOUNT_TOKEN);
        sdk.setAppId(MsmProperties.APP_ID);
        sdk.setBodyType(BodyType.Type_JSON);
        Object resultMSg = null;
        try {
            HashMap<String, Object> result = sdk.sendTemplateSMS(phone, templateId, params);
            Object resultCd = result.get("statusCode");
            if("000000".equals(resultCd)){
                //正常返回输出data包体信息(map)
                HashMap<String,Object> data = (HashMap<String, Object>) result.get("data");
                Set<String> keySet = data.keySet();
                for(String key:keySet){
                    Object object = data.get(key);
                    System.out.println(key +" = "+object);
                }
                log.info("data{}",data);
                return true;
            }else{
                //异常返回输出错误码和错误信息
                log.info("错误码>>" + resultCd +" 错误信息>> "+ resultMSg);
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean send(MsmVo msmVo) {
        if(!StringUtils.isEmpty(msmVo.getPhone())) {
            String  str = ":\n基本信息:" + msmVo.getParam().get("title") +
                    "\n医院服务费:" + msmVo.getParam().get("amount") +
                    "\n安排日期:" + msmVo.getParam().get("reserveDate") +
                    "\n就诊人姓名:" + msmVo.getParam().get("name") +
                    "\n退号时间:" + msmVo.getParam().get("quitTime");
            String[] params = {str};
            return this.send(msmVo.getPhone(),"3",params);
        }
        return false;
    }

}
