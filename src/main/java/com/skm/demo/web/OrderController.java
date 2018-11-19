package com.skm.demo.web;

import com.skm.common.bean.dto.Page;
import com.skm.common.bean.dto.PageParam;
import com.skm.common.bean.dto.Result;
import com.skm.common.bean.dto.UnifyUser;
import com.skm.common.bean.utils.BeanMapper;
import com.skm.common.spring.advisor.BaseController;
import com.skm.demo.domain.UserBean;
import com.skm.demo.persistence.qo.UserQO;
import com.skm.demo.service.OrderService;
import com.skm.demo.web.vo.UserQueryVo;
import com.skm.demo.web.vo.UserVo;
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

    @PostMapping(value = "/page")
    public Result<Page<UserVo>> page(@RequestBody PageParam<UserQueryVo> pageParam) {
        int pn = pageParam.getPn();
        int ps = pageParam.getPs();
        UnifyUser currentUser = getCurrentUser();
        UserQO userQO = Optional.of(pageParam.getConditions()).map(cond -> {
            return BeanMapper.map(cond, UserQO.class);
        }).orElse(null);

        Page<UserBean> beanPage = orderService.list(userQO, ps, pn, currentUser);

        List<UserVo> userVos = BeanMapper.mapList(beanPage.getDatas(), UserBean.class, UserVo.class);
        Page<UserVo> page = new Page<>(beanPage.getPn(), beanPage.getPs());
        page.setTc(beanPage.getTc());
        page.setDatas(userVos);

        return Result.success(page);
    }

    @PostMapping(value = "/add")
    public Result<Page<UserVo>> add(@RequestBody PageParam<UserQueryVo> pageParam) {
        int pn = pageParam.getPn();
        int ps = pageParam.getPs();

        return Result.success();
    }

    private String getOrderNum(){
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");//设置日期格式
        return df.format(new Date());
    }
}
