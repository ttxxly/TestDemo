package com.skm.demo.persistence.DTO;

import com.skm.common.bean.utils.BeanMapper;
import com.skm.demo.domain.OrderBean;
import com.skm.demo.domain.OrderDetailBean;

import java.util.List;

public class OrderQueryDTO  extends OrderBean{

    /**
     * 订购商品种类数量
     */
    private Long productTypeNum;

    /**
     * 商品总数量
     */
    private Long productNum;

    private Double totalMoney;

    private String orderNo;

    public Long getProductTypeNum() {
        return productTypeNum;
    }

    public void setProductTypeNum(Long productTypeNum) {
        this.productTypeNum = productTypeNum;
    }

    public Long getProductNum() {
        return productNum;
    }

    public void setProductNum(Long productNum) {
        this.productNum = productNum;
    }

    public Double getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(Double totalMoney) {
        this.totalMoney = totalMoney;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }
}
