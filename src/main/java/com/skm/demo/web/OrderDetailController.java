package com.skm.demo.web;

import com.skm.common.bean.dto.Page;
import com.skm.common.bean.dto.PageParam;
import com.skm.common.bean.dto.Result;
import com.skm.common.spring.advisor.BaseController;
import com.skm.demo.web.vo.UserQueryVo;
import com.skm.demo.web.vo.UserVo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/web/v1/order_detail")
public class OrderDetailController extends BaseController {

    @PostMapping(value = "/add")
    public Result<Page<UserVo>> add(@RequestBody PageParam<UserQueryVo> pageParam) {
        int pn = pageParam.getPn();
        int ps = pageParam.getPs();

        return Result.success();
    }

    public Result<Page<UserVo>> update(@RequestBody PageParam<UserQueryVo> pageParam) {
        int pn = pageParam.getPn();
        int ps = pageParam.getPs();

        return Result.success();
    }
}
