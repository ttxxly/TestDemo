package com.skm.demo.service;

import com.skm.common.bean.dto.Page;
import com.skm.common.bean.dto.UnifyUser;
import com.skm.demo.domain.OrderBean;
import com.skm.demo.domain.OrderDetailBean;
import com.skm.demo.domain.UserBean;
import com.skm.demo.persistence.DTO.OrderDTO;
import com.skm.demo.persistence.qo.OrderQo;
import com.skm.demo.persistence.qo.UserQO;
import com.skm.demo.web.vo.OrderTemp;

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
    Page<OrderBean> list(OrderQo qo, int ps, int pn, UnifyUser optUser);

    /**
     * 保存订单
     * @param orderDTO 数据转换对象
     * @param optUser 操作者
     * @return 订单对象
     */
    OrderBean save(OrderDTO orderDTO, UnifyUser optUser);

    /**
     * 获取指定订单号下的 商品种类数量和总数量
     */
    OrderTemp getProductTypeNumAndProductNumByNo(OrderDetailBean orderDetailBean);
}
