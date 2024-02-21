package com.ruoyi.statis.domain;

import java.math.BigDecimal;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 商品消耗统计对象 consumption_statis
 * 
 * @author 朱建伟
 * @date 2024-02-19
 */
public class ConsumptionStatis extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 记录ID */
    private Long consumptionStatisId;

    /** 商品ID */
    private Long goodsId;

    /** 商品名 */
    @Excel(name = "商品名")
    private String goodsName;

    /** 规格型号 */
    @Excel(name = "规格型号")
    private String normsModel;

    /** 消耗量 */
    @Excel(name = "消耗量")
    private BigDecimal consumption;

    /** 消耗时间长度 */
    @Excel(name = "消耗时间长度")
    private String time;

    /** 消耗率下限 */
    @Excel(name = "消耗率下限")
    private BigDecimal lowerLimit;

    /** 消耗率上限 */
    @Excel(name = "消耗率上限")
    private BigDecimal upperLimit;

    /** 预警状态 */
    @Excel(name = "预警状态")
    private String state;

    /** 异常原因 */
    @Excel(name = "异常原因")
    private String reason;

    public void setConsumptionStatisId(Long consumptionStatisId) 
    {
        this.consumptionStatisId = consumptionStatisId;
    }

    public Long getConsumptionStatisId() 
    {
        return consumptionStatisId;
    }
    public void setGoodsId(Long goodsId) 
    {
        this.goodsId = goodsId;
    }

    public Long getGoodsId() 
    {
        return goodsId;
    }
    public void setGoodsName(String goodsName) 
    {
        this.goodsName = goodsName;
    }

    public String getGoodsName() 
    {
        return goodsName;
    }
    public void setNormsModel(String normsModel) 
    {
        this.normsModel = normsModel;
    }

    public String getNormsModel() 
    {
        return normsModel;
    }
    public void setConsumption(BigDecimal consumption) 
    {
        this.consumption = consumption;
    }

    public BigDecimal getConsumption() 
    {
        return consumption;
    }
    public void setTime(String time) 
    {
        this.time = time;
    }

    public String getTime() 
    {
        return time;
    }
    public void setLowerLimit(BigDecimal lowerLimit) 
    {
        this.lowerLimit = lowerLimit;
    }

    public BigDecimal getLowerLimit() 
    {
        return lowerLimit;
    }
    public void setUpperLimit(BigDecimal upperLimit) 
    {
        this.upperLimit = upperLimit;
    }

    public BigDecimal getUpperLimit() 
    {
        return upperLimit;
    }
    public void setState(String state) 
    {
        this.state = state;
    }

    public String getState() 
    {
        return state;
    }
    public void setReason(String reason) 
    {
        this.reason = reason;
    }

    public String getReason() 
    {
        return reason;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("consumptionStatisId", getConsumptionStatisId())
            .append("goodsId", getGoodsId())
            .append("goodsName", getGoodsName())
            .append("normsModel", getNormsModel())
            .append("consumption", getConsumption())
            .append("time", getTime())
            .append("lowerLimit", getLowerLimit())
            .append("upperLimit", getUpperLimit())
            .append("state", getState())
            .append("reason", getReason())
            .toString();
    }
}
