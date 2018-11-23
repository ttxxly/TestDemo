package com.skm.demo.web.vo;

public class OrderTemp {
    /**
     * 订购商品种类数量
     */
    private Long productTypeNum;

    /**
     * 商品总数量
     */
    private Long productNum;

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
}
