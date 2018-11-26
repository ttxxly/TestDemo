package com.skm.demo.service.impl;

import com.skm.common.bean.dto.Page;
import com.skm.common.bean.dto.UnifyUser;
import com.skm.common.mybatis.config.ITransactional;
import com.skm.common.mybatis.dao.BaseDao;
import com.skm.common.mybatis.dto.BatchInsertParameter;
import com.skm.demo.domain.OrderBean;
import com.skm.demo.domain.OrderDetailBean;
import com.skm.demo.persistence.DTO.OrderDTO;
import com.skm.demo.persistence.dao.OrderDao;
import com.skm.demo.persistence.qo.OrderQo;
import com.skm.demo.service.OrderService;
import com.skm.demo.web.vo.OrderTemp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service("myOrderServiceImpl")
public class OrderServiceImpl implements OrderService {

    private OrderDao dao;

    @Autowired
    public OrderServiceImpl(OrderDao dao) {
        this.dao = dao;
    }

    @Override
    public Page<OrderBean> list(OrderQo qo, int ps, int pn, UnifyUser optUser) {
        Page<OrderBean> page = new Page<>();
        page.setConditions(qo);
        page.setPn(pn);
        page.setPs(ps);
        dao.dynamicSelectPage(page);
        return page;
    }

    @Override
    @ITransactional
    public OrderBean save(OrderDTO orderDTO, UnifyUser optUser) {

        OrderBean orderBean = orderDTO.getOrderBean();
        orderBean.setEntryDt(new Date());
        orderBean.setEntryId(optUser.getId());
        orderBean.setEntryName(optUser.getRealName());
        orderBean.setUpdateDt(new Date());
        orderBean.setUpdateId(optUser.getId());
        orderBean.setUpdateName(optUser.getRealName());
        dao.saveOrder(orderBean);

        List<OrderDetailBean> orderDetailBeans = orderDTO.getOrderDetailBeans();
        for (OrderDetailBean orderDetailBean: orderDetailBeans) {
            orderDetailBean.setOrderNo(orderBean.getNo());
            orderDetailBean.setEntryDt(new Date());
            orderDetailBean.setEntryId(optUser.getId());
            orderDetailBean.setEntryName(optUser.getRealName());
            orderDetailBean.setUpdateDt(new Date());
            orderDetailBean.setUpdateId(optUser.getId());
            orderDetailBean.setUpdateName(optUser.getRealName());
        }
        dao.batchSaveOrderDetails(BatchInsertParameter.wrap(orderDetailBeans));
        return orderBean;
    }

    @Override
    public OrderTemp getProductTypeNumAndProductNumByNo(OrderDetailBean orderDetailBean) {
        return dao.getProductTypeNumAndProductNumByNo(orderDetailBean);
    }
}
