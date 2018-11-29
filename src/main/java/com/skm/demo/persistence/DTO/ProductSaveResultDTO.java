package com.skm.demo.persistence.DTO;

import java.io.Serializable;
import java.util.List;

public class ProductSaveResultDTO implements Serializable {

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
