package com.skm.demo.web.vo;

import java.util.Date;

public class ProductVo {
    /**
     * 主键id
     */
    private Long id;

    /**
     * 商品编码
     */
    private String code;

    /**
     * 商品名称
     */
    private String name;

    /**
     * 商品价格
     */
    private double price;
    /**
     * 商品导入时间
     */
    private Date importDt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Date getImportDt() {
        return importDt;
    }

    public void setImportDt(Date importDt) {
        this.importDt = importDt;
    }
}
