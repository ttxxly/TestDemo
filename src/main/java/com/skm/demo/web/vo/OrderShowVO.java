package com.skm.demo.web.vo;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class OrderShowVO {

    /**
     * 采购单号
     */
    private String no;

    /**
     * 货主编码
     */
    private String shipperCode;

    /**
     * 货主名称
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
     * 到货日期
     */
    private Date deliveryDt;

    private List<OrderDetailVO> orderDetails;

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

    public Date getDeliveryDt() {
        return deliveryDt;
    }

    public void setDeliveryDt(Date deliveryDt) {
        this.deliveryDt = deliveryDt;
    }

    public List<OrderDetailVO> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<OrderDetailVO> orderDetails) {
        this.orderDetails = orderDetails;
    }
}
