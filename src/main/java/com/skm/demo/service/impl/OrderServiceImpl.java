package com.skm.demo.service.impl;

import com.skm.common.bean.dto.Page;
import com.skm.common.bean.dto.UnifyUser;
import com.skm.common.bean.utils.BeanMapper;
import com.skm.common.mybatis.config.ITransactional;
import com.skm.common.mybatis.dto.BatchInsertParameter;
import com.skm.common.mybatis.dto.BatchUpdateParameter;
import com.skm.demo.domain.OrderBean;
import com.skm.demo.domain.OrderDetailBean;
import com.skm.demo.domain.ProductBean;
import com.skm.demo.persistence.DTO.OrderAndOrderDetailShowDTO;
import com.skm.demo.persistence.DTO.OrderQueryDTO;
import com.skm.demo.persistence.DTO.OrderSaveDTO;
import com.skm.demo.persistence.DTO.OrderUpdateDTO;
import com.skm.demo.persistence.dao.OrderDao;
import com.skm.demo.persistence.dao.OrderDetailDao;
import com.skm.demo.persistence.qo.OrderQo;
import com.skm.demo.service.OrderService;
import com.skm.demo.web.vo.OrderSaveVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.rmi.runtime.Log;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service("myOrderServiceImpl")
public class OrderServiceImpl implements OrderService {


    private OrderDao<OrderBean> orderDao;

    private OrderDetailDao<OrderDetailBean> orderDetailDao;


    @Autowired
    public void setOrderDao(OrderDao<OrderBean> orderDao) {
        this.orderDao = orderDao;
    }

    @Autowired
    public void setOrderDetailDao(OrderDetailDao<OrderDetailBean> orderDetailDao) {
        this.orderDetailDao = orderDetailDao;
    }

    @Override
    public Page<OrderQueryDTO> list(OrderQo qo, int ps, int pn, UnifyUser optUser) {

        Page<OrderBean> page = new Page<>();
        page.setConditions(qo);
        page.setPn(pn);
        page.setPs(ps);
        List<OrderQueryDTO> queryDTO = orderDao.dynamicSelectOrder(page);
        if (queryDTO.size() != 0) {
            List<String> orderNosList = new ArrayList<>();
            List<OrderQueryDTO> queryDTOList = new ArrayList<>();
            int count = 0;
            for (OrderBean orderBean : queryDTO) {
                orderNosList.add(orderBean.getNo());
                count ++;
                if (count == 500) {
                    List<OrderQueryDTO> numAndMoneyByNos = orderDetailDao.getNumAndMoneyByNos(orderNosList);
                    queryDTOList.addAll(numAndMoneyByNos);
                    count = 0;
                }
            }
            if (count != 0) {
                List<OrderQueryDTO> numAndMoneyByNos = orderDetailDao.getNumAndMoneyByNos(orderNosList);
                queryDTOList.addAll(numAndMoneyByNos);
            }
            for (OrderQueryDTO orderQueryDTO : queryDTOList) {
                for (int i = 0; i < queryDTO.size(); i++) {
                    if (queryDTO.get(i).getNo().equals(orderQueryDTO.getOrderNo())) {
                        queryDTO.get(i).setProductNum(orderQueryDTO.getProductNum());
                        queryDTO.get(i).setProductTypeNum(orderQueryDTO.getProductTypeNum());
                        queryDTO.get(i).setTotalMoney(orderQueryDTO.getTotalMoney());
                        queryDTO.get(i).setOrderNo(orderQueryDTO.getOrderNo());
                    }
                }
            }
        }
        Page<OrderQueryDTO> queryDTOPage = new Page<>();
        queryDTOPage.setConditions(qo);
        queryDTOPage.setPn(pn);
        queryDTOPage.setPs(ps);
        queryDTOPage.setDatas(queryDTO);
        return queryDTOPage;
    }

