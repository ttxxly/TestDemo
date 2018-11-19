package com.skm.demo.persistence.qo;

import com.skm.common.bean.dto.Operable;
import com.skm.common.bean.dto.OperatorFilter;
import com.skm.common.bean.dto.SortParam;
import com.skm.common.bean.dto.Sortable;
import com.skm.demo.domain.ProductBean;
import com.skm.demo.domain.UserBean;

import java.util.List;

public class ProductQo extends ProductBean implements Sortable, Operable {
    private static final long serialVersionUID = -1L;

    private String codeLike;
    private String nameLike;

    private List<Long> ids;
    private SortParam sortParam = new SortParam();
    private List<OperatorFilter> operatorFilters;
    private List<List<OperatorFilter>> orOperatorFilters;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getCodeLike() {
        return codeLike;
    }

    public void setCodeLike(String codeLike) {
        this.codeLike = codeLike;
    }

    public String getNameLike() {
        return nameLike;
    }

    public void setNameLike(String nameLike) {
        this.nameLike = nameLike;
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
