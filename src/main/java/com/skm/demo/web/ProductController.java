package com.skm.demo.web;

import com.skm.common.bean.dto.Page;
import com.skm.common.bean.dto.PageParam;
import com.skm.common.bean.dto.Result;
import com.skm.common.bean.dto.UnifyUser;
import com.skm.common.bean.utils.BeanMapper;
import com.skm.common.spring.advisor.BaseController;
import com.skm.demo.domain.ProductBean;
import com.skm.demo.persistence.DTO.ProductSaveResultDTO;
import com.skm.demo.persistence.qo.ProductQo;
import com.skm.demo.service.ProductService;
import com.skm.demo.web.vo.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

@RestController
@RequestMapping("/web/v1/product")
@Api(tags = {"商品接口"}, description = "操作商品表")
public class ProductController extends BaseController {

    private ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/page")
    @ApiOperation("动态查询商品列表")
    public Result<Page<ProductVo>> page(@RequestBody PageParam<ProductQueryVo> pageParam) {
        int pn = pageParam.getPn();
        int ps = pageParam.getPs();
        UnifyUser currentUser = getCurrentUser();

        ProductQo productQo = Optional.of(pageParam.getConditions())
                .map(cond -> BeanMapper.map(cond, ProductQo.class))
                .orElse(new ProductQo());

        Page<ProductBean> beanPage = productService.list(productQo, ps, pn, currentUser);

        List<ProductVo> productVos = BeanMapper.mapList(beanPage.getDatas(), ProductBean.class, ProductVo.class);
        Page<ProductVo> page = new Page<>(beanPage.getPn(), beanPage.getPs());
        page.setTc(beanPage.getTc());
        page.setDatas(productVos);

        return Result.success(page);
    }

    @PostMapping("/getAllProduct")
    @ApiOperation("获取商品表中的所有数据")
    public Result<List<ProductVo>> getAllProduct() {
        List<ProductBean> productBeans = productService.getAllProduct();
        List<ProductVo> productVos = BeanMapper.mapList(productBeans, ProductBean.class, ProductVo.class);
        return Result.success(productVos);
    }

    @PostMapping("/add")
    @ApiOperation("上传文件，保存数据")
    public Result<ProductSaveResultVO> add(MultipartFile file) {

        ProductSaveResultDTO pvo = productService.batchProductSave(file, getCurrentUser());
        ProductSaveResultVO productSaveResultVO = BeanMapper.map(pvo, ProductSaveResultVO.class);

        return Result.success(productSaveResultVO);
    }
}
