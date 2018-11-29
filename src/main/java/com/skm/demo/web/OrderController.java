package com.skm.demo.web;

import com.skm.common.bean.dto.Page;
import com.skm.common.bean.dto.PageParam;
import com.skm.common.bean.dto.Result;
import com.skm.common.bean.dto.UnifyUser;
import com.skm.common.bean.utils.BeanMapper;
import com.skm.common.spring.advisor.BaseController;
import com.skm.demo.domain.OrderBean;
import com.skm.demo.domain.OrderDetailBean;
import com.skm.demo.persistence.DTO.OrderQueryDTO;
import com.skm.demo.persistence.DTO.OrderSaveDTO;
import com.skm.demo.persistence.DTO.OrderUpdateDTO;
import com.skm.demo.persistence.qo.OrderQo;
import com.skm.demo.service.OrderService;
import com.skm.demo.web.vo.*;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/web/v1/order")
public class OrderController extends BaseController {

    private OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    /**
     * 查询订单
     *
     * @param pageParam 分页查询对象
     * @return 结果集
     */
    @PostMapping(value = "/page")
    public Result<Page<OrderVo>> page(@RequestBody PageParam<OrderQueryVo> pageParam) {
        int pn = pageParam.getPn();
        int ps = pageParam.getPs();
        UnifyUser currentUser = getCurrentUser();
        OrderQo orderQo = Optional.of(pageParam.getConditions())
                .map(cond -> BeanMapper.map(cond, OrderQo.class))
                .orElse(null);

        Page<OrderQueryDTO> dtoPage = orderService.list(orderQo, ps, pn, currentUser);
        List<OrderVo> orderVos = BeanMapper.mapList(dtoPage.getDatas(), OrderQueryDTO.class, OrderVo.class);
        Page<OrderVo> page = new Page<>(dtoPage.getPn(), dtoPage.getPs());
        page.setTc(dtoPage.getTc());
        page.setDatas(orderVos);

        return Result.success(page);
    }

    /**
     * 添加订单
     *
     * @param saveVo 保存订单对象
     * @return 结果集
     */
    @PostMapping(value = "/add")
    public Result<OrderVo> add(@Validated @RequestBody OrderSaveVo saveVo) {

        OrderSaveDTO orderSaveDTO = BeanMapper.map(saveVo, OrderSaveDTO.class);
        orderSaveDTO.setList(BeanMapper.mapList(saveVo.getBeans(), OrderDetailSaveVo.class, OrderDetailBean.class));
        OrderBean save = orderService.save(orderSaveDTO, getCurrentUser());
        OrderVo savedVo = BeanMapper.map(save, OrderVo.class);
        return Result.success(savedVo);
    }

    /**
     * 获取订单号
     *
     * @return 订单号
     */
    @PostMapping(value = "/getOrderNum")
    public String getOrderNum() {
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmssSS");//设置日期格式
        return df.format(new Date());
    }

    /**
     * 更新订单
     *
     * @return 操作代码：0表示更新失败，1表示更新成功
     */
    @PostMapping(value = "/updateOrder")
    public Integer updateOrder(OrderSaveVo orderSaveVo) {

        return 0;
    }

    /**
     * 数据回显订单的信息
     *
     * @param no 订单号
     * @return 操作代码：1表示数据回显成功，0表示失败
     */
    @PostMapping(value = "/showOrderAndOrderDetail")
    public OrderShowVO showOrderAndOrderDetail(String no) {
        OrderShowVO orderShowVO;

        OrderUpdateDTO orderUpdateDTO = orderService.showOrderAndOrderDetail(no);
        orderShowVO = BeanMapper.map(orderUpdateDTO, OrderShowVO.class);
        orderShowVO.setOrderDetailVOS(BeanMapper.mapList(orderUpdateDTO.getOrderDetailBeans(), OrderDetailBean.class, OrderDetailVO.class));

        return orderShowVO;
    }

}
