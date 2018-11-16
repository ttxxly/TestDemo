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

    public OrderBean() {
    }

    public OrderBean(Long id, String no, String shipper_code, String provider_code, String provider_name, Date submit_dt, Date delivery_dt) {
        this.id = id;
        this.no = no;
        this.shipper_code = shipper_code;
        this.provider_code = provider_code;
        this.provider_name = provider_name;
        this.submit_dt = submit_dt;
        this.delivery_dt = delivery_dt;
    }

    @Override
    public String toString() {
        return "OrderBean{" +
                "id=" + id +
                ", no='" + no + '\'' +
                ", shipper_code='" + shipper_code + '\'' +
                ", provider_code='" + provider_code + '\'' +
                ", provider_name='" + provider_name + '\'' +
                ", submit_dt=" + submit_dt +
                ", delivery_dt=" + delivery_dt +
                '}';
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
