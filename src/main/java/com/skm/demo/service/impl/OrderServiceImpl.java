package com.skm.demo.service.impl;

import com.skm.common.bean.dto.Page;
import com.skm.common.bean.dto.UnifyUser;
import com.skm.common.bean.utils.BeanMapper;
import com.skm.common.mybatis.config.ITransactional;
import com.skm.common.mybatis.dto.BatchInsertParameter;
import com.skm.demo.domain.OrderBean;
import com.skm.demo.domain.OrderDetailBean;
import com.skm.demo.persistence.DTO.OrderQueryDTO;
import com.skm.demo.persistence.DTO.OrderSaveDTO;
import com.skm.demo.persistence.dao.OrderDao;
import com.skm.demo.persistence.dao.OrderDetailDao;
import com.skm.demo.persistence.qo.OrderQo;
import com.skm.demo.service.OrderService;
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
            List<OrderDetailBean> orderDetailBeans = new ArrayList<>();
            OrderDetailBean orderDetailBean = new OrderDetailBean();
            for (OrderBean orderBean : queryDTO) {
                orderDetailBean.setOrderNo(orderBean.getNo());
                orderDetailBeans.add(orderDetailBean);
            }
            List<OrderQueryDTO> queryDTOList = orderDetailDao.getNumAndMoneyByNos(orderDetailBeans);
            OrderBean orderBean;
            for (OrderQueryDTO orderQueryDTO : queryDTOList) {
                for (int i = 0; i<queryDTO.size(); i++) {
                    if (queryDTO.get(i).getNo().equals(orderQueryDTO.getOrderNo())) {
                        queryDTO.get(i).setProductNum(orderQueryDTO.getProductNum());
                        queryDTO.get(i).setProductTypeNum(orderQueryDTO.getProductTypeNum());
                        queryDTO.get(i).setTotalMoney(orderQueryDTO.getTotalMoney());
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
    public OrderBean save(OrderSaveDTO orderSaveDTO, UnifyUser optUser) {

        OrderBean orderBean = BeanMapper.map(orderSaveDTO, OrderBean.class);
        orderBean.setEntryDt(new Date());
        orderBean.setEntryId(optUser.getId());
        orderBean.setEntryName(optUser.getRealName());
        orderBean.setUpdateDt(new Date());
        orderBean.setUpdateId(optUser.getId());
        orderBean.setUpdateName(optUser.getRealName());
        orderDao.saveOrder(orderBean);

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
        orderDetailDao.batchSaveOrderDetails(BatchInsertParameter.wrap(orderDetailBeans));
        return orderBean;
    }
}
