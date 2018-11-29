package com.skm.demo.web;

import com.skm.common.bean.dto.Result;
import com.skm.common.bean.utils.BeanMapper;
import com.skm.common.spring.advisor.BaseController;
import com.skm.demo.domain.OrderBean;
import com.skm.demo.domain.OrderDetailBean;
import com.skm.demo.persistence.DTO.OrderSaveDTO;
import com.skm.demo.service.OrderDetailService;
import com.skm.demo.service.OrderService;
import com.skm.demo.web.vo.OrderDetailSaveVo;
import com.skm.demo.web.vo.OrderDetailVO;
import com.skm.demo.web.vo.OrderSaveVo;
import com.skm.demo.web.vo.OrderVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/web/v1/orderDetail")
public class OrderDetailController extends BaseController {

    private OrderDetailService orderDetailService;

    @Autowired
    public OrderDetailController(OrderDetailService orderDetailService) {
        this.orderDetailService = orderDetailService;
    }


    /**
     * 添加订单
     *
     * @param no 订单号
     * @return 结果集
     */
    @PostMapping(value = "/getOrderDetailByNo")
    public Result<List<OrderDetailVO>> getOrderDetailByNo(String no) {

        List<OrderDetailBean> orderDetailBeans = orderDetailService.getOrderDetailByNo(no);

        List<OrderDetailVO> list = BeanMapper.mapList(orderDetailBeans, OrderDetailBean.class, OrderDetailVO.class);
        return Result.success(list);
    }
}
