package com.skm.demo.web.vo;

import java.util.Date;

public class OrderQueryVo {
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
    private String shipper_code;

    /**
     * 供货商编码
     */
    private String provider_code;

    /**
     * 供货商名称
     */
    private String provider_name;

    /**
     * 订单提交时间
     */
    private Date submit_dt;

    /**
     * 到货日期
     */
    private Date delivery_dt;

    /**
     * 查询条件：采购单号
     */
    private String orderLike;

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
    private Date startSubmitTimeLike;

    /**
     * 查询条件：结束提交时间
     */
    private Date endSubmitTimeLike;

    public OrderQueryVo() {
    }

    public OrderQueryVo(Long id, String no, String shipper_code, String provider_code, String provider_name, Date submit_dt, Date delivery_dt, String orderLike, String shipperCodeLike, String providerCodeLike, Date startSubmitTimeLike, Date endSubmitTimeLike) {
        this.id = id;
        this.no = no;
        this.shipper_code = shipper_code;
        this.provider_code = provider_code;
        this.provider_name = provider_name;
        this.submit_dt = submit_dt;
        this.delivery_dt = delivery_dt;
        this.orderLike = orderLike;
        this.shipperCodeLike = shipperCodeLike;
        this.providerCodeLike = providerCodeLike;
        this.startSubmitTimeLike = startSubmitTimeLike;
        this.endSubmitTimeLike = endSubmitTimeLike;
    }

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

    public String getShipper_code() {
        return shipper_code;
    }

    public void setShipper_code(String shipper_code) {
        this.shipper_code = shipper_code;
    }

    public String getProvider_code() {
        return provider_code;
    }

    public void setProvider_code(String provider_code) {
        this.provider_code = provider_code;
    }

    public String getProvider_name() {
        return provider_name;
    }

    public void setProvider_name(String provider_name) {
        this.provider_name = provider_name;
    }

    public Date getSubmit_dt() {
        return submit_dt;
    }

    public void setSubmit_dt(Date submit_dt) {
        this.submit_dt = submit_dt;
    }

    public Date getDelivery_dt() {
        return delivery_dt;
    }

    public void setDelivery_dt(Date delivery_dt) {
        this.delivery_dt = delivery_dt;
    }

    public String getOrderLike() {
        return orderLike;
    }

    public void setOrderLike(String orderLike) {
        this.orderLike = orderLike;
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

    public Date getStartSubmitTimeLike() {
        return startSubmitTimeLike;
    }

    public void setStartSubmitTimeLike(Date startSubmitTimeLike) {
        this.startSubmitTimeLike = startSubmitTimeLike;
    }

    public Date getEndSubmitTimeLike() {
        return endSubmitTimeLike;
    }

    public void setEndSubmitTimeLike(Date endSubmitTimeLike) {
        this.endSubmitTimeLike = endSubmitTimeLike;
    }
}