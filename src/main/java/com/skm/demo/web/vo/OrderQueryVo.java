package com.skm.demo.web.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;
@ApiModel(value="订单表查询对象")
public class OrderQueryVo {
    /**
     * 采购单号
     */
    @ApiModelProperty(value = "采购单号")
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
     * 查询条件：开始提交时间
     */
    private Date startSubmitTime;

    /**
     * 查询条件：结束提交时间
     */
    private Date endSubmitTime;

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
