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

@Service("myProductServiceImpl")
public class ProductServiceImpl implements ProductService {

    private ProductDao dao;

    @Autowired
    public ProductServiceImpl(ProductDao dao) {
        this.dao = dao;
    }

    @Override
    public Page<ProductBean> list(ProductQo qo, int ps, int pn, UnifyUser optUser) {
        Page<ProductBean> page = new Page<>();
        page.setConditions(qo);
        page.setPn(pn);
        page.setPs(ps);
        dao.productDynamicSelectPage(page);

        return page;
    }

    @Override
    @ITransactional
    public Integer batchProductSave(List<ProductBean> productBeans, UnifyUser optUser){
        for (ProductBean p :
                productBeans) {
            p.setImportDt(new Date());
            p.setEntryDt(new Date());
            p.setEntryId(optUser.getId());
            p.setEntryName(optUser.getRealName());
            p.setUpdateDt(new Date());
            p.setUpdateId(optUser.getId());
            p.setUpdateName(optUser.getRealName());
        }
        return dao.batchProductSave(BatchInsertParameter.wrap(productBeans));
    }

    @Override
    public Integer batchProductUpdate(List<ProductBean> productBeans, UnifyUser optUser) {
        for (ProductBean productBean: productBeans) {
            productBean.setUpdateDt(new Date());
            productBean.setUpdateId(optUser.getId());
            productBean.setUpdateName(optUser.getRealName());
        }
        return dao.batchProductUpdate(BatchInsertParameter.wrap(productBeans));
    }

    @Override
    public List<ProductBean> getAllProduct() {
        return dao.getAllProduct();
    }

}
