package com.ruoyi.rushsale.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 抢购人购买结算信息对象 pro_rush_purchaseinfo
 * 
 * @author ruoyi
 * @date 2023-03-24
 */
public class ProRushPurchaseinfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long purchaseId;

    /** 用户id */
    private Long userId;

    /** 部门id */
    private Long deptId;

    /** 购买人姓名 */
    @Excel(name = "购买人姓名")
    private String purchName;

    /** 购买人手机号 */
    @Excel(name = "购买人手机号")
    private String phoneNum;

    /** 购买商品id */
    @Excel(name = "购买商品id")
    private Long goodsId;

    /** 购买渠道 */
    @Excel(name = "购买渠道")
    private String buyFrom;

    /** 购买价格 */
    @Excel(name = "购买价格")
    private String buyPrice;

    /** 下单时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "下单时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date buyTime;

    /** 订单号-后六位 */
    @Excel(name = "订单号-后六位")
    private String orderNum;

    /** 机器唯一码（串码） */
    @Excel(name = "机器唯一码", readConverterExp = "串=码")
    private String machineId;

    /** 订单状态 */
    @Excel(name = "订单状态")
    private String orderState;

    /** 结算状态 */
    @Excel(name = "结算状态")
    private String dealState;

    /** 到货时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "到货时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date arrivalTime;

    /** 中间价 */
    @Excel(name = "中间价")
    private String dealPrice;

    /** 结算时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "结算时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date dealTime;

    /** 抢购流转状态 发货 到货 退货 */
    @Excel(name = "抢购流转状态 发货 到货 退货")
    private String rushState;

    public void setPurchaseId(Long purchaseId) 
    {
        this.purchaseId = purchaseId;
    }

    public Long getPurchaseId() 
    {
        return purchaseId;
    }
    public void setUserId(Long userId) 
    {
        this.userId = userId;
    }

    public Long getUserId() 
    {
        return userId;
    }
    public void setDeptId(Long deptId) 
    {
        this.deptId = deptId;
    }

    public Long getDeptId() 
    {
        return deptId;
    }
    public void setPurchName(String purchName) 
    {
        this.purchName = purchName;
    }

    public String getPurchName() 
    {
        return purchName;
    }
    public void setPhoneNum(String phoneNum) 
    {
        this.phoneNum = phoneNum;
    }

    public String getPhoneNum() 
    {
        return phoneNum;
    }
    public void setGoodsId(Long goodsId) 
    {
        this.goodsId = goodsId;
    }

    public Long getGoodsId() 
    {
        return goodsId;
    }
    public void setBuyFrom(String buyFrom) 
    {
        this.buyFrom = buyFrom;
    }

    public String getBuyFrom() 
    {
        return buyFrom;
    }
    public void setBuyPrice(String buyPrice) 
    {
        this.buyPrice = buyPrice;
    }

    public String getBuyPrice() 
    {
        return buyPrice;
    }
    public void setBuyTime(Date buyTime) 
    {
        this.buyTime = buyTime;
    }

    public Date getBuyTime() 
    {
        return buyTime;
    }
    public void setOrderNum(String orderNum) 
    {
        this.orderNum = orderNum;
    }

    public String getOrderNum() 
    {
        return orderNum;
    }
    public void setMachineId(String machineId) 
    {
        this.machineId = machineId;
    }

    public String getMachineId() 
    {
        return machineId;
    }
    public void setOrderState(String orderState) 
    {
        this.orderState = orderState;
    }

    public String getOrderState() 
    {
        return orderState;
    }
    public void setDealState(String dealState) 
    {
        this.dealState = dealState;
    }

    public String getDealState() 
    {
        return dealState;
    }
    public void setArrivalTime(Date arrivalTime) 
    {
        this.arrivalTime = arrivalTime;
    }

    public Date getArrivalTime() 
    {
        return arrivalTime;
    }
    public void setDealPrice(String dealPrice) 
    {
        this.dealPrice = dealPrice;
    }

    public String getDealPrice() 
    {
        return dealPrice;
    }
    public void setDealTime(Date dealTime) 
    {
        this.dealTime = dealTime;
    }

    public Date getDealTime() 
    {
        return dealTime;
    }
    public void setRushState(String rushState) 
    {
        this.rushState = rushState;
    }

    public String getRushState() 
    {
        return rushState;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("purchaseId", getPurchaseId())
            .append("userId", getUserId())
            .append("deptId", getDeptId())
            .append("purchName", getPurchName())
            .append("phoneNum", getPhoneNum())
            .append("goodsId", getGoodsId())
            .append("buyFrom", getBuyFrom())
            .append("buyPrice", getBuyPrice())
            .append("buyTime", getBuyTime())
            .append("orderNum", getOrderNum())
            .append("machineId", getMachineId())
            .append("orderState", getOrderState())
            .append("dealState", getDealState())
            .append("arrivalTime", getArrivalTime())
            .append("dealPrice", getDealPrice())
            .append("dealTime", getDealTime())
            .append("rushState", getRushState())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
