package com.ruoyi.returnBack.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 交还记录对象 return_record
 * 
 * @author ruoyi
 * @date 2024-02-19
 */
public class ReturnRecord extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 记录ID */
    private Long id;

    /** 商品ID */
    @Excel(name = "商品ID")
    private Long goodsId;

    /** 商品名 */
    @Excel(name = "商品名")
    private String goodsName;

    /** 规格型号 */
    @Excel(name = "规格型号")
    private String normsModel;

    /** 交还数量 */
    @Excel(name = "交还数量")
    private BigDecimal quantity;

    /** 交还人 */
    @Excel(name = "交还人")
    private String returnPeople;

    /** 交还时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "交还时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date returnTime;

    /** 领用人 */
    @Excel(name = "领用人")
    private String recipient;

    /** 交接办理人 */
    @Excel(name = "交接办理人")
    private String handover;

    /** 交还原因 */
    @Excel(name = "交还原因")
    private String reason;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
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
    public void setQuantity(BigDecimal quantity) 
    {
        this.quantity = quantity;
    }

    public BigDecimal getQuantity() 
    {
        return quantity;
    }
    public void setReturnPeople(String returnPeople) 
    {
        this.returnPeople = returnPeople;
    }

    public String getReturnPeople() 
    {
        return returnPeople;
    }
    public void setReturnTime(Date returnTime) 
    {
        this.returnTime = returnTime;
    }

    public Date getReturnTime() 
    {
        return returnTime;
    }
    public void setRecipient(String recipient) 
    {
        this.recipient = recipient;
    }

    public String getRecipient() 
    {
        return recipient;
    }
    public void setHandover(String handover) 
    {
        this.handover = handover;
    }

    public String getHandover() 
    {
        return handover;
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
            .append("id", getId())
            .append("goodsId", getGoodsId())
            .append("goodsName", getGoodsName())
            .append("normsModel", getNormsModel())
            .append("quantity", getQuantity())
            .append("returnPeople", getReturnPeople())
            .append("returnTime", getReturnTime())
            .append("recipient", getRecipient())
            .append("handover", getHandover())
            .append("reason", getReason())
            .toString();
    }
}
