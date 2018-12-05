package com.skm.demo.service;

import com.skm.common.bean.dto.Page;
import com.skm.common.bean.dto.UnifyUser;
import com.skm.demo.persistence.DTO.OrderAndOrderDetailShowDTO;
import com.skm.demo.persistence.DTO.OrderQueryDTO;
import com.skm.demo.persistence.DTO.OrderSaveDTO;
import com.skm.demo.persistence.DTO.OrderUpdateDTO;
import com.skm.demo.persistence.qo.OrderQo;


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
     * @return 数据结果
     */
    OrderSaveDTO save(OrderSaveDTO orderSaveDTO, UnifyUser optUser);

    /**
     * 回显需要更新的订单数据
     * @param dto 订单号对象
     * @return 回显数据
     */
    OrderUpdateDTO showOrderAndOrderDetail(OrderAndOrderDetailShowDTO dto);

    /**
     * 更新订单信息
     * @param orderUpdateDTO 需要更新的订单数据
     * @return 状态码
     */
    Boolean updateOrder(OrderUpdateDTO orderUpdateDTO, UnifyUser optUser);
}

