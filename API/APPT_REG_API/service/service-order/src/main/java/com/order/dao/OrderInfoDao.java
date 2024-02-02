package com.order.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.model.order.OrderInfo;
import com.vo.order.OrderCountQueryVo;
import com.vo.order.OrderCountVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * description
 *
 * @author panyx
 * @since 2023-12-31 20:29:24
 */
public interface OrderInfoDao extends BaseMapper<OrderInfo> {

    List<OrderCountVo> selectOrderCount(@Param("vo") OrderCountQueryVo orderCountQueryVo);

}
