package com.skm.demo.persistence.DTO;

import com.skm.common.bean.utils.BeanMapper;
import com.skm.demo.domain.OrderBean;
import com.skm.demo.domain.OrderDetailBean;

import java.util.List;

public class OrderDTO {

    private OrderBean orderBean;
    private List<OrderDetailBean> orderDetailBeans;

    public OrderBean getOrderBean() {
        return orderBean;
    }

    public void setOrderBean(OrderBean orderBean) {
        this.orderBean = orderBean;
    }

    public List<OrderDetailBean> getOrderDetailBeans() {
        return orderDetailBeans;
    }

    public void setOrderDetailBeans(List<OrderDetailBean> orderDetailBeans) {
        this.orderDetailBeans = orderDetailBeans;
    }
}
