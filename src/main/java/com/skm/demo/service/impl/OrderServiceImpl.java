package com.skm.demo.service.impl;

import com.skm.common.bean.dto.Page;
import com.skm.common.bean.dto.UnifyUser;
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

    @Autowired
    private OrderDao dao;

    @Override
    public Page<OrderBean> list(OrderQo qo, int ps, int pn, UnifyUser optUser) {
        Page<OrderBean> page = new Page<>();
        page.setConditions(qo);
        page.setPn(pn);
        page.setPs(ps);
        dao.dynamicSelectOrder(page);
        return page;
    }

    @Override
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
        for (int i=0; i<orderDetailBeans.size(); i++) {
            orderDetailBeans.get(i).setOrderNo(orderBean.getNo());
            orderDetailBeans.get(i).setEntryDt(new Date());
            orderDetailBeans.get(i).setEntryId(optUser.getId());
            orderDetailBeans.get(i).setEntryName(optUser.getRealName());
            orderDetailBeans.get(i).setUpdateDt(new Date());
            orderDetailBeans.get(i).setUpdateId(optUser.getId());
            orderDetailBeans.get(i).setUpdateName(optUser.getRealName());
        }
        dao.batchSaveOrderDetails(BatchInsertParameter.wrap(orderDetailBeans));
        return orderBean;
    }

    @Override
    public OrderTemp getProductTypeNumAndProductNumByNo(String no) {
        return dao.getProductTypeNumAndProductNumByNo(no);
    }

}
