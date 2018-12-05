package com.skm.demo.web;

import com.skm.common.bean.dto.Page;
import com.skm.common.bean.dto.PageParam;
import com.skm.common.bean.dto.Result;
import com.skm.common.bean.dto.UnifyUser;
import com.skm.common.bean.utils.BeanMapper;
import com.skm.common.spring.advisor.BaseController;
import com.skm.demo.domain.OrderBean;
import com.skm.demo.domain.OrderDetailBean;
import com.skm.demo.persistence.DTO.OrderAndOrderDetailShowDTO;
import com.skm.demo.persistence.DTO.OrderQueryDTO;
import com.skm.demo.persistence.DTO.OrderSaveDTO;
import com.skm.demo.persistence.DTO.OrderUpdateDTO;
import com.skm.demo.persistence.qo.OrderQo;
import com.skm.demo.service.OrderService;
import com.skm.demo.web.vo.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
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
@Api(tags = "订单接口", description = "对订单进行操作")
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
    @PostMapping("/page")
    @ApiOperation("动态查询订单列表")
    public Result<Page<OrderVo>> page(@RequestBody PageParam<OrderQueryVo> pageParam) {
        int pn = pageParam.getPn();
        int ps = pageParam.getPs();
        UnifyUser currentUser = getCurrentUser();
        OrderQo orderQo = Optional.of(pageParam.getConditions())
                .map(cond -> BeanMapper.map(cond, OrderQo.class))
                .orElse(null);

        Page<OrderQueryDTO> dtoPage = orderService.list(orderQo, ps, pn, currentUser);

        List<OrderVo> orderVos = BeanMapper.mapList(dtoPage.getDatas(), OrderQueryDTO.class, OrderVo.class);
        Page<OrderVo> page = new Page<>();
        page.setTc(dtoPage.getTc());
        page.setDatas(orderVos);
        page.setPn(dtoPage.getPn());
        page.setPs(dtoPage.getPs());

        return Result.success(page);
    }

    /**
     * 添加订单
     *
     * @param saveVo 保存订单对象
     * @return 结果集
     */
    @PostMapping("/add")
    @ApiOperation("添加订单")
    public Result<OrderSaveVo> add(@Validated @RequestBody OrderSaveVo saveVo) {

        OrderSaveDTO orderSaveDTO = BeanMapper.map(saveVo, OrderSaveDTO.class);
        orderSaveDTO.setList(BeanMapper.mapList(saveVo.getBeans(), OrderDetailSaveVo.class, OrderDetailBean.class));

        OrderSaveDTO saveDTO = orderService.save(orderSaveDTO, getCurrentUser());
        OrderSaveVo orderSaveVo = BeanMapper.map(saveDTO, OrderSaveVo.class);
        orderSaveVo.setBeans(BeanMapper.mapList(saveDTO.getList(), OrderDetailBean.class, OrderDetailSaveVo.class));

        return Result.success(orderSaveVo);
    }

    /**
     * 获取订单号
     *
     * @return 订单号
     */
    @PostMapping("/getOrderNum")
    @ApiOperation("获取订单号")
    public Result<OrderNumVO> getOrderNum() {
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmssSS");//设置日期格式
        String no = df.format(new Date());
        OrderNumVO vo = new OrderNumVO();
        vo.setNo(no);
        return Result.success(vo);
    }

    /**
     * 更新订单
     *
     * @return 操作代码：0表示更新失败，1表示更新成功
     */
    @PostMapping("/updateOrder")
    @ApiOperation("更新订单")
    public Result<Boolean> updateOrder(OrderUpdateVo orderUpdateVo) {

        OrderUpdateDTO orderUpdateDTO;
        orderUpdateDTO = BeanMapper.map(orderUpdateVo, OrderUpdateDTO.class);
        orderUpdateDTO.setOrderDetailBeans(BeanMapper.mapList(orderUpdateVo.getBeans(), OrderDetailSaveVo.class, OrderDetailBean.class));
        Boolean aBoolean = orderService.updateOrder(orderUpdateDTO, getCurrentUser());

        return Result.success(aBoolean);
    }

    /**
     * 数据回显订单的信息
     *
     * @param vo 订单号对象
     * @return 操作代码：1表示数据回显成功，0表示失败
     */
    @PostMapping("/showOrderAndOrderDetail")
    @ApiOperation("显示订单单头和明细")
    public Result<OrderShowVO> showOrderAndOrderDetail(OrderAndOrderDetailShowVO vo) {
        OrderShowVO orderShowVO;
        OrderAndOrderDetailShowDTO dto = BeanMapper.map(vo, OrderAndOrderDetailShowDTO.class);
        OrderUpdateDTO orderUpdateDTO = orderService.showOrderAndOrderDetail(dto);
        orderShowVO = BeanMapper.map(orderUpdateDTO, OrderShowVO.class);
        orderShowVO.setOrderDetails(BeanMapper.mapList(orderUpdateDTO.getOrderDetailBeans(), OrderDetailBean.class, OrderDetailVO.class));

        return Result.success(orderShowVO);
    }

}
