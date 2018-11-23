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

    @Autowired
    private OrderService orderService;

    /**
     * 查询订单
     *
     * @param pageParam
     * @return
     */
    //to do OrderQueryVo 加上 LIke
    @PostMapping(value = "/page")
    public Result<Page<OrderVo>> page(@RequestBody PageParam<OrderQueryVo> pageParam) {
        int pn = pageParam.getPn();
        int ps = pageParam.getPs();
        UnifyUser currentUser = getCurrentUser();
        OrderQo orderQo = Optional.of(pageParam.getConditions()).map(cond -> {
            return BeanMapper.map(cond, OrderQo.class);
        }).orElse(null);

        Page<OrderBean> beanPage = orderService.list(orderQo, ps, pn, currentUser);

        List<OrderVo> orderVos = BeanMapper.mapList(beanPage.getDatas(), OrderBean.class, OrderVo.class);
        //获取指定订单号的商品种类数量和总数量
        for (int i=0; i<orderVos.size(); i++) {
            OrderTemp orderTemp = orderService.getProductTypeNumAndProductNumByNo(orderVos.get(i).getNo());
            orderVos.get(i).setProductTypeNum(orderTemp.getProductTypeNum());
            orderVos.get(i).setProductNum(orderTemp.getProductNum());
        }
        Page<OrderVo> page = new Page<>(beanPage.getPn(), beanPage.getPs());
        page.setTc(beanPage.getTc());
        page.setDatas(orderVos);

        return Result.success(page);
    }

    /**
     * 添加订单
     *
     * @param
     * @return
     */
    @PostMapping(value = "/add")
    //
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
     * @return
     */
    @PostMapping(value = "/getOrderNum")
    public String getOrderNum() {
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");//设置日期格式
        return df.format(new Date());
    }
}
