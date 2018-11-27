package com.skm.demo.web;

import com.skm.common.bean.dto.Page;
import com.skm.common.bean.dto.PageParam;
import com.skm.common.bean.dto.Result;
import com.skm.common.bean.dto.UnifyUser;
import com.skm.common.bean.utils.BeanMapper;
import com.skm.common.spring.advisor.BaseController;
import com.skm.demo.domain.ProductBean;
import com.skm.demo.persistence.qo.ProductQo;
import com.skm.demo.service.ProductService;
import com.skm.demo.web.vo.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.util.*;

@RestController
@RequestMapping("/web/v1/product")
public class ProductController extends BaseController {
    public static Logger LOG = LoggerFactory.getLogger(ProductController.class);

    private ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping(value = "/page")
    public Result<Page<ProductVo>> page(@RequestBody PageParam<ProductQueryVo> pageParam) {
        int pn = pageParam.getPn();
        int ps = pageParam.getPs();
        UnifyUser currentUser = getCurrentUser();

        ProductQo productQo = Optional.of(pageParam.getConditions())
                .map(cond -> BeanMapper.map(cond, ProductQo.class))
                .orElse(null);

        Page<ProductBean> beanPage = productService.list(productQo, ps, pn, currentUser);

        List<ProductVo> productVos = BeanMapper.mapList(beanPage.getDatas(), ProductBean.class, ProductVo.class);
        Page<ProductVo> page = new Page<>(beanPage.getPn(), beanPage.getPs());
        page.setTc(beanPage.getTc());
        page.setDatas(productVos);

        return Result.success(page);
    }

    @PostMapping(value = "/getAllProduct")
    public Result<List<ProductVo>> getAllProduct() {
        List<ProductBean> productBeans = productService.getAllProduct();
        List<ProductVo> productVos = BeanMapper.mapList(productBeans, ProductBean.class, ProductVo.class);
        return Result.success(productVos);
    }

    @PostMapping(value = "/add")
    public Result add(MultipartFile file) {

        int insertNum;
        int updateNum;

        //返回结果对象
        ProductSaveResultVo pvo = new ProductSaveResultVo();
        try (InputStream inputStream = file.getInputStream();
             BufferedReader br = new BufferedReader(
                     new InputStreamReader(inputStream, StandardCharsets.UTF_8));) {
            // Get the file and save it somewhere

            StringBuilder sb = new StringBuilder();
            String line;
            int count = 0;
            ProductSaveResultVo result = new ProductSaveResultVo();
            List<String> errorMsg = new ArrayList<>();
            List<ProductSaveVo> psv = new ArrayList<>();
            while ((line = br.readLine()) != null) {
                sb.append(line);
                count++;
                String[] rsu = line.split(",");

                //数据是否错误判断条件：是否获取到了三个属性
                if (rsu.length != 3) {
                    errorMsg.add("导入错误：第" + count + "行 数据格式错误");
                } else {
                    //如果数据正确，保存到 ProductSaveVo list中
                    ProductSaveVo saveVo = new ProductSaveVo();
                    saveVo.setCode(rsu[0]);
                    saveVo.setName(rsu[1]);
                    saveVo.setPrice(new BigDecimal(rsu[2]));
                    psv.add(saveVo);
                }
            }
            System.out.println(sb.toString());
            //批量转化
            List<ProductBean> productBeans = BeanMapper.mapList(psv, ProductSaveVo.class, ProductBean.class);
            //自身去重
            HashSet<ProductBean> hashSet = new HashSet<>();
            //记录重复的key
            List<ProductBean> codeList = new ArrayList<>();
            for (ProductBean productBean : productBeans) {
                if (!hashSet.add(productBean)) {
                    codeList.add(productBean);
                }
            }
            productBeans.clear();
            productBeans.addAll(hashSet);
            for (ProductBean productBean : codeList) {
                productBeans.remove(productBean);
                errorMsg.add("导入重复，不予读取：重复的编码值为" + productBean.getCode());
            }
            List<ProductBean> allProduct = productService.getAllProduct();
            List<ProductBean> insertBeans = new ArrayList<>();
            List<ProductBean> updateBeans = new ArrayList<>();

            HashMap<String, ProductBean> hashMap = new HashMap<>();
            if (allProduct.size() == 0) {
                productService.batchProductSave(productBeans, getCurrentUser());
                insertNum = productBeans.size();
                updateNum = 0;
            } else {
                for (ProductBean productBean : allProduct) {
                    hashMap.put(productBean.getCode(), productBean);
                }
                for (ProductBean productBean : productBeans) {
                    if (hashMap.containsKey(productBean.getCode())) {
                        updateBeans.add(productBean);
                    } else {
                        insertBeans.add(productBean);
                    }
                }

                insertNum = insertBeans.size();
                updateNum = updateBeans.size();
                if (insertNum != 0) {
                    productService.batchProductSave(insertBeans, getCurrentUser());
                }
                if (updateNum != 0) {
                    productService.batchProductUpdate(updateBeans, getCurrentUser());
                }
            }

            //定制返回结果
            pvo.setErrorMsg(errorMsg);
            pvo.setInsertSum(insertNum);
            pvo.setUpdateNum(updateNum);
            pvo.setSuccessMsg("导入成功:共导入" + insertNum + "个商品,更新" + updateNum + "个商品");
        } catch (Exception e) {
            LOG.error(e.toString());
        }
        return Result.success(pvo);
    }
}
