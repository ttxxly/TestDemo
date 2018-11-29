package com.skm.demo.service;

import com.skm.common.bean.dto.Page;
import com.skm.common.bean.dto.UnifyUser;
import com.skm.demo.domain.ProductBean;
import com.skm.demo.persistence.qo.ProductQo;
import com.skm.demo.persistence.DTO.ProductSaveResultDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
@Service
public interface ProductService {

    /**
     * 分页获取
     *
     * @param qo 查询参数
     * @param ps 页大小
     * @param pn 页码
     * @return 数据
     */
    Page<ProductBean> list(ProductQo qo, int ps, int pn, UnifyUser optUser);

    /**
     * 批量新增商品信息
     * @param file 上传的文件
     * @return 结果集
     */
    ProductSaveResultDTO batchProductSave(MultipartFile file, UnifyUser optUser);

    /**
     * 获取所有的商品信息
     * @return 商品
     */
    List<ProductBean> getAllProduct();
}
