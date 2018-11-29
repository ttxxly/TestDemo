package com.skm.demo.service.impl;

import com.skm.common.bean.dto.Page;
import com.skm.common.bean.dto.UnifyUser;
import com.skm.common.bean.utils.BeanMapper;
import com.skm.common.mybatis.config.ITransactional;
import com.skm.common.mybatis.dto.BatchInsertParameter;
import com.skm.demo.domain.ProductBean;
import com.skm.demo.persistence.dao.ProductDao;
import com.skm.demo.persistence.qo.ProductQo;
import com.skm.demo.service.ProductService;
import com.skm.demo.web.ProductController;
import com.skm.demo.persistence.DTO.ProductSaveResultDTO;
import com.skm.demo.web.vo.ProductSaveVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.util.*;


@Service("myProductServiceImpl")
public class ProductServiceImpl implements ProductService {

    private ProductDao<ProductBean> dao;
    private static Logger LOG = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    public ProductServiceImpl(ProductDao<ProductBean> dao) {
        this.dao = dao;
    }

    @Override
    public Page<ProductBean> list(ProductQo qo, int ps, int pn, UnifyUser optUser) {
        Page<ProductBean> page = new Page<>();
        page.setConditions(qo);
        page.setPn(pn);
        page.setPs(ps);
        dao.productDynamicSelectPage(page);

        return page;
    }

    @Override
    @ITransactional
    public ProductSaveResultDTO batchProductSave(MultipartFile file, UnifyUser optUser){

        int insertNum;
        int updateNum;
        List<String> errorMsg = new ArrayList<>();
        List<ProductSaveVo> psv = new ArrayList<>();
        HashMap<Integer, Integer> lineMap = new HashMap<>();

        //返回结果对象
        ProductSaveResultDTO resultVo = new ProductSaveResultDTO();

        try (   InputStream inputStream = file.getInputStream();
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
                BufferedReader br = new BufferedReader(inputStreamReader)){
            String line ;
            int count = 0;
            int tmp = 0;
            while ((line = br.readLine()) != null) {
                count++;
                String[] rsu = line.split(",");

                //数据是否错误判断条件：是否获取到了三个属性
                if (rsu.length != 3) {
                    errorMsg.add("导入错误：第" + count + "行 数据格式错误");
                } else {
                    tmp++;
                    //如果数据正确，保存到 ProductSaveVo list中
                    ProductSaveVo saveVo = new ProductSaveVo();
                    saveVo.setCode(rsu[0]);
                    saveVo.setName(rsu[1]);
                    saveVo.setPrice(new BigDecimal(rsu[2]));
                    psv.add(saveVo);
                    lineMap.put(tmp, count);
                }
            }
        } catch (Exception e) {
            LOG.error(e.toString());
        }

        //批量转化
        List<ProductBean> productBeans = BeanMapper.mapList(psv, ProductSaveVo.class, ProductBean.class);
        //自身去重
        HashSet<ProductBean> hashSet = new HashSet<>();
        //记录重复的key
        List<ProductBean> codeList = new ArrayList<>();
        //记录重复的行数
        Map<Integer, List<Integer>> repeatLine = new HashMap<>();
        int repeatListCount = 0;
        for (ProductBean productBean : productBeans) {
            repeatListCount ++;
            if (!hashSet.add(productBean)) {
                codeList.add(productBean);
                //获取重复对象的下标
                int index = productBeans.indexOf(productBean);
                ++index;
                if (repeatLine.get(index) == null) {
                    List<Integer> integerList = new ArrayList<>();
                    integerList.add(repeatListCount);
                    repeatLine.put(index, integerList);
                }else {
                    List<Integer> integerList = repeatLine.get(index);
                    integerList.add(repeatListCount);
                    repeatLine.replace(index, integerList);
                }
            }
        }
        productBeans.clear();
        productBeans.addAll(hashSet);
        for (ProductBean productBean : codeList) {
            productBeans.remove(productBean);
        }
        for (Integer integer:repeatLine.keySet()) {
            StringBuilder s = new StringBuilder();
            String stmp = "导入重复：第"+lineMap.get(integer)+"行与";
            s.append(stmp);
            List<Integer> list = repeatLine.get(integer);
            for (Integer i:
                    list) {
                stmp = "第" + lineMap.get(i)+ "行 ";
                s.append(stmp);
            }
            stmp = "重复";
            s.append(stmp);
            errorMsg.add(s.toString());
        }
        List<ProductBean> allProduct = dao.getAllProduct();
        List<ProductBean> insertBeans = new ArrayList<>();
        List<ProductBean> updateBeans = new ArrayList<>();

        HashMap<String, ProductBean> hashMap = new HashMap<>();
        if (allProduct.size() == 0) {
            dao.batchProductSave(BatchInsertParameter.wrap(setNecessary(productBeans, optUser, 1)));
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
                dao.batchProductSave(BatchInsertParameter.wrap(setNecessary(insertBeans, optUser, 1)));
            }
            if (updateNum != 0) {
                dao.batchProductUpdate(BatchInsertParameter.wrap(setNecessary(updateBeans, optUser, 2)));
            }
        }
        //定制返回结果
        resultVo.setErrorMsg(errorMsg);
        resultVo.setSuccessMsg("导入成功:共导入" + insertNum + "个商品,更新" + updateNum + "个商品");
        return resultVo;
    }

    @Override
    public List<ProductBean> getAllProduct() {
        return dao.getAllProduct();
    }

    private List<ProductBean> setNecessary(List<ProductBean> productBeans, UnifyUser optUser, Integer code){

        if (code == 1) { //做插入操作
            for (ProductBean p : productBeans) {
                p.setImportDt(new Date());
                p.setEntryDt(new Date());
                p.setEntryId(optUser.getId());
                p.setEntryName(optUser.getRealName());
                p.setUpdateDt(new Date());
                p.setUpdateId(optUser.getId());
                p.setUpdateName(optUser.getRealName());
            }
        }else if (code == 2) {//做更新操作
            for (ProductBean p : productBeans) {
                p.setUpdateDt(new Date());
                p.setUpdateId(optUser.getId());
                p.setUpdateName(optUser.getRealName());
            }
        }
        return productBeans;
    }
}
