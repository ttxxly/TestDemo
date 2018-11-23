package com.skm.demo.persistence.qo;

import com.skm.common.bean.dto.*;
import com.skm.demo.domain.OrderBean;
import com.skm.demo.domain.ProductBean;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;
import java.util.List;

public class OrderQo extends OrderBean implements Sortable, Operable {

    private static final long serialVersionUID = -1L;

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

    private List<Long> ids;
    private SortParam sortParam = new SortParam();
    private List<OperatorFilter> operatorFilters;
    private List<List<OperatorFilter>> orOperatorFilters;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

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

    public List<Long> getIds() {
        return ids;
    }

    public void setIds(List<Long> ids) {
        this.ids = ids;
    }

    public void setSortParam(SortParam sortParam) {
        this.sortParam = sortParam;
    }

    @Override
    public List<List<OperatorFilter>> getOrOperatorFilters() {
        return orOperatorFilters;
    }

    @Override
    public void setOrOperatorFilters(List<List<OperatorFilter>> orOperatorFilters) {
        this.orOperatorFilters = orOperatorFilters;
    }

    @Override
    public List<OperatorFilter> getOperatorFilters() {
        return null;
    }

    @Override
    public void setOperatorFilters(List<OperatorFilter> operatorFilters) {

    }

    @Override
    public SortParam getSortParam() {
        return null;
    }
}
