package com.skm.demo.web;

import com.skm.common.bean.dto.Page;
import com.skm.common.bean.dto.PageParam;
import com.skm.common.bean.dto.Result;
import com.skm.common.bean.dto.UnifyUser;
import com.skm.common.bean.utils.BeanMapper;
import com.skm.common.spring.advisor.BaseController;
import com.skm.demo.domain.OrderBean;
import com.skm.demo.domain.OrderDetailBean;
import com.skm.demo.domain.ProductBean;
import com.skm.demo.domain.UserBean;
import com.skm.demo.enums.UserStatus;
import com.skm.demo.persistence.DTO.OrderDTO;
import com.skm.demo.persistence.qo.OrderQo;
import com.skm.demo.persistence.qo.UserQO;
import com.skm.demo.service.OrderService;
import com.skm.demo.web.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
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
        if (pn == 0 || ps == 0) {
            return Result.error("", "页码或行数不能为零哦");
        }
        UnifyUser currentUser = getCurrentUser();
        OrderQo orderQo = Optional.of(pageParam.getConditions())
                .map(cond -> BeanMapper.map(cond, OrderQo.class))
                .orElse(null);

        Page<OrderBean> beanPage = orderService.list(orderQo, ps, pn, currentUser);

        List<OrderVo> orderVos = BeanMapper.mapList(beanPage.getDatas(), OrderBean.class, OrderVo.class);
        //获取指定订单号的商品种类数量和总数量
        OrderDetailBean orderDetailBean = new OrderDetailBean();
        for (OrderVo orderVo:orderVos) {
            orderDetailBean.setOrderNo(orderVo.getNo());
            OrderTemp orderTemp = orderService.getProductTypeNumAndProductNumByNo(orderDetailBean);
            orderVo.setProductTypeNum(orderTemp.getProductTypeNum());
            orderVo.setProductNum(orderTemp.getProductNum());
        }
        Page<OrderVo> page = new Page<>(beanPage.getPn(), beanPage.getPs());
        page.setTc(beanPage.getTc());
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

        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setOrderBean(BeanMapper.map(saveVo, OrderBean.class));
        orderDTO.setOrderDetailBeans(BeanMapper.mapList(saveVo.getBeans(), OrderDetailSaveVo.class, OrderDetailBean.class));

        OrderBean save = orderService.save(orderDTO, getCurrentUser());
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
}
