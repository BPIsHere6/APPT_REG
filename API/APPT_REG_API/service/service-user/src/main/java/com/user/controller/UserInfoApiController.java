package com.user.controller;

import com.common.result.Result;
import com.common.utils.AuthContextHolder;
import com.model.user.UserInfo;
import com.user.service.UserInfoService;
import com.vo.user.LoginVo;
import com.vo.user.UserAuthVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * description
 *
 * @author panyx
 * @since 2023-12-23 13:08:18
 */
@Api(tags = "用户管理")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserInfoApiController {


    private final UserInfoService userInfoService;


    @ApiOperation(value = "用户手机号登录接口")
    @PostMapping("login")
    public Result<?> login(@RequestBody LoginVo loginVo){
        Map<String,Object> info = userInfoService.loginUser(loginVo);
        return Result.ok(info);
    }


    @ApiOperation(value = "用户认证")
    @PostMapping("auth/userAuth")
    public Result<?> userAuth(@RequestBody UserAuthVo userAuthVo, HttpServletRequest request) {
        //传递两个参数，第一个参数用户id，第二个参数认证数据vo对象
        userInfoService.userAuth(AuthContextHolder.getUserId(request),userAuthVo);
        return Result.ok();
    }


    @ApiOperation(value = "获取用户id信息")
    @GetMapping("auth/getUserInfo")
    public Result<?> getUserInfo(HttpServletRequest request) {
        Long userId = AuthContextHolder.getUserId(request);
        UserInfo userInfo = userInfoService.getById(userId);
        return Result.ok(userInfo);
    }


}
