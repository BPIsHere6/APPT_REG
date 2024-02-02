package com.msm.controller;

import com.common.result.Result;
import com.msm.service.MsmService;
import com.msm.utils.RandomUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * description
 *
 * @author panyx
 * @since 2023-12-24 12:35:49
 */
@Api(tags = "短信服务")
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/msm")
public class MsmApiController {

    private final MsmService msmService;

    private final RedisTemplate<String, String> redisTemplate;

    @ApiOperation(value = "发送手机验证码")
    @GetMapping("send/{phone}")
    public Result<?> sendCode(@PathVariable("phone") String phone){

        // 从redis获取验证码(key:手机号 value:验证码)
        String code = redisTemplate.opsForValue().get(phone);
        if(StringUtils.isNotBlank(code)){
            return Result.ok();
        }

        // 获取不到验证码,生成验证码,通过短信发送
        code = RandomUtil.getFourBitRandom();
        log.info("验证码:{}",code);

        String[] params = {code, "5"};

        boolean isSend = msmService.send(phone,"1",params);

        // 生成验证码放到redis,设置有效时间
        if(isSend){
            redisTemplate.opsForValue().set(phone, code, 5, TimeUnit.MINUTES);
            return Result.ok();
        } else {
            return Result.fail().message("发送短信失败");
        }

    }


}
