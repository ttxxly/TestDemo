package com.skm.demo.domain;

import com.skm.common.bean.BaseBean;


public class OrderDetailBean extends BaseBean {
    /**
     * 主键id
     */
    private Long id;

    /**
     * 订单单号
     */
    private String orderNo;

    /**
     * 商品编码
     */
    private String productCode;
    /**
     * 商品名称
     */
    private String productName;

    /**
     * 商品总数量
     */
    private Long amount;

    /**
     * 订单单价
     */
    private Double price;

    /**
     * 订单总金额
     */
    private Double totalMoney;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(Double totalMoney) {
        this.totalMoney = totalMoney;
    }
}
