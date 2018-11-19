package com.skm.demo.web.vo;

import java.util.Date;

public class ProductQueryVo {

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

    /**
     * 查询条件：商品编码
     */
    private String codeLike;

    /**
     * 查询条件：商品名称
     */
    private String nameLike;

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

    public String getCodeLike() {
        return codeLike;
    }

    public void setCodeLike(String codeLike) {
        this.codeLike = codeLike;
    }

    public String getNameLike() {
        return nameLike;
    }

    public void setNameLike(String nameLike) {
        this.nameLike = nameLike;
    }
}
