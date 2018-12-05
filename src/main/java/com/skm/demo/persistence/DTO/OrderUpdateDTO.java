package com.skm.demo.persistence.DTO;

import com.skm.demo.domain.OrderBean;
import com.skm.demo.domain.OrderDetailBean;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

public class OrderUpdateDTO extends OrderBean {

    private List<OrderDetailBean> orderDetailBeans;

    public List<OrderDetailBean> getOrderDetailBeans() {
        return orderDetailBeans;
    }

    public void setOrderDetailBeans(List<OrderDetailBean> orderDetailBeans) {
        this.orderDetailBeans = orderDetailBeans;
    }
}
