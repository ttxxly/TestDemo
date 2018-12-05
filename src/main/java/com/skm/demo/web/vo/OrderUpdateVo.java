package com.skm.demo.web.vo;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

public class OrderUpdateVo {

    /**
     * 采购单号
     */
    @NotNull
    private String no;

    /**
     * 货主编码
     */
    @NotNull
    private String shipperCode;

    /**
     * 货主名称
     */
    @NotNull
    private String shipperName;

    /**
     * 供货商编码
     */
    @NotNull
    private String providerCode;

    /**
     * 供货商名称
     */
    private String providerName;

    /**
     * 订单提交时间
     */
    private Date submitDt;

    /**
     * 到货日期
     */
    private Date deliveryDt;

    private List<OrderDetailSaveVo> beans;



    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getShipperCode() {
        return shipperCode;
    }

    public void setShipperCode(String shipperCode) {
        this.shipperCode = shipperCode;
    }

    public String getProviderCode() {
        return providerCode;
    }

    public void setProviderCode(String providerCode) {
        this.providerCode = providerCode;
    }

    public String getProviderName() {
        return providerName;
    }

    public void setProviderName(String providerName) {
        this.providerName = providerName;
    }

    public Date getSubmitDt() {
        return submitDt;
    }

    public void setSubmitDt(Date submitDt) {
        this.submitDt = submitDt;
    }

    public Date getDeliveryDt() {
        return deliveryDt;
    }

    public void setDeliveryDt(Date deliveryDt) {
        this.deliveryDt = deliveryDt;
    }

    public List<OrderDetailSaveVo> getBeans() {
        return beans;
    }

    public void setBeans(List<OrderDetailSaveVo> beans) {
        this.beans = beans;
    }

    public String getShipperName() {
        return shipperName;
    }

    public void setShipperName(String shipperName) {
        this.shipperName = shipperName;
    }
}
