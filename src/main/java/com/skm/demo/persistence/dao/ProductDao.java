package com.skm.demo.persistence.dao;

import com.skm.common.mybatis.dao.BaseDao;
import com.skm.common.mybatis.dto.BatchInsertParameter;
import com.skm.demo.domain.ProductBean;
import com.skm.demo.domain.UserBean;
import com.skm.demo.web.vo.ProductVo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("myProductDao")
public interface ProductDao extends BaseDao<ProductBean> {

    int BatchSave(List<ProductBean> productBeans);
}
