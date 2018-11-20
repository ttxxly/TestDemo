package com.skm.demo.service;

import com.skm.common.bean.dto.Page;
import com.skm.common.bean.dto.UnifyUser;
import com.skm.demo.domain.OrderBean;
import com.skm.demo.domain.UserBean;
import com.skm.demo.persistence.qo.OrderQo;
import com.skm.demo.persistence.qo.UserQO;

public interface OrderService {
    /**
     * 分页获取
     *
     * @param qo 查询参数
     * @param ps 页大小
     * @param pn 页码
     * @param optUser 操作用户
     * @return
     */
    Page<OrderBean> list(OrderQo qo, int ps, int pn, UnifyUser optUser);

    /**
     * 保存订单
     * @param bean 订单实例
     * @param optUser 操作者
     * @return
     */
    OrderBean save(OrderBean bean, UnifyUser optUser);
}
