package com.skm.demo.service;

import com.skm.common.bean.dto.Page;
import com.skm.common.bean.dto.UnifyUser;
import com.skm.demo.domain.ProductBean;
import com.skm.demo.domain.UserBean;
import com.skm.demo.persistence.qo.ProductQo;
import com.skm.demo.persistence.qo.UserQO;
import com.skm.demo.web.vo.ProductSaveResultVo;
import com.skm.demo.web.vo.ProductSaveVo;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.List;
@Service
public interface ProductService {

    /**
     * 分页获取
     *
     * @param qo 查询参数
     * @param ps 页大小
     * @param pn 页码
     * @return
     */
    Page<ProductBean> list(ProductQo qo, int ps, int pn, UnifyUser optUser);

    /**
     * 批量新增商品信息
     * @param productBeans
     * @return
     */
    Integer batchProductSave(List<ProductBean> productBeans, UnifyUser optUser) throws ParseException;

    /**
     * 批量更新商品信息
     */
    Integer batchProductUpdate(List<ProductBean> productBeans, UnifyUser optUser);

    /**
     * 获取所有的商品信息
     * @return
     */
    List<ProductBean> getAllProduct();
}
