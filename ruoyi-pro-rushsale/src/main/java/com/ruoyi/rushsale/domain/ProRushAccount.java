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
 * @date 2023-04-03
 */
public class ProRushAccount extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long accountId;

    /** $column.columnComment */
    private Long userId;

    /** $column.columnComment */
    private Long deptId;

    /** 账户 */
    @Excel(name = "账户")
    private String accountNum;

    /** 开户行 */
    @Excel(name = "开户行")
    private String openingBank;

    /** 账户所有者 */
    @Excel(name = "账户所有者")
    private String owner;

    /** 手机号 */
    @Excel(name = "手机号")
    private String phoneNum;

    /** 身份证号 */
    @Excel(name = "身份证号")
    private String idNum;

    /** 余额 */
    @Excel(name = "余额")
    private BigDecimal remainder;

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
    public void setAccountNum(String accountNum) 
    {
        this.accountNum = accountNum;
    }

    public String getAccountNum() 
    {
        return accountNum;
    }
    public void setOpeningBank(String openingBank) 
    {
        this.openingBank = openingBank;
    }

    public String getOpeningBank() 
    {
        return openingBank;
    }
    public void setOwner(String owner) 
    {
        this.owner = owner;
    }

    public String getOwner() 
    {
        return owner;
    }
    public void setPhoneNum(String phoneNum) 
    {
        this.phoneNum = phoneNum;
    }

    public String getPhoneNum() 
    {
        return phoneNum;
    }
    public void setIdNum(String idNum) 
    {
        this.idNum = idNum;
    }

    public String getIdNum() 
    {
        return idNum;
    }
    public void setRemainder(BigDecimal remainder) 
    {
        this.remainder = remainder;
    }

    public BigDecimal getRemainder() 
    {
        return remainder;
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
            .append("accountNum", getAccountNum())
            .append("openingBank", getOpeningBank())
            .append("owner", getOwner())
            .append("phoneNum", getPhoneNum())
            .append("idNum", getIdNum())
            .append("remainder", getRemainder())
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
