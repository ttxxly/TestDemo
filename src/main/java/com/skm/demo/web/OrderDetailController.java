package com.skm.demo.web;

import com.skm.common.bean.dto.Result;
import com.skm.common.bean.utils.BeanMapper;
import com.skm.common.spring.advisor.BaseController;
import com.skm.demo.domain.OrderDetailBean;
import com.skm.demo.persistence.DTO.OrderDetailDeleteDTO;
import com.skm.demo.persistence.DTO.OrderDetailObtainDTO;
import com.skm.demo.service.OrderDetailService;
import com.skm.demo.web.vo.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/web/v1/orderDetail")
@Api(tags = "订单明细接口", description = "操作订单明细表")
public class OrderDetailController extends BaseController {

    private OrderDetailService orderDetailService;

    @Autowired
    public OrderDetailController(OrderDetailService orderDetailService) {
        this.orderDetailService = orderDetailService;
    }


    /**
     * 添加订单
     *
     * @param vo 订单号
     * @return 结果集
     */
    @PostMapping("/getOrderDetailByNo")
    @ApiOperation("通过订单号获取订单明细列表")
    public Result<List<OrderDetailVO>> getOrderDetailByNo(OrderDetailObtainVO vo) {

        OrderDetailObtainDTO dto = BeanMapper.map(vo, OrderDetailObtainDTO.class);
        List<OrderDetailBean> orderDetailBeans = orderDetailService.getOrderDetailByNo(dto);

        List<OrderDetailVO> list = BeanMapper.mapList(orderDetailBeans, OrderDetailBean.class, OrderDetailVO.class);
        return Result.success(list);
    }

    @PostMapping("/deleteOrderDetailByNo")
    @ApiOperation("通过订单号和商品编码删除该订单明细记录")
    public Result<List<OrderDetailVO>> deleteOrderDetailByNoAndCode(OrderDetailDeleteVO vo) {

        OrderDetailDeleteDTO dto = BeanMapper.map(vo, OrderDetailDeleteDTO.class);
        List<OrderDetailBean> orderDetailBeans = orderDetailService.deleteOrderDetailByNoAndCode(dto);

        List<OrderDetailVO> list = BeanMapper.mapList(orderDetailBeans, OrderDetailBean.class, OrderDetailVO.class);
        return Result.success(list);
    }
}
