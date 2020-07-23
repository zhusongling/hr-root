package com.ling.hr.base.enums;

public enum TradeStatus {
    // 1=待支付；2=支付中；3=支付成功；4=订单取消；5=交易关闭；6=转入退款; 7=交易不存在; 8=三方业务处理；9=数据返回错误；10=支付失败
    CREATE(1), PAYING(2), PAID(3), CANCEL(4), CLOSED(5), REFUND(6), NOT_EXISTENT(7), BUSINESS_ERROR(8), DATA_ERROR(9), PAY_FAIL(10), FINISH(11);
    private Integer value;

    TradeStatus(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }
}
