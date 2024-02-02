package com.user.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.common.result.Result;
import com.model.user.UserInfo;
import com.user.service.UserInfoService;
import com.vo.user.UserInfoQueryVo;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * description
 *
 * @author panyx
 * @since 2023-12-30 12:58:25
 */
@RestController
@AllArgsConstructor
@RequestMapping("/admin/user")
public class UserController {

    private final UserInfoService userInfoService;

    @ApiOperation(value = "用户列表（条件查询带分页）")
    @GetMapping("{page}/{limit}")
    public Result<?> list(@PathVariable Long page,
                          @PathVariable Long limit,
                          UserInfoQueryVo userInfoQueryVo) {
        Page<UserInfo> pageParam = new Page<>(page,limit);
        IPage<UserInfo> pageModel =
                userInfoService.selectPage(pageParam,userInfoQueryVo);
        return Result.ok(pageModel);
    }

    @ApiOperation(value = "用户锁定")
    @GetMapping("lock/{userId}/{status}")
    public Result<?> lock(
            @PathVariable("userId") Long userId,
            @PathVariable("status") Integer status){
        userInfoService.lock(userId, status);
        return Result.ok();
    }

    @ApiOperation(value = "用户详情")
    @GetMapping("show/{userId}")
    public Result<?> show(@PathVariable Long userId) {
        Map<String,Object> map = userInfoService.show(userId);
        return Result.ok(map);
    }

    @ApiOperation(value = "认证审批")
    @GetMapping("approval/{userId}/{authStatus}")
    public Result<?> approval(@PathVariable Long userId,@PathVariable Integer authStatus) {
        userInfoService.approval(userId,authStatus);
        return Result.ok();
    }



}

