package com.skm.demo.domain;

import com.skm.common.bean.BaseBean;


public class OrderDetailBean extends BaseBean {
    /**
     * 主键id
     */
    private Long id;

    /**
     * 订单单号
     */
    private String order_no;

    /**
     * 商品编码
     */
    private String product_code;
    /**
     * 商品名称
     */
    private String product_name;

    /**
     * 商品总数量
     */
    private Long amount;

    public OrderDetailBean() {
    }

    @Override
    public String toString() {
        return "OrderDetailBean{" +
                "id=" + id +
                ", order_no='" + order_no + '\'' +
                ", product_code='" + product_code + '\'' +
                ", product_name='" + product_name + '\'' +
                ", amount=" + amount +
                '}';
    }

    public OrderDetailBean(Long id, String order_no, String product_code, String product_name, Long amount) {
        this.id = id;
        this.order_no = order_no;
        this.product_code = product_code;
        this.product_name = product_name;
        this.amount = amount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrder_no() {
        return order_no;
    }

    public void setOrder_no(String order_no) {
        this.order_no = order_no;
    }

    public String getProduct_code() {
        return product_code;
    }

    public void setProduct_code(String product_code) {
        this.product_code = product_code;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }
}
