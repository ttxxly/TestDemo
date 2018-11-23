package com.skm.demo.domain;

import com.skm.common.bean.BaseBean;

import java.util.Date;


public class OrderBean extends BaseBean {

    /**
     * 主键id
     */
    private Long id;

    /**
     * 采购单号
     */
    private String no;

    /**
     * 货主编码
     */
    private String shipperCode;

    /**
     * 货主编码
     */
    private String shipperName;

    /**
     * 供货商编码
     */
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public String getShipperName() {
        return shipperName;
    }

    public void setShipperName(String shipperName) {
        this.shipperName = shipperName;
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
}
