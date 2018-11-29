package com.skm.demo.service;

import com.skm.demo.domain.OrderDetailBean;
import com.skm.demo.web.vo.OrderDetailVO;

import java.util.List;

public interface OrderDetailService {

    List<OrderDetailBean> getOrderDetailByNo(String no);
}
