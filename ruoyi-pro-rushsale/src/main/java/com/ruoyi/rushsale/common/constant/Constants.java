package com.ruoyi.rushsale.common.constant;

public class Constants {
    //-------------------------抢购信息修改 ----------------------------
    public static String UPDATE_ORDERSTATE = "orderstate";//订单状态
    public static String UPDATE_RUSHSTATE = "rushState";//抢购单流转状态
    public static String UPDATE_DEALSTATE = "dealState";//结算状态
    public static String PURCHASEINFO_UPDATE_ORDERNUM = "orderNum";//选择收货人状态
    public static String PURCHASEINFO_UPDATE_other = "other";//其他状态

    //-----------------交易账户列
    public static String UPDATE_ACCOUNTNUM= "accountNum";//交易账户修改
    public static String UPDATE_DEALCANCEL= "dealcancel";//交易取消

 //-------------------------订单状态 ----------------------------
    public static String ORDERSTATUS_SEND = "send";//发出
    public static String ORDERSTATUS_ARRIVE = "arrive";//到达
    public static String ORDERSTATUS_OUT = "out";//转出
    public static String ORDERSTATUS_REUTRN = "return";//退回
    //-------------------------交易信息购买类型 ----------------------------
    public static String DEAL_TYPE_BUY = "buy";//我为买方
    public static String DEAL_TYPE_SALE = "sale";//我为卖方
    //-------------------------交易信息状态 ----------------------------
    public static String DEAL_STATUS_VALID = "1";//有效
    public static String DEAL_STATUS_INVALID = "0";//有效

    //-------------------------结算信息状态 ----------------------------
    public static String DEAL_STATUS_NOT = "0";//未结算
    public static String DEAL_STATUS_COMPLETE = "1";//已结算
    public static String DEAL_STATUS_FAIL = "2";//结算失败
    public static String DEAL_STATUS_BACK = "3";//退货成功


}
