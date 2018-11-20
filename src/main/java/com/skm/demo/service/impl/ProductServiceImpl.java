package com.skm.demo.service.impl;

import com.skm.common.bean.dto.Page;
import com.skm.common.bean.dto.Result;
import com.skm.common.bean.dto.UnifyUser;
import com.skm.common.mybatis.config.ITransactional;
import com.skm.demo.domain.ProductBean;
import com.skm.demo.domain.UserBean;
import com.skm.demo.persistence.dao.ProductDao;
import com.skm.demo.persistence.qo.ProductQo;
import com.skm.demo.persistence.qo.UserQO;
import com.skm.demo.service.ProductService;
import com.skm.demo.web.vo.ProductSaveResultVo;
import com.skm.demo.web.vo.ProductSaveVo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDao dao;



    @Override
    @ITransactional
    public Page<ProductBean> list(ProductQo qo, int ps, int pn, UnifyUser optUser) {
        return null;
    }

    @Override
    @ITransactional
    public Integer batchSave(List<ProductBean> productBeans, UnifyUser optUser) {
        for (int i=0; i<productBeans.size(); i++) {

            productBeans.get(i).setEntryDt(new Date());
            productBeans.get(i).setEntryId(optUser.getId());
            productBeans.get(i).setEntryName(optUser.getRealName());
            productBeans.get(i).setUpdateDt(new Date());
            productBeans.get(i).setUpdateId(optUser.getId());
            productBeans.get(i).setUpdateName(optUser.getRealName());
        }
        //影响行数
        int num = dao.BatchSave(productBeans);
        ProductSaveResultVo productSaveResultVo = new ProductSaveResultVo();
        productSaveResultVo.setUpdateNUm((long) num);
        productSaveResultVo.setSum((long) productBeans.size());
        productSaveResultVo.setSuccessMsg("导入成功：共导入"+ productSaveResultVo.getSum()
                +"个商品，更新"+ productSaveResultVo.getUpdateNUm()+"个商品");
        return num;
    }

    @Override
    @ITransactional
    public List<ProductBean> getAll() {
        return null;
    }


}
