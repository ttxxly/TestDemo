package com.skm.demo.service;

import com.skm.demo.domain.OrderDetailBean;
import com.skm.demo.persistence.DTO.OrderDetailDeleteDTO;
import com.skm.demo.persistence.DTO.OrderDetailObtainDTO;
import com.skm.demo.web.vo.OrderDetailVO;

import java.util.List;

public interface OrderDetailService {

    List<OrderDetailBean> getOrderDetailByNo(OrderDetailObtainDTO dto);

    List<OrderDetailBean> deleteOrderDetailByNoAndCode(OrderDetailDeleteDTO dto);
}
