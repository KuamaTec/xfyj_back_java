package com.zgds.xfyj.enums;

/**
 * Created by ASUS-LXP on 2019/10/28.
 * 订单状态枚举
 */
public enum OrderStatusEnum {

    NO_PAY(30000, "未支付"),
    PAY_SUCCESS_TRADE_QUERY(30001, "同步交易查询-支付成功"),//支付完成后，同步交易查询-支付完成（成功）
    PAY_FAIL_TRADE_QUERY(30002, "同步交易查询-支付失败"),//支付完成后，同步交易查询-支付完成（失败）
    PAY_SUCCESS_ALI_PAY(30003, "阿里后台异步-支付成功"),//支付宝后台异步通知 支付-成功
    PAY_FAIL_ALI_PAY(30004, "阿里后台异步-支付失败"),//支付宝后台异步通知 支付-失败
    PAY_FAIL(30005, "支付失败"),//最终支付结果-支付失败，包括支付超时等
    PAY_CANCEL(30006, "支付取消"),
    WAIT_SEND(30007, "待发货"),//待发货
    ALREADY_SEND(30008, "已发货"),//已发货
    ALREADY_RECEIVED(30009, "已收货"),//已收货
    ORDER_COMPLETE(30010, "已完成"),//已完成
    SALES_RETURN(30011, "退货");//退货

    private Integer pay_status_code;//支付状态码
    private String pay_status_desc;//支付状态描述

    OrderStatusEnum(Integer oper_status_code, String oper_status_desc) {
        this.pay_status_code = oper_status_code;
        this.pay_status_desc = oper_status_desc;
    }

    public Integer getPay_status_code() {
        return pay_status_code;
    }

    public void setPay_status_code(Integer pay_status_code) {
        this.pay_status_code = pay_status_code;
    }

    public String getPay_status_desc() {
        return pay_status_desc;
    }

    public void setPay_status_desc(String pay_status_desc) {
        this.pay_status_desc = pay_status_desc;
    }

    OrderStatusEnum() {
    }

    public static String getOperStatusDesc(Integer oper_status_code) {
        OrderStatusEnum[] operStatusCodeEnums = OrderStatusEnum.values();
        for (OrderStatusEnum operStatusCode : operStatusCodeEnums) {
            if (operStatusCode.pay_status_code.equals(oper_status_code)) {
                return operStatusCode.getPay_status_desc();
            }
        }
        return null;
    }

    public static Integer getDiscussType(String oper_status_desc) {
        OrderStatusEnum[] operStatusCodeEnums = OrderStatusEnum.values();
        for (OrderStatusEnum operStatusCode : operStatusCodeEnums) {
            if (operStatusCode.getPay_status_desc().equals(oper_status_desc)) {
                return operStatusCode.getPay_status_code();
            }
        }
        return null;
    }
}
