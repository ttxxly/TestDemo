package com.skm.demo.web;

import com.skm.common.bean.dto.Page;
import com.skm.common.bean.dto.PageParam;
import com.skm.common.bean.dto.Result;
import com.skm.common.bean.dto.UnifyUser;
import com.skm.common.bean.utils.BeanMapper;
import com.skm.common.spring.advisor.BaseController;
import com.skm.demo.domain.UserBean;
import com.skm.demo.persistence.qo.UserQO;
import com.skm.demo.service.ProductService;
import com.skm.demo.service.UserService;
import com.skm.demo.web.vo.UserQueryVo;
import com.skm.demo.web.vo.UserVo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/web/v1/product")
public class ProductController extends BaseController {

    private ProductService productService;

    @PostMapping(value = "/page")
    public Result<Page<UserVo>> page(@RequestBody PageParam<UserQueryVo> pageParam) {
        int pn = pageParam.getPn();
        int ps = pageParam.getPs();

        return Result.success();
    }

    @PostMapping(value = "/getAll")
    public Result<Page<UserVo>> getAll(@RequestBody PageParam<UserQueryVo> pageParam) {
        int pn = pageParam.getPn();
        int ps = pageParam.getPs();

        return Result.success();
    }

    @PostMapping(value = "/add")
    public Result<Page<UserVo>> add(@RequestBody PageParam<UserQueryVo> pageParam) {
        int pn = pageParam.getPn();
        int ps = pageParam.getPs();

        return Result.success();
    }
}
