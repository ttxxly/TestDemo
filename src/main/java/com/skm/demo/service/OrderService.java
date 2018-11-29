package com.skm.demo.service;

import com.skm.common.bean.dto.Page;
import com.skm.common.bean.dto.UnifyUser;
import com.skm.demo.domain.OrderBean;
import com.skm.demo.domain.OrderDetailBean;
import com.skm.demo.persistence.DTO.OrderQueryDTO;
import com.skm.demo.persistence.DTO.OrderSaveDTO;
import com.skm.demo.persistence.DTO.OrderUpdateDTO;
import com.skm.demo.persistence.qo.OrderQo;
import com.skm.demo.web.vo.OrderSaveVo;
import com.skm.demo.web.vo.OrderTemp;
import org.springframework.stereotype.Service;

import java.util.List;


public interface OrderService {
    /**
     * 分页获取
     *
     * @param qo 查询参数
     * @param ps 页大小
     * @param pn 页码
     * @param optUser 操作用户
     * @return 分页的订单对象结果集
     */
    Page<OrderQueryDTO> list(OrderQo qo, int ps, int pn, UnifyUser optUser);

    /**
     * 保存订单
     * @param orderSaveDTO 数据转换对象
     * @param optUser 操作者
     * @return 订单对象
     */
    OrderBean save(OrderSaveDTO orderSaveDTO, UnifyUser optUser);

    /**
     * 回显需要更新的订单数据
     * @param no 订单号
     * @return 回显数据
     */
    OrderUpdateDTO showOrderAndOrderDetail(String no);

    /**
     * 更新订单信息
     * @param orderSaveVo 需要更新的订单数据
     * @return 状态码：0表示失败，1表示成功
     */
    Integer updateOrder(OrderSaveVo orderSaveVo);
}

