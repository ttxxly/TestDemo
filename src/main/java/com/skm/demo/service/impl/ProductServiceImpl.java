package com.skm.demo.service.impl;

import com.skm.common.bean.dto.Page;
import com.skm.common.bean.dto.UnifyUser;
import com.skm.common.bean.utils.BeanMapper;
import com.skm.common.mybatis.config.ITransactional;
import com.skm.common.mybatis.dto.BatchInsertParameter;
import com.skm.demo.domain.ProductBean;
import com.skm.demo.persistence.DTO.ImportFileDTO;
import com.skm.demo.persistence.dao.ProductDao;
import com.skm.demo.persistence.qo.ProductQo;
import com.skm.demo.service.ProductService;
import com.skm.demo.web.ProductController;
import com.skm.demo.persistence.DTO.ProductSaveResultDTO;
import com.skm.demo.web.vo.ProductSaveVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.regex.Pattern;


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
    public ProductSaveResultDTO batchProductSave(MultipartFile file, UnifyUser optUser) {

        Integer ERROR = -1;
        Integer REPEAT = 1;
        Integer INSERT = 2;
//        int insertNum;
//        int updateNum;
//        List<String> errorMsg = new ArrayList<>();
//        List<ProductSaveVo> psv = new ArrayList<>();
//        HashMap<Integer, Integer> lineMap = new HashMap<>();
//
//        //返回结果对象
//        ProductSaveResultDTO productSaveResultDTO = new ProductSaveResultDTO();
//
//        try (InputStream inputStream = file.getInputStream();
//             InputStreamReader inputStreamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
//             BufferedReader br = new BufferedReader(inputStreamReader)) {
//            String line;
//            int count = 0;
//            int tmp = 0;
//            while ((line = br.readLine()) != null) {
//                count++;
//                String[] rsu = line.split(",");
//
//                //数据是否错误判断条件：是否获取到了三个属性
//                if (rsu.length != 3) {
//                    errorMsg.add("导入错误：第" + count + "行 数据格式错误");
//                } else {
//                    tmp++;
//                    //如果数据正确，保存到 ProductSaveVo list中
//                    ProductSaveVo saveVo = new ProductSaveVo();
//                    saveVo.setCode(rsu[0]);
//                    saveVo.setName(rsu[1]);
//                    saveVo.setPrice(new BigDecimal(rsu[2]));
//                    psv.add(saveVo);
//                    lineMap.put(tmp, count);
//                }
//            }
//        } catch (Exception e) {
//            LOG.error(e.toString());
//        }
//
//        //批量转化
//        List<ProductBean> productBeans = BeanMapper.mapList(psv, ProductSaveVo.class, ProductBean.class);
//        //自身去重
//        HashSet<ProductBean> hashSet = new HashSet<>();
//        //记录重复的key
//        List<ProductBean> codeList = new ArrayList<>();
//        //记录重复的行数
//        Map<Integer, List<Integer>> repeatLine = new HashMap<>();
//        int repeatListCount = 0;
//        for (ProductBean productBean : productBeans) {
//            repeatListCount++;
//            if (!hashSet.add(productBean)) {
//                codeList.add(productBean);
//                //获取重复对象的下标
//                int index = productBeans.indexOf(productBean);
//                ++index;
//                if (repeatLine.get(index) == null) {
//                    List<Integer> integerList = new ArrayList<>();
//                    integerList.add(repeatListCount);
//                    repeatLine.put(index, integerList);
//                } else {
//                    List<Integer> integerList = repeatLine.get(index);
//                    integerList.add(repeatListCount);
//                    repeatLine.replace(index, integerList);
//                }
//            }
//        }
//        productBeans.clear();
//        productBeans.addAll(hashSet);
//        for (ProductBean productBean : codeList) {
//            productBeans.remove(productBean);
//        }
//        int count = 0;
//        for (Integer integer : repeatLine.keySet()) {
//            StringBuilder s = new StringBuilder();
//            String startStr = "导入重复：第" + lineMap.get(integer) + "行与";
//            s.append(startStr);
//            List<Integer> list = repeatLine.get(integer);
//            for (Integer i :
//                    list) {
//                String tmp = "第" + lineMap.get(i) + "行 ";
//                s.append(tmp);
//            }
//            String endStr = "重复,重复值为"+codeList.get(count).getCode()+".";
//            s.append(endStr);
//            errorMsg.add(s.toString());
//            count ++;
//        }
//
//        List<ProductBean> allProduct = dao.getAllProduct();
//        List<ProductBean> insertBeans = new ArrayList<>();
//        List<ProductBean> updateBeans = new ArrayList<>();
//
//        HashMap<String, ProductBean> hashMap = new HashMap<>();
//        if (allProduct.size() == 0) {
//            dao.batchProductSave(BatchInsertParameter.wrap(setNecessary(productBeans, optUser, 1)));
//            insertNum = productBeans.size();
//            updateNum = 0;
//        } else {
//            for (ProductBean productBean : allProduct) {
//                hashMap.put(productBean.getCode(), productBean);
//            }
//            for (ProductBean productBean : productBeans) {
//                if (hashMap.containsKey(productBean.getCode())) {
//                    updateBeans.add(productBean);
//                } else {
//                    insertBeans.add(productBean);
//                }
//            }
//
//            insertNum = insertBeans.size();
//            updateNum = updateBeans.size();
//            if (insertNum != 0) {
//                dao.batchProductSave(BatchInsertParameter.wrap(setNecessary(insertBeans, optUser, 1)));
//            }
//            if (updateNum != 0) {
//                dao.batchProductUpdate(BatchInsertParameter.wrap(setNecessary(updateBeans, optUser, 2)));
//            }
//        }
//        //定制返回结果
//        productSaveResultDTO.setErrorMsg(errorMsg);
//        productSaveResultDTO.setSuccessMsg("导入成功:共导入" + insertNum + "个商品,更新" + updateNum + "个商品");
//        return productSaveResultDTO;

        //返回结果对象
        ProductSaveResultDTO productSaveResultDTO = new ProductSaveResultDTO();
        List<String> errorMsg = new ArrayList<>();
        List<ImportFileDTO> importFileDTOS = new ArrayList<>();
        //获取数据库数据
        List<ProductBean> allProduct = dao.getAllProduct();
        //保存数据库中，编码和对象实例映射关系
        HashMap<String, ProductBean> databaseMap = new HashMap<>();
        for (ProductBean productBean : allProduct) {
            databaseMap.put(productBean.getCode(), productBean);
        }
        try (InputStream inputStream = file.getInputStream();
             InputStreamReader inputStreamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
             BufferedReader br = new BufferedReader(inputStreamReader)) {
            String line;
            int lineCount = 0;
            while ((line = br.readLine()) != null) {
                String[] lineData = line.split(",");
                lineCount++;
                //数据是否错误判断条件：是否获取到了三个属性
                ImportFileDTO importFileDTO = new ImportFileDTO();
                if (lineData.length != 3) {
                    importFileDTO.setStatus(ERROR);
                    importFileDTO.setErrorMsg("格式错误");
                } else {
                    //获取到了三个数据，需要验证三个数据是否正确
                    boolean matches;
                    try {
                        BigDecimal price = new BigDecimal(lineData[2]);
                        matches = price.compareTo(new BigDecimal(0)) >= 0;
                    } catch (NumberFormatException e) {
                        matches = false;
                    }
                    if (matches) {
                        importFileDTO.setStatus(INSERT);
                        importFileDTO.setCode(lineData[0]);
                        importFileDTO.setName(lineData[1]);
                        importFileDTO.setPrice(new BigDecimal(lineData[2]));
                    } else {
                        importFileDTO.setStatus(ERROR);
                        importFileDTO.setErrorMsg("金额错误");
                    }
                }
                importFileDTO.setCurrentLine(lineCount);
                importFileDTOS.add(importFileDTO);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        //Set 去重
        HashSet<ImportFileDTO> tempSet = new HashSet<>();
        //要保存的 Bean 包含 和数据库编码重复的数据
        List<ProductBean> saveBean = new ArrayList<>();
        HashMap<ImportFileDTO, List<Integer>> repeatMap = new HashMap<>();
        for (ImportFileDTO importFileDTO : importFileDTOS) {
            //1. 保存错误信息
            if (importFileDTO.getStatus() - ERROR == 0) {
                errorMsg.add("导入错误：第" + importFileDTO.getCurrentLine() + "行" + importFileDTO.getErrorMsg() + ".");
            }  else {
                if (!tempSet.add(importFileDTO)) {
                    importFileDTO.setStatus(REPEAT);
                    //获取与当前重复编码的元素下标
                    int index = importFileDTOS.indexOf(importFileDTO);
                    importFileDTOS.get(index).setStatus(REPEAT);
                    List<Integer> lines = repeatMap.get(importFileDTOS.get(index));
                    if (lines == null) {
                        lines = new ArrayList<>();
                        lines.add(importFileDTO.getCurrentLine());
                        repeatMap.put(importFileDTOS.get(index), lines);
                    } else {
                        lines.add(importFileDTO.getCurrentLine());
                        repeatMap.replace(importFileDTOS.get(index), lines);
                    }
                }
            }
        }
        for (ImportFileDTO importFileDTO : importFileDTOS) {
            if (importFileDTO.getStatus() - INSERT == 0) {
                ProductBean productBean = BeanMapper.map(importFileDTO, ProductBean.class);
                saveBean.add(productBean);
            }
        }
        //做数据库操作,分为 insertBeans 和 updateBeans
        List<ProductBean> insertBeans = new ArrayList<>();
        List<ProductBean> updateBeans = new ArrayList<>();
        for (ProductBean productBean:saveBean) {
            if (databaseMap.get(productBean.getCode()) == null) {
                insertBeans.add(productBean);
            }else {
                updateBeans.add(productBean);
            }
        }
        if (insertBeans.size() != 0) {
            dao.batchProductSave(BatchInsertParameter.wrap(setNecessary(insertBeans, optUser, 1)));
        }
        if (updateBeans.size() != 0) {
            dao.batchProductSave(BatchInsertParameter.wrap(setNecessary(updateBeans, optUser, 2)));
        }
        //聚合返回结果
        for (ImportFileDTO importFileDTO : repeatMap.keySet()) {

            StringBuilder s = new StringBuilder();
            String startStr = "导入重复：第" + importFileDTO.getCurrentLine() + "行与";
            s.append(startStr);
            List<Integer> list = repeatMap.get(importFileDTO);
            for (Integer line : list) {
                String tmp = "第" + line + "行 ";
                s.append(tmp);
            }
            String endStr = "重复,重复值为" + importFileDTO.getCode() + ".";
            s.append(endStr);
            errorMsg.add(s.toString());
        }

        productSaveResultDTO.setErrorMsg(errorMsg);
        productSaveResultDTO.setSuccessMsg("导入成功：插入"
                + insertBeans.size() + "条数据,更新了"+updateBeans.size()+"条数据");

        return productSaveResultDTO;
    }

    @Override
    public List<ProductBean> getAllProduct() {
        return dao.getAllProduct();
    }

    private List<ProductBean> setNecessary(List<ProductBean> productBeans, UnifyUser optUser, Integer code) {

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
        } else if (code == 2) {//做更新操作
            for (ProductBean p : productBeans) {
                p.setUpdateDt(new Date());
                p.setUpdateId(optUser.getId());
                p.setUpdateName(optUser.getRealName());
            }
        }
        return productBeans;
    }
}
