package com.msm.service;

import com.vo.msm.MsmVo;

/**
 * description
 *
 * @author panyx
 * @since 2023-12-24 12:36:51
 */
public interface MsmService {

    /**
     * 发送手机验证码
     * @param phone 手机号
     * @param templateId 短信模板,默认为 1
     * @param params 短信模板中需要用到的参数：随机验证码，验证码有效期
     */
    boolean send(String phone, String templateId, String[] params);

    /**
     * mq使用发送短信
     */
    boolean send(MsmVo msmVo);
}
