package com.ruoyi.rushsale.domain;

import java.math.BigDecimal;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 抢购资金账户对象 pro_rush_account
 * 
 * @author ruoyi
 * @date 2023-03-24
 */
public class ProRushAccount extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long accountId;

    /** 用户id */
    private Long userId;

    /** 部门id */
    private Long deptId;

    /** 本金 */
    @Excel(name = "本金")
    private Long capital;

    /** 收入 */
    @Excel(name = "收入")
    private BigDecimal income;

    /** 利润 */
    @Excel(name = "利润")
    private BigDecimal profit;

    /** 净利润 */
    @Excel(name = "净利润")
    private BigDecimal netProfit;

    public void setAccountId(Long accountId) 
    {
        this.accountId = accountId;
    }

    public Long getAccountId() 
    {
        return accountId;
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
    public void setCapital(Long capital) 
    {
        this.capital = capital;
    }

    public Long getCapital() 
    {
        return capital;
    }
    public void setIncome(BigDecimal income) 
    {
        this.income = income;
    }

    public BigDecimal getIncome() 
    {
        return income;
    }
    public void setProfit(BigDecimal profit) 
    {
        this.profit = profit;
    }

    public BigDecimal getProfit() 
    {
        return profit;
    }
    public void setNetProfit(BigDecimal netProfit) 
    {
        this.netProfit = netProfit;
    }

    public BigDecimal getNetProfit() 
    {
        return netProfit;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("accountId", getAccountId())
            .append("userId", getUserId())
            .append("deptId", getDeptId())
            .append("capital", getCapital())
            .append("income", getIncome())
            .append("profit", getProfit())
            .append("netProfit", getNetProfit())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
