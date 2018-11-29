package com.skm.demo.web.vo;

import java.math.BigDecimal;
import java.math.BigInteger;

public class OrderDetailVO {

    /**
     * 商品编码
     */
    private String productCode;

    /**
     * 商品编码
     */
    private String productName;
    /**
     * 商品编码
     */
    private BigInteger amount;
    /**
     * 商品编码
     */
    private BigDecimal price;
    /**
     * 商品编码
     */
    private BigDecimal totalMoney;

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

    public BigInteger getAmount() {
        return amount;
    }

    public void setAmount(BigInteger amount) {
        this.amount = amount;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(BigDecimal totalMoney) {
        this.totalMoney = totalMoney;
    }
}
