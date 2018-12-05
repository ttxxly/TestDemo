package com.skm.demo.service.impl;

import com.skm.demo.domain.OrderDetailBean;
import com.skm.demo.persistence.DTO.OrderDetailDeleteDTO;
import com.skm.demo.persistence.DTO.OrderDetailObtainDTO;
import com.skm.demo.persistence.dao.OrderDetailDao;
import com.skm.demo.service.OrderDetailService;
import com.skm.demo.web.vo.OrderDetailVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("myOrderDetailServiceImpl")
public class OrderDetailServiceImpl implements OrderDetailService {

    private OrderDetailDao<OrderDetailBean> orderDetailDao;

    @Autowired
    public OrderDetailServiceImpl(OrderDetailDao<OrderDetailBean> orderDetailDao) {
        this.orderDetailDao = orderDetailDao;
    }

    @Override
    public List<OrderDetailBean> getOrderDetailByNo(OrderDetailObtainDTO dto) {
        return orderDetailDao.getOrderDetailByNo(dto.getNo());
    }

    @Override
    public List<OrderDetailBean> deleteOrderDetailByNoAndCode(OrderDetailDeleteDTO dto) {
        return orderDetailDao.deleteOrderDetailByNoAndCode(dto.getNo(), dto.getCode());
    }
}
