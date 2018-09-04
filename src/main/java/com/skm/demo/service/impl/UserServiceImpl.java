package com.skm.demo.service.impl;

import com.skm.common.bean.dto.DataPrivilege;
import com.skm.common.bean.dto.Page;
import com.skm.common.bean.dto.UnifyUser;
import com.skm.common.bean.exception.ApplicationException;
import com.skm.common.bean.exception.ErrorCode;
import com.skm.common.mybatis.config.ITransactional;
import com.skm.common.mybatis.dto.BatchInsertParameter;
import com.skm.demo.domain.UserBean;
import com.skm.demo.persistence.dao.UserDao;
import com.skm.demo.persistence.qo.UserQO;
import com.skm.demo.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Date;

import static com.skm.demo.enums.DataPrivilegeCode.USER_UPDATE;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao dao;

    @Override
    public Page<UserBean> list(UserQO qo, int ps, int pn, UnifyUser optUser) {
        Page<UserBean> page = new Page<>();
        page.setConditions(qo);
        page.setPn(pn);
        page.setPs(ps);
        dao.dynamicSelectPage(page);

        return page;
    }

    @Override
    @ITransactional
    public UserBean save(UserBean bean, UnifyUser optUser) {
        bean.setEntryDt(new Date());
        bean.setEntryId(optUser.getId());
        bean.setEntryName(optUser.getRealName());
        bean.setUpdateDt(new Date());
        bean.setUpdateId(optUser.getId());
        bean.setUpdateName(optUser.getRealName());
        dao.save(bean);

        return bean;
    }

    @Override
    @ITransactional
    public UserBean saveWithDefaults(UserBean bean, UnifyUser optUser) {
        bean.fillDefaults();

        return save(bean, optUser);
    }

    @Override
    @ITransactional
    public Integer batchSave(Collection<UserBean> beans, UnifyUser optUser) {
        for (UserBean bean : beans) {
          bean.setEntryDt(new Date());
          bean.setEntryId(optUser.getId());
          bean.setEntryName(optUser.getRealName());
          bean.setUpdateDt(new Date());
          bean.setUpdateId(optUser.getId());
          bean.setUpdateName(optUser.getRealName());
        }

        return dao.batchSave(BatchInsertParameter.wrap(beans));
    }

    @Override
    @ITransactional
    public Integer batchSaveWithDefaults(Collection<UserBean> beans, UnifyUser optUser) {
        for (UserBean bean : beans) {
          bean.fillDefaults();

          bean.setEntryDt(new Date());
          bean.setEntryId(optUser.getId());
          bean.setEntryName(optUser.getRealName());
          bean.setUpdateDt(new Date());
          bean.setUpdateId(optUser.getId());
          bean.setUpdateName(optUser.getRealName());
        }

        return dao.batchSave(BatchInsertParameter.wrap(beans));
    }

    @Override
    @ITransactional
    public UserBean update(UserBean bean, UnifyUser optUser) {
        bean.setUpdateDt(new Date());
        bean.setUpdateId(optUser.getId());
        bean.setUpdateName(optUser.getRealName());
        if(bean.getId() != null) {
            UserQO qo = new UserQO();
            BeanUtils.copyProperties(bean, qo);
            dao.dynamicUpdate(qo);
            return dao.get(bean.getId());
        }

        return null;
    }

    @Override
    @ITransactional
    public UserBean staticUpdate(UserBean bean, UnifyUser optUser) {
        bean.setUpdateDt(new Date());
        bean.setUpdateId(optUser.getId());
        bean.setUpdateName(optUser.getRealName());
        if(bean.getId() != null) {
            UserQO qo = new UserQO();
            BeanUtils.copyProperties(bean, qo);
            dao.update(qo);
            return dao.get(bean.getId());
        }

        return null;
    }

    @Override
    public UserBean getById(Long id) {
        UserBean bean = dao.get(id);

        return bean;
    }
}
