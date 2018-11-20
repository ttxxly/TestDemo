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
    private Date import_dt;

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

    public Date getImport_dt() {
        return import_dt;
    }

    public void setImport_dt(Date import_dt) {
        this.import_dt = import_dt;
    }

    @Override
    public String toString() {
        return "ProductBean{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", import_dt=" + import_dt +
                '}';
    }

    public ProductVo() {
    }

    public ProductVo(Long id, String code, String name, double price, Date import_dt) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.price = price;
        this.import_dt = import_dt;
    }
}
