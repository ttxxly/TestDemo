package com.skm.demo.persistence.dao;

import com.skm.common.bean.dto.Page;
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

    List<ProductBean> getAllProduct();

    /**
     * 分页查询，查询后数据会直接存储在page参数的datas中，同时填写上分页数据
     *
     * @param page 分页查询对象
     * @return 结果集
     */
    List<T> productDynamicSelectPage(Page<T> page);
}
