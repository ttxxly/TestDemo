package com.skm.demo.domain;

import java.io.Serializable;

import java.util.Date;
import com.skm.common.bean.BaseBean;

/**
  *  <p>用户表
  *
  *  <p>作者: tractor
  */
public class UserBean extends BaseBean implements Serializable{
	private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Long id;

    /**
     * 租户 id
     */
    private Long tenantId;
    /**
     * 企业 id
     */
    private Long companyId;
    /**
     * 部门 id
     */
    private Long deptId;
    /**
     * 用户类型：0 管理员，1 普通用户
     */
    private Integer type;
    /**
     * 用户名称
     */
    private String name;
    /**
     * 用户真实姓名
     */
    private String realName;
    /**
     * 用户默认语言
     */
    private String lang;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 密码
     */
    private String password;
    /**
     * 状态：0：未激活；1：已激活；2：禁用
     */
    private Integer status;
    /**
     * 性别 0：男，1：女，2：未知性
     */
    private Integer gender;
    /**
     * 生日
     */
    private Date birthday;
    /**
     * 电话
     */
    private String tel;
    /**
     * 备注
     */
    private String remark;
    /**
     * 登录错误次数
     */
    private Integer loginErrorNum;
    /**
     * 登录验证码
     */
    private String verifyCode;

    /**
     * Set {@link #id}
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets {@link #id}
     *
     * @return value of id
     */
    public Long getId() {
        return this.id;
    }

    /**
     * Set {@link #tenantId}
     */
    public void setTenantId(Long tenantId) {
        this.tenantId = tenantId;
    }

    /**
     * Gets {@link #tenantId}
     *
     * @return value of tenantId
     */
    public Long getTenantId() {
        return this.tenantId;
    }

    /**
     * Set {@link #companyId}
     */
    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    /**
     * Gets {@link #companyId}
     *
     * @return value of companyId
     */
    public Long getCompanyId() {
        return this.companyId;
    }

    /**
     * Set {@link #deptId}
     */
    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }

    /**
     * Gets {@link #deptId}
     *
     * @return value of deptId
     */
    public Long getDeptId() {
        return this.deptId;
    }

    /**
     * Set {@link #type}
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * Gets {@link #type}
     *
     * @return value of type
     */
    public Integer getType() {
        return this.type;
    }

    /**
     * Set {@link #name}
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets {@link #name}
     *
     * @return value of name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Set {@link #realName}
     */
    public void setRealName(String realName) {
        this.realName = realName;
    }

    /**
     * Gets {@link #realName}
     *
     * @return value of realName
     */
    public String getRealName() {
        return this.realName;
    }

    /**
     * Set {@link #lang}
     */
    public void setLang(String lang) {
        this.lang = lang;
    }

    /**
     * Gets {@link #lang}
     *
     * @return value of lang
     */
    public String getLang() {
        return this.lang;
    }

    /**
     * Set {@link #email}
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets {@link #email}
     *
     * @return value of email
     */
    public String getEmail() {
        return this.email;
    }

    /**
     * Set {@link #password}
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Gets {@link #password}
     *
     * @return value of password
     */
    public String getPassword() {
        return this.password;
    }

    /**
     * Set {@link #status}
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * Gets {@link #status}
     *
     * @return value of status
     */
    public Integer getStatus() {
        return this.status;
    }

    /**
     * Set {@link #gender}
     */
    public void setGender(Integer gender) {
        this.gender = gender;
    }

    /**
     * Gets {@link #gender}
     *
     * @return value of gender
     */
    public Integer getGender() {
        return this.gender;
    }

    /**
     * Set {@link #birthday}
     */
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    /**
     * Gets {@link #birthday}
     *
     * @return value of birthday
     */
    public Date getBirthday() {
        return this.birthday;
    }

    /**
     * Set {@link #tel}
     */
    public void setTel(String tel) {
        this.tel = tel;
    }

    /**
     * Gets {@link #tel}
     *
     * @return value of tel
     */
    public String getTel() {
        return this.tel;
    }

    /**
     * Set {@link #remark}
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * Gets {@link #remark}
     *
     * @return value of remark
     */
    public String getRemark() {
        return this.remark;
    }

    /**
     * Set {@link #loginErrorNum}
     */
    public void setLoginErrorNum(Integer loginErrorNum) {
        this.loginErrorNum = loginErrorNum;
    }

    /**
     * Gets {@link #loginErrorNum}
     *
     * @return value of loginErrorNum
     */
    public Integer getLoginErrorNum() {
        return this.loginErrorNum;
    }

    /**
     * Set {@link #verifyCode}
     */
    public void setVerifyCode(String verifyCode) {
        this.verifyCode = verifyCode;
    }

    /**
     * Gets {@link #verifyCode}
     *
     * @return value of verifyCode
     */
    public String getVerifyCode() {
        return this.verifyCode;
    }

    public void fillDefaults() {
        if(this.name == null) {
            this.name = "";
        }
        if(this.realName == null) {
            this.realName = "";
        }
        if(this.lang == null) {
            this.lang = "";
        }
        if(this.email == null) {
            this.email = "";
        }
        if(this.password == null) {
            this.password = "";
        }
        if(this.tel == null) {
            this.tel = "";
        }
        if(this.loginErrorNum == null) {
            this.loginErrorNum = 0;
        }
    }
}
