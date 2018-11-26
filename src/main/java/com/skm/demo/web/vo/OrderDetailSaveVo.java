package com.skm.demo.web.vo;

public class OrderDetailSaveVo {

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
}
