package com.skm.demo.persistence.dao;

import com.skm.common.mybatis.dao.BaseDao;
import com.skm.demo.domain.UserBean;
import org.springframework.stereotype.Repository;

@Repository("myUserDao")
public interface UserDao extends BaseDao<UserBean> {


}
