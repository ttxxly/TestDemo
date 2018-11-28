package com.skm.demo.persistence.DTO;

import com.skm.demo.domain.OrderBean;
import com.skm.demo.domain.OrderDetailBean;

import java.util.List;

public class OrderSaveDTO extends OrderBean {

    private List<OrderDetailBean> list;

    public List<OrderDetailBean> getList() {
        return list;
    }

    public void setList(List<OrderDetailBean> list) {
        this.list = list;
    }
}
