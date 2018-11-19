package com.skm.demo.web.vo;

import java.io.Serializable;
import java.util.List;

public class ProductSaveResultVo implements Serializable {

    private List<String> errorMsg;
    private String successMsg;
    private Long sum;
    private Long updateNUm;

    public List<String> getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(List<String> errorMsg) {
        this.errorMsg = errorMsg;
    }

    public String getSuccessMsg() {
        return successMsg;
    }

    public void setSuccessMsg(String successMsg) {
        this.successMsg = successMsg;
    }

    public Long getSum() {
        return sum;
    }

    public void setSum(Long sum) {
        this.sum = sum;
    }

    public Long getUpdateNUm() {
        return updateNUm;
    }

    public void setUpdateNUm(Long updateNUm) {
        this.updateNUm = updateNUm;
    }

    public ProductSaveResultVo() {
    }


}
