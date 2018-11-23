package com.skm.demo.web.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

public class OrderQueryVo {
    /**
     * 查询条件：采购单号
     */
    private String noLike;

    /**
     * 查询条件：货主编码
     */
    private String shipperCodeLike;

    /**
     * 查询条件：供应商编码
     */
    private String providerCodeLike;

    /**
     * 查询条件：开始提交时间
     */
    private Date startSubmitTime;

    /**
     * 查询条件：结束提交时间
     */
    private Date endSubmitTime;

    public String getNoLike() {
        return noLike;
    }

    public void setNoLike(String noLike) {
        this.noLike = noLike;
    }

    public String getShipperCodeLike() {
        return shipperCodeLike;
    }

    public void setShipperCodeLike(String shipperCodeLike) {
        this.shipperCodeLike = shipperCodeLike;
    }

    public String getProviderCodeLike() {
        return providerCodeLike;
    }

    public void setProviderCodeLike(String providerCodeLike) {
        this.providerCodeLike = providerCodeLike;
    }

    public Date getStartSubmitTime() {
        return startSubmitTime;
    }

    public void setStartSubmitTime(Date startSubmitTime) {
        this.startSubmitTime = startSubmitTime;
    }

    public Date getEndSubmitTime() {
        return endSubmitTime;
    }

    public void setEndSubmitTime(Date endSubmitTime) {
        this.endSubmitTime = endSubmitTime;
    }
}
