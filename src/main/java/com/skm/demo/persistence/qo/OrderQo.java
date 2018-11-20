package com.skm.demo.persistence.qo;

import com.skm.common.bean.dto.*;
import com.skm.demo.domain.OrderBean;
import com.skm.demo.domain.ProductBean;

import java.util.Date;
import java.util.List;

public class OrderQo extends OrderBean implements Sortable, Operable {

    private static final long serialVersionUID = -1L;

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

    private List<Long> ids;
    private SortParam sortParam = new SortParam();
    private List<OperatorFilter> operatorFilters;
    private List<List<OperatorFilter>> orOperatorFilters;


    public OrderQo(String shipperCodeLike, String providerCodeLike, Date startSubmitTimeLike, Date endSubmitTimeLike, List<Long> ids, SortParam sortParam, List<OperatorFilter> operatorFilters, List<List<OperatorFilter>> orOperatorFilters) {
        this.shipperCodeLike = shipperCodeLike;
        this.providerCodeLike = providerCodeLike;
        this.startSubmitTimeLike = startSubmitTimeLike;
        this.endSubmitTimeLike = endSubmitTimeLike;
        this.ids = ids;
        this.sortParam = sortParam;
        this.operatorFilters = operatorFilters;
        this.orOperatorFilters = orOperatorFilters;
    }

    public OrderQo(Long id, String no, String shipper_code, String provider_code, String provider_name, Date submit_dt, Date delivery_dt, String shipperCodeLike, String providerCodeLike, Date startSubmitTimeLike, Date endSubmitTimeLike, List<Long> ids, SortParam sortParam, List<OperatorFilter> operatorFilters, List<List<OperatorFilter>> orOperatorFilters) {
        super(id, no, shipper_code, provider_code, provider_name, submit_dt, delivery_dt);
        this.shipperCodeLike = shipperCodeLike;
        this.providerCodeLike = providerCodeLike;
        this.startSubmitTimeLike = startSubmitTimeLike;
        this.endSubmitTimeLike = endSubmitTimeLike;
        this.ids = ids;
        this.sortParam = sortParam;
        this.operatorFilters = operatorFilters;
        this.orOperatorFilters = orOperatorFilters;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
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
