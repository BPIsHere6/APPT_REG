package com.user.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.model.user.UserInfo;
import com.vo.user.LoginVo;
import com.vo.user.UserAuthVo;
import com.vo.user.UserInfoQueryVo;

import java.util.Map;

/**
 * description
 *
 * @author panyx
 * @since 2023-12-23 13:10:02
 */
public interface UserInfoService extends IService<UserInfo> {


    /**
     * 用户手机号登录接口
     */
    Map<String, Object> loginUser(LoginVo loginVo);

    /**
     * 根据微信openid获取用户信息
     */
    UserInfo getByOpenid(String openid);

    /**
     * 用户认证
     */
    void userAuth(Long userId, UserAuthVo userAuthVo);

    /**
     * 用户列表（条件查询带分页）
     */
    IPage<UserInfo> selectPage(Page<UserInfo> pageParam, UserInfoQueryVo userInfoQueryVo);


    /**
     * 用户锁定
     * @param status 0：锁定 1：正常
     */
    void lock(Long userId, Integer status);

    /**
     * 用户详情
     */
    Map<String, Object> show(Long userId);

    /**
     * 认证审批
     * @param authStatus 2：通过 -1：不通过
     */
    void approval(Long userId, Integer authStatus);



}
