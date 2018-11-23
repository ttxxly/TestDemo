package com.skm.demo.web.vo;

import java.util.Date;

public class ProductQueryVo {
    /**
     * 查询条件：商品编码
     */
    private String codeLike;

    /**
     * 查询条件：商品名称
     */
    private String nameLike;

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
