package com.skm.demo.service;

import com.skm.common.bean.dto.Page;
import com.skm.common.bean.dto.UnifyUser;
import com.skm.demo.domain.ProductBean;
import com.skm.demo.domain.UserBean;
import com.skm.demo.persistence.qo.UserQO;
import com.skm.demo.web.vo.ProductSaveVo;
import com.skm.demo.web.vo.ProductVo;

import java.util.List;

public interface ProductService {

    /**
     * 分页获取
     *
     * @param qo 查询参数
     * @param ps 页大小
     * @param pn 页码
     * @param optUser 操作用户
     * @return
     */
    Page<UserBean> list(UserQO qo, int ps, int pn, UnifyUser optUser);

    /**
     * 批量新增商品信息
     * @param productBeans
     * @return
     */
    ProductVo add(List<ProductBean> productBeans);

}
