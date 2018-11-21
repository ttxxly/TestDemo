package com.skm.demo.service.impl;

import com.skm.common.bean.dto.Page;
import com.skm.common.bean.dto.Result;
import com.skm.common.bean.dto.UnifyUser;
import com.skm.common.mybatis.config.ITransactional;
import com.skm.common.mybatis.dto.BatchInsertParameter;
import com.skm.demo.domain.ProductBean;
import com.skm.demo.domain.UserBean;
import com.skm.demo.persistence.dao.ProductDao;
import com.skm.demo.persistence.qo.ProductQo;
import com.skm.demo.persistence.qo.UserQO;
import com.skm.demo.service.ProductService;
import com.skm.demo.web.vo.ProductSaveResultVo;
import com.skm.demo.web.vo.ProductSaveVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDao dao;
    private String date;

    @Override
    @ITransactional
    public Page<ProductBean> list(ProductQo qo, int ps, int pn, UnifyUser optUser) {
        return null;
    }

    @Override
    @ITransactional
    public Integer batchProductSave(List<ProductBean> productBeans, UnifyUser optUser) throws ParseException {
        for (int i=0; i<productBeans.size(); i++) {
            productBeans.get(i).setImportDt(new Date());
            productBeans.get(i).setEntryDt(new Date());
            productBeans.get(i).setEntryId(optUser.getId());
            productBeans.get(i).setEntryName(optUser.getRealName());
            productBeans.get(i).setUpdateDt(new Date());
            productBeans.get(i).setUpdateId(optUser.getId());
            productBeans.get(i).setUpdateName(optUser.getRealName());
        }
        //影响行数
        int saveNum = dao.batchProductSave(BatchInsertParameter.wrap(productBeans));
        return saveNum;
    }

    @Override
    @ITransactional
    public Integer batchProductUpdate(List<ProductBean> productBeans, UnifyUser optUser) {
        for (int i=0; i<productBeans.size(); i++) {
            productBeans.get(i).setUpdateDt(new Date());
            productBeans.get(i).setUpdateId(optUser.getId());
            productBeans.get(i).setUpdateName(optUser.getRealName());
        }
        int updateNum = dao.batchProductUpdate(BatchInsertParameter.wrap(productBeans));
        return updateNum;
    }

    @Override
    @ITransactional
    public List<ProductBean> getAllProduct() {
        return dao.getAllProduct();
    }


}
