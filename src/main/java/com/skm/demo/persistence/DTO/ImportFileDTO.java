package com.skm.demo.persistence.DTO;

import com.skm.demo.domain.ProductBean;

import java.math.BigDecimal;
import java.util.List;

public class ImportFileDTO {

    private String code;
    private String name;
    private BigDecimal price;
    /**
     * -1:表示数据错误
     * 1：表示数据重复
     * 2：表示数据插入
     */
    private Integer status;
    private String errorMsg;
    private Integer currentLine;


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public Integer getCurrentLine() {
        return currentLine;
    }

    public void setCurrentLine(Integer currentLine) {
        this.currentLine = currentLine;
    }

    @Override
    public int hashCode() {
        return code.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof ImportFileDTO)) {
            return false;
        } else {
            ImportFileDTO importFileDTO = (ImportFileDTO)obj;
            return this.code.equals(importFileDTO.code);
        }
    }
}