    @Override
    @ITransactional
    public OrderSaveDTO save(OrderSaveDTO orderSaveDTO, UnifyUser optUser) {

        OrderSaveDTO saveDTO = new OrderSaveDTO();

        OrderBean orderBean = BeanMapper.map(orderSaveDTO, OrderBean.class);
        orderBean.setEntryDt(new Date());
        orderBean.setEntryId(optUser.getId());
        orderBean.setEntryName(optUser.getRealName());
        orderBean.setUpdateDt(new Date());
        orderBean.setUpdateId(optUser.getId());
        orderBean.setUpdateName(optUser.getRealName());
        int line = orderDao.saveOrder(orderBean);


        List<OrderDetailBean> orderDetailBeans = orderSaveDTO.getList();
        for (OrderDetailBean orderDetailBean : orderDetailBeans) {
            orderDetailBean.setOrderNo(orderBean.getNo());
            orderDetailBean.setEntryDt(new Date());
            orderDetailBean.setEntryId(optUser.getId());
            orderDetailBean.setEntryName(optUser.getRealName());
            orderDetailBean.setUpdateDt(new Date());
            orderDetailBean.setUpdateId(optUser.getId());
            orderDetailBean.setUpdateName(optUser.getRealName());
            orderDetailBean.setTotalMoney(orderDetailBean.getPrice() * orderDetailBean.getAmount());
        }
        int lines = orderDetailDao.batchSaveOrderDetails(BatchInsertParameter.wrap(orderDetailBeans));
        if (line == 1 && lines == orderDetailBeans.size()) {
            saveDTO = BeanMapper.map(orderBean, OrderSaveDTO.class);
            saveDTO.setList(orderDetailBeans);

            return saveDTO;
        }
        return saveDTO;
    }

    @Override
    public OrderUpdateDTO showOrderAndOrderDetail(OrderAndOrderDetailShowDTO dto) {
        OrderUpdateDTO orderUpdateDTO;

        //根据订单查询订单表
        OrderBean orderBean = orderDao.getOrderByNo(dto.getNo());
        //根据订单号查询订单明细表数据
        List<OrderDetailBean> list = orderDetailDao.getOrderDetailByNo(dto.getNo());
        orderUpdateDTO = BeanMapper.map(orderBean, OrderUpdateDTO.class);
        orderUpdateDTO.setOrderDetailBeans(list);

        return orderUpdateDTO;
    }

    @Override
    public Boolean updateOrder(OrderUpdateDTO orderUpdateDTO, UnifyUser optUser) {

        OrderBean orderBean = BeanMapper.map(orderUpdateDTO, OrderBean.class);
        orderBean.setUpdateDt(new Date());
        orderBean.setUpdateId(optUser.getId());
        orderBean.setUpdateName(optUser.getRealName());

        List<OrderDetailBean> list = orderUpdateDTO.getOrderDetailBeans();
        List<OrderDetailBean> insertBeans = new ArrayList<>();
        List<OrderDetailBean> updateBeans = new ArrayList<>();
        for (OrderDetailBean orderDetailBean : list) {
            if (orderDetailBean.getId() == null) {
                orderDetailBean.setEntryDt(new Date());
                orderDetailBean.setEntryId(optUser.getId());
                orderDetailBean.setEntryName(optUser.getRealName());
                orderDetailBean.setUpdateDt(new Date());
                orderDetailBean.setUpdateId(optUser.getId());
                orderDetailBean.setUpdateName(optUser.getRealName());
                orderDetailBean.setTotalMoney(orderDetailBean.getPrice() * orderDetailBean.getAmount());
                insertBeans.add(orderDetailBean);
            }else {
                orderDetailBean.setUpdateDt(new Date());
                orderDetailBean.setUpdateId(optUser.getId());
                orderDetailBean.setUpdateName(optUser.getRealName());
                orderDetailBean.setTotalMoney(orderDetailBean.getPrice() * orderDetailBean.getAmount());
                updateBeans.add(orderDetailBean);
            }
        }
        int update = orderDao.update(orderBean);
        int batchUpdate = orderDetailDao.batchUpdateOrderDetail(BatchUpdateParameter.wrap(updateBeans));
        int lines = orderDetailDao.batchSaveOrderDetails(BatchInsertParameter.wrap(insertBeans));


        return update == 1 && batchUpdate+lines == list.size();
    }
}
