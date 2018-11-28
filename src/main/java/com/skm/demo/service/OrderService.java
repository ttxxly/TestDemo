package com.skm.demo.service;

import com.skm.common.bean.dto.Page;
import com.skm.common.bean.dto.UnifyUser;
import com.skm.demo.domain.OrderBean;
import com.skm.demo.domain.OrderDetailBean;
import com.skm.demo.persistence.DTO.OrderQueryDTO;
import com.skm.demo.persistence.DTO.OrderSaveDTO;
import com.skm.demo.persistence.qo.OrderQo;
import com.skm.demo.web.vo.OrderTemp;

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
}
