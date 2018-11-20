package com.skm.demo.service.impl;

import com.skm.common.bean.dto.Page;
import com.skm.common.bean.dto.UnifyUser;
import com.skm.demo.domain.OrderBean;
import com.skm.demo.domain.UserBean;
import com.skm.demo.persistence.qo.OrderQo;
import com.skm.demo.persistence.qo.UserQO;
import com.skm.demo.service.OrderService;

public class OrderServiceImpl implements OrderService {
    @Override
    public Page<OrderBean> list(OrderQo qo, int ps, int pn, UnifyUser optUser) {
        return null;
    }

    @Override
    public OrderBean save(OrderBean bean, UnifyUser optUser) {
        return null;
    }
}
