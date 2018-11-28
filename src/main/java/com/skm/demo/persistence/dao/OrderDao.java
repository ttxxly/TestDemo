package com.skm.demo.persistence.dao;

import com.skm.common.bean.BaseBean;
import com.skm.common.bean.dto.Page;
import com.skm.common.mybatis.dao.BaseDao;
import com.skm.common.mybatis.dto.BatchInsertParameter;
import com.skm.demo.domain.OrderBean;
import com.skm.demo.domain.OrderDetailBean;
import com.skm.demo.domain.UserBean;
import com.skm.demo.persistence.DTO.OrderQueryDTO;
import com.skm.demo.web.vo.OrderTemp;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("myOrderDao")
public interface OrderDao<T> extends BaseDao<OrderBean> {

    /**
     * 分页查询，查询后数据会直接存储在page参数的datas中，同时填写上分页数据
     *
     * @param page 分页查询对象
     * @return 查询结果集
     */
    List<OrderQueryDTO> dynamicSelectOrder(Page<T> page);

    /**
     * 新增实体
     *
     * @param entity 需要新增的实体
     * @return 行数
     */
    int saveOrder(T entity);

}
