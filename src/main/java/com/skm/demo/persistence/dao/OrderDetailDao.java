package com.skm.demo.persistence.dao;

import com.skm.common.mybatis.dao.BaseDao;
import com.skm.common.mybatis.dto.BatchInsertParameter;
import com.skm.demo.domain.OrderBean;
import com.skm.demo.domain.OrderDetailBean;
import com.skm.demo.persistence.DTO.OrderQueryDTO;
import com.skm.demo.web.vo.OrderTemp;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("myOrderDetailDao")
public interface OrderDetailDao<T> extends BaseDao<OrderBean> {

    List<OrderQueryDTO> getNumAndMoneyByNos(List<OrderDetailBean> orderDetailBeans);

    /**
     * 批量保存
     *
     * @param entity 需要保存的实体
     * @return 行数
     */
    int batchSaveOrderDetails(BatchInsertParameter<T> entity);

    List<OrderDetailBean> getOrderDetailByNo(String no);

    List<OrderDetailBean> getOrderDetailByCode(List<OrderDetailBean> orderDetailBeans);
}
