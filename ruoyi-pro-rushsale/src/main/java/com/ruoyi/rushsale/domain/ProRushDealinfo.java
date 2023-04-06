package com.ruoyi.rushsale.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 抢购交易信息对象 pro_rush_dealinfo
 * 
 * @author ruoyi
 * @date 2023-03-24
 */
public class ProRushDealinfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 交易id */
    private Long dealId;

    /** 用户id */
    private Long userId;

    /** 部门id */
    private Long deptId;

    /** 交易商品 */

    private Long goodsId;
    @Excel(name = "交易商品")
    private String goodsName;

    /** 卖方 */
    @Excel(name = "卖方")
    private String dealFrom;

    /** 买方 */
    @Excel(name = "买方")
    private String dealTo;

    /** 交易金额 */

    @Excel(name = "交易金额")
    private String dealNum;
    @Excel(name = "交易账户")
    private String accountNum;

    /** 交易类型-buy：我为买方 sale：我为卖方 */
    @Excel(name = "交易类型-buy：我为买方 sale：我为卖方")
    private String dealType;

    /** 交易来源 */
    @Excel(name = "交易来源")
    private Long handleOrderId;
    /** 交易状态 */
    @Excel(name = "交易状态")
    private String dealStatus;


    /** 交易时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "交易时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date dealTime;
    private String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getDealStatus() {
        return dealStatus;
    }

    public void setDealStatus(String dealStatus) {
        this.dealStatus = dealStatus;
    }

    public void setDealId(Long dealId)
    {
        this.dealId = dealId;
    }

    public Long getDealId() 
    {
        return dealId;
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
    public void setGoodsId(Long goodsId) 
    {
        this.goodsId = goodsId;
    }

    public Long getGoodsId() 
    {
        return goodsId;
    }
    public void setDealFrom(String dealFrom) 
    {
        this.dealFrom = dealFrom;
    }

    public String getDealFrom() 
    {
        return dealFrom;
    }
    public void setDealTo(String dealTo) 
    {
        this.dealTo = dealTo;
    }

    public String getDealTo() 
    {
        return dealTo;
    }
    public void setDealNum(String dealNum) 
    {
        this.dealNum = dealNum;
    }

    public String getDealNum() 
    {
        return dealNum;
    }

    public String getAccountNum() {
        return accountNum;
    }

    public void setAccountNum(String accountNum) {
        this.accountNum = accountNum;
    }

    public void setDealType(String dealType)
    {
        this.dealType = dealType;
    }

    public String getDealType() 
    {
        return dealType;
    }
    public void setHandleOrderId(Long handleOrderId) 
    {
        this.handleOrderId = handleOrderId;
    }

    public Long getHandleOrderId() 
    {
        return handleOrderId;
    }
    public void setDealTime(Date dealTime) 
    {
        this.dealTime = dealTime;
    }

    public Date getDealTime() 
    {
        return dealTime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("dealId", getDealId())
            .append("userId", getUserId())
            .append("deptId", getDeptId())
            .append("goodsId", getGoodsId())
            .append("dealFrom", getDealFrom())
            .append("dealTo", getDealTo())
            .append("dealNum", getDealNum())
            .append("dealType", getDealType())
            .append("handleOrderId", getHandleOrderId())
            .append("dealTime", getDealTime())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
