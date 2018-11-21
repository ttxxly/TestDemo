package com.skm.demo.persistence.dao;

import com.skm.common.mybatis.dao.BaseDao;
import com.skm.common.mybatis.dto.BatchInsertParameter;
import com.skm.demo.domain.ProductBean;
import com.skm.demo.web.vo.ProductSaveVo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("myProductDao")
public interface ProductDao<T> extends BaseDao<ProductBean> {

    int batchProductSave(BatchInsertParameter<T> entity);

    int batchProductUpdate(BatchInsertParameter<T> entity);

    List<ProductSaveVo> getAllProduct();
}
