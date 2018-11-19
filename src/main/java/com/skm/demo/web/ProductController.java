package com.skm.demo.web;

import com.skm.common.bean.dto.Page;
import com.skm.common.bean.dto.PageParam;
import com.skm.common.bean.dto.Result;
import com.skm.common.bean.dto.UnifyUser;
import com.skm.common.bean.utils.BeanMapper;
import com.skm.common.spring.advisor.BaseController;
import com.skm.demo.domain.ProductBean;
import com.skm.demo.domain.UserBean;
import com.skm.demo.persistence.qo.ProductQo;
import com.skm.demo.persistence.qo.UserQO;
import com.skm.demo.service.ProductService;
import com.skm.demo.web.vo.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/web/v1/product")
public class ProductController extends BaseController {

    private ProductService productService;

    @PostMapping(value = "/page")
    public Result<Page<ProductVo>> page(@RequestBody PageParam<ProductQueryVo> pageParam) {
        int pn = pageParam.getPn();
        int ps = pageParam.getPs();
        UnifyUser currentUser = getCurrentUser();

        ProductQo productQo = Optional.of(pageParam.getConditions()).map(cond -> {
            return BeanMapper.map(cond, ProductQo.class);
        }).orElse(null);

        Page<ProductBean> beanPage = productService.list(productQo, ps, pn, currentUser);

        List<ProductVo> productVos = BeanMapper.mapList(beanPage.getDatas(), ProductBean.class, ProductVo.class);
        Page<ProductVo> page = new Page<>(beanPage.getPn(), beanPage.getPs());
        page.setTc(beanPage.getTc());
        page.setDatas(productVos);

        return Result.success(page);
    }

    @PostMapping(value = "/getAll")
    public Result<List<ProductVo>> getAll() {
        Result result = new Result();
        List<ProductBean> productBeans = productService.getAll();
        List<ProductVo> productVos= BeanMapper.mapList(productBeans, ProductBean.class, ProductVo.class);

        result.setContent(productVos);
        return result;
    }

    @PostMapping(value = "/add")
    public Result add(MultipartFile file) {

//        if (file.isEmpty()) {
//            return "文件为空";
//        }
        try {
            // Get the file and save it somewhere
            InputStream inputStream = file.getInputStream();
            StringBuilder sb = new StringBuilder();
            String line;

            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
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
                    errorMsg.add("第" + count + "行 数据格式错误");
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
            ProductSaveResultVo pvo = productService.add(productBeans);
            //加上错误信息
            pvo.setErrorMsg(errorMsg);
            return Result.success(pvo);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Result.error("500", "服务器错误");
    }
}
