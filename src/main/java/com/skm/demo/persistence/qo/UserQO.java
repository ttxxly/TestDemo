package com.skm.demo.persistence.qo;

import com.skm.common.bean.dto.Operable;
import com.skm.common.bean.dto.OperatorFilter;
import com.skm.common.bean.dto.OperatorFilterGroup;
import com.skm.common.bean.dto.SortParam;
import com.skm.common.bean.dto.Sortable;
import com.skm.demo.domain.UserBean;

import java.util.List;

public class UserQO extends UserBean implements Sortable, Operable {
    private static final long serialVersionUID = -1L;

    private String nameLike;
    private String realNameLike;
    private String langLike;
    private String emailLike;
    private String passwordLike;
    private String telLike;
    private String verifyCodeLike;
    private String entryNameLike;
    private String updateNameLike;

    private List<Long> ids;
    private SortParam sortParam = new SortParam();
    private List<OperatorFilter> operatorFilters;
    private List<OperatorFilterGroup> operatorFilterGroups;

    @Override
    public SortParam getSortParam() {
      return this.sortParam;
    }

    public void setIds(List<Long> ids) {
      this.ids = ids;
    }

    public List<Long> getIds() {
      return this.ids;
    }

    public void setNameLike(String nameLike) {
        this.nameLike = nameLike;
    }

    public String getNameLike() {
        return this.nameLike;
    }

    public void setRealNameLike(String realNameLike) {
        this.realNameLike = realNameLike;
    }

    public String getRealNameLike() {
        return this.realNameLike;
    }

    public void setLangLike(String langLike) {
        this.langLike = langLike;
    }

    public String getLangLike() {
        return this.langLike;
    }

    public void setEmailLike(String emailLike) {
        this.emailLike = emailLike;
    }

    public String getEmailLike() {
        return this.emailLike;
    }

    public void setPasswordLike(String passwordLike) {
        this.passwordLike = passwordLike;
    }

    public String getPasswordLike() {
        return this.passwordLike;
    }

    public void setTelLike(String telLike) {
        this.telLike = telLike;
    }

    public String getTelLike() {
        return this.telLike;
    }

    public void setVerifyCodeLike(String verifyCodeLike) {
        this.verifyCodeLike = verifyCodeLike;
    }

    public String getVerifyCodeLike() {
        return this.verifyCodeLike;
    }

    public void setEntryNameLike(String entryNameLike) {
        this.entryNameLike = entryNameLike;
    }

    public String getEntryNameLike() {
        return this.entryNameLike;
    }

    public void setUpdateNameLike(String updateNameLike) {
        this.updateNameLike = updateNameLike;
    }

    public String getUpdateNameLike() {
        return this.updateNameLike;
    }


    @Override
    public List<OperatorFilter> getOperatorFilters() {
      return operatorFilters;
    }
    @Override
    public void setOperatorFilters(List<OperatorFilter> operatorFilters) {
      this.operatorFilters = operatorFilters;
    }

    @Override
    public List<OperatorFilterGroup> getOperatorFilterGroups() {
        return operatorFilterGroups;
    }

    @Override
    public void setOperatorFilterGroups(List<OperatorFilterGroup> operatorFilterGroups) {
        this.operatorFilterGroups = operatorFilterGroups;
    }
}
