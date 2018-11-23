package com.skm.demo.web.vo;

public class OrderDetailSaveVo {

    /**
     * 商品编码
     */
    private String product_code;
    /**
     * 商品名称
     */
    private String product_name;

    /**
     * 商品总数量
     */
    private Long amount;

    public String getProduct_code() {
        return product_code;
    }

    public void setProduct_code(String product_code) {
        this.product_code = product_code;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }
}
