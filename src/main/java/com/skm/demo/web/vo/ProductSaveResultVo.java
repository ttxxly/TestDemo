package com.skm.demo.web.vo;

import java.io.Serializable;
import java.util.List;

public class ProductSaveResultVo implements Serializable {

    private List<String> errorMsg;
    private String successMsg;


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

}
