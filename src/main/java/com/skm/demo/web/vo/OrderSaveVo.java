package com.skm.demo.web.vo;

import com.skm.demo.domain.OrderBean;
import com.skm.demo.domain.OrderDetailBean;

import java.util.Date;
import java.util.List;

public class OrderSaveVo {

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

    private List<OrderBean> beans;

    public List<OrderBean> getBeans() {
        return beans;
    }

    public void setBeans(List<OrderBean> beans) {
        this.beans = beans;
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
}
