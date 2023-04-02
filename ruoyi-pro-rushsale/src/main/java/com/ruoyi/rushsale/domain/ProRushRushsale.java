package com.ruoyi.rushsale.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 出售交易信息对象 pro_rush_rushsale
 * 
 * @author zhujw
 * @date 2023-03-24
 */
public class ProRushRushsale extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 收购商结算id */
    private Long rushsaleId;

    /** 用户id */
    private Long userId;

    /** 部门id */
    private Long deptId;
    private String type;//修改类型列名

    /** 抢购人购买id */
    @Excel(name = "抢购人购买id")
    private Long purchaseId;

    /** 收购商结算价 */
    @Excel(name = "收购商结算价")
    private String payPrice;

    /** 收购商结算时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "收购商结算时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date payTime;

    /** 收购商 */
    @Excel(name = "收购商")
    private String payFrom;

    /** 结算方式 */
    @Excel(name = "结算方式")
    private String payType;

    /** 中间价 */
    @Excel(name = "中间价")
    private String dealPrice;

    /** 结算状态 */
    @Excel(name = "结算状态")
    private String dealState;

    /** 转送状态 转出 退回 */
    @Excel(name = "转送状态 转出 退回")
    private String rushState;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setRushsaleId(Long rushsaleId)
    {
        this.rushsaleId = rushsaleId;
    }

    public Long getRushsaleId() 
    {
        return rushsaleId;
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
    public void setPurchaseId(Long purchaseId) 
    {
        this.purchaseId = purchaseId;
    }

    public Long getPurchaseId() 
    {
        return purchaseId;
    }
    public void setPayPrice(String payPrice) 
    {
        this.payPrice = payPrice;
    }

    public String getPayPrice() 
    {
        return payPrice;
    }
    public void setPayTime(Date payTime) 
    {
        this.payTime = payTime;
    }

    public Date getPayTime() 
    {
        return payTime;
    }
    public void setPayFrom(String payFrom) 
    {
        this.payFrom = payFrom;
    }

    public String getPayFrom() 
    {
        return payFrom;
    }
    public void setPayType(String payType) 
    {
        this.payType = payType;
    }

    public String getPayType() 
    {
        return payType;
    }
    public void setDealPrice(String dealPrice) 
    {
        this.dealPrice = dealPrice;
    }

    public String getDealPrice() 
    {
        return dealPrice;
    }
    public void setDealState(String dealState) 
    {
        this.dealState = dealState;
    }

    public String getDealState() 
    {
        return dealState;
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
            .append("rushsaleId", getRushsaleId())
            .append("userId", getUserId())
            .append("deptId", getDeptId())
            .append("purchaseId", getPurchaseId())
            .append("payPrice", getPayPrice())
            .append("payTime", getPayTime())
            .append("payFrom", getPayFrom())
            .append("payType", getPayType())
            .append("dealPrice", getDealPrice())
            .append("dealState", getDealState())
            .append("rushState", getRushState())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
