package com.skm.demo.web.vo;

import javax.validation.constraints.NotNull;

public class OrderDetailSaveVo {

    /**
     * 商品编码
     */
    @NotNull
    private String productCode;
    /**
     * 商品名称
     */
    @NotNull
    private String productName;

    /**
     * 商品总数量
     */
    @NotNull
    private Long amount;

    /**
     * 订单单价
     */
    @NotNull
    private Double price;

    /**
     * 订单总金额
     */
    private Double totalMoney;

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
