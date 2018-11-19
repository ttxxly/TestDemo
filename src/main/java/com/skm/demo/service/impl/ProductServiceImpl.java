package com.skm.demo.service.impl;

import com.skm.common.bean.dto.Page;
import com.skm.common.bean.dto.UnifyUser;
import com.skm.common.mybatis.config.ITransactional;
import com.skm.demo.domain.ProductBean;
import com.skm.demo.domain.UserBean;
import com.skm.demo.persistence.dao.ProductDao;
import com.skm.demo.persistence.dao.UserDao;
import com.skm.demo.persistence.qo.UserQO;
import com.skm.demo.service.ProductService;
import com.skm.demo.web.vo.ProductSaveVo;
import com.skm.demo.web.vo.ProductVo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDao dao;


    @Override
    public Page<UserBean> list(UserQO qo, int ps, int pn, UnifyUser optUser) {
        return null;
    }

    @Override
    @ITransactional
    public ProductVo add(List<ProductBean> productBeans) {
//        for (int i=0; i<productBeans.size(); i++) {
//
//            productBeans.get(i).setEntryDt(new Date());
//            productBeans.get(i).setEntryId(optUser.getId());
//            productBeans.get(i).setEntryName(optUser.getRealName());
//            productBeans.get(i).setUpdateDt(new Date());
//            productBeans.get(i).setUpdateId(optUser.getId());
//            productBeans.get(i).setUpdateName(optUser.getRealName());
//        }
        //影响行数
        int num = dao.BatchSave(productBeans);
        ProductVo productVo = new ProductVo();
        productVo.setUpdateNUm((long) num);
        productVo.setSum((long) productBeans.size());
        productVo.setSuccessMsg("导入成功：共导入"+productVo.getSum()
                +"个商品，更新"+productVo.getUpdateNUm()+"个商品");
        return productVo;
    }

}
