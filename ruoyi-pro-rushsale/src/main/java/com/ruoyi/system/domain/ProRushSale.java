package com.ruoyi.system.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 抢购买卖对象 pro_rush_sale
 * 
 * @author ruoyi
 * @date 2023-03-22
 */
public class ProRushSale extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 抢购买卖ID */
    private Long proRushSaleId;

    /** 物品名称 */
    @Excel(name = "物品名称")
    private String goodsName;

    /** 规格 */
    @Excel(name = "规格")
    private String specs;

    /** 物品图片 */
    @Excel(name = "物品图片")
    private String picture;

    /** 颜色 */
    @Excel(name = "颜色")
    private String color;

    /** 采购价格 */
    @Excel(name = "采购价格")
    private String purchasePrice;

    /** 购入价格 */
    @Excel(name = "购入价格")
    private String inPrice;

    /** 结算价格 */
    @Excel(name = "结算价格")
    private String outPrice;

    /** 采购人 */
    @Excel(name = "采购人")
    private String purchasePeople;

    /** 购入人 */
    @Excel(name = "购入人")
    private String inPeople;

    /** 结算人 */
    @Excel(name = "结算人")
    private String outPeople;

    /** 购入结算方式 */
    @Excel(name = "购入结算方式")
    private String inSettleModel;

    /** 支出结算方式 */
    @Excel(name = "支出结算方式")
    private String outSettleModel;

    /** 采购时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "采购时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date buyTime;

    /** 转入时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "转入时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date inTime;

    /** 转出时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "转出时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date outTime;

    /** 购入结算时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "购入结算时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date outMomeyTime;

    /** 出手结算时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "出手结算时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date inMomeyTime;

    /** 购入渠道 */
    @Excel(name = "购入渠道")
    private String buyFrom;

    /** 数量 */
    @Excel(name = "数量")
    private String quantity;

    public void setProRushSaleId(Long proRushSaleId) 
    {
        this.proRushSaleId = proRushSaleId;
    }

    public Long getProRushSaleId() 
    {
        return proRushSaleId;
    }
    public void setGoodsName(String goodsName) 
    {
        this.goodsName = goodsName;
    }

    public String getGoodsName() 
    {
        return goodsName;
    }
    public void setSpecs(String specs) 
    {
        this.specs = specs;
    }

    public String getSpecs() 
    {
        return specs;
    }
    public void setPicture(String picture) 
    {
        this.picture = picture;
    }

    public String getPicture() 
    {
        return picture;
    }
    public void setColor(String color) 
    {
        this.color = color;
    }

    public String getColor() 
    {
        return color;
    }
    public void setPurchasePrice(String purchasePrice) 
    {
        this.purchasePrice = purchasePrice;
    }

    public String getPurchasePrice() 
    {
        return purchasePrice;
    }
    public void setInPrice(String inPrice) 
    {
        this.inPrice = inPrice;
    }

    public String getInPrice() 
    {
        return inPrice;
    }
    public void setOutPrice(String outPrice) 
    {
        this.outPrice = outPrice;
    }

    public String getOutPrice() 
    {
        return outPrice;
    }
    public void setPurchasePeople(String purchasePeople) 
    {
        this.purchasePeople = purchasePeople;
    }

    public String getPurchasePeople() 
    {
        return purchasePeople;
    }
    public void setInPeople(String inPeople) 
    {
        this.inPeople = inPeople;
    }

    public String getInPeople() 
    {
        return inPeople;
    }
    public void setOutPeople(String outPeople) 
    {
        this.outPeople = outPeople;
    }

    public String getOutPeople() 
    {
        return outPeople;
    }
    public void setInSettleModel(String inSettleModel) 
    {
        this.inSettleModel = inSettleModel;
    }

    public String getInSettleModel() 
    {
        return inSettleModel;
    }
    public void setOutSettleModel(String outSettleModel) 
    {
        this.outSettleModel = outSettleModel;
    }

    public String getOutSettleModel() 
    {
        return outSettleModel;
    }
    public void setBuyTime(Date buyTime) 
    {
        this.buyTime = buyTime;
    }

    public Date getBuyTime() 
    {
        return buyTime;
    }
    public void setInTime(Date inTime) 
    {
        this.inTime = inTime;
    }

    public Date getInTime() 
    {
        return inTime;
    }
    public void setOutTime(Date outTime) 
    {
        this.outTime = outTime;
    }

    public Date getOutTime() 
    {
        return outTime;
    }
    public void setOutMomeyTime(Date outMomeyTime) 
    {
        this.outMomeyTime = outMomeyTime;
    }

    public Date getOutMomeyTime() 
    {
        return outMomeyTime;
    }
    public void setInMomeyTime(Date inMomeyTime) 
    {
        this.inMomeyTime = inMomeyTime;
    }

    public Date getInMomeyTime() 
    {
        return inMomeyTime;
    }
    public void setBuyFrom(String buyFrom) 
    {
        this.buyFrom = buyFrom;
    }

    public String getBuyFrom() 
    {
        return buyFrom;
    }
    public void setQuantity(String quantity) 
    {
        this.quantity = quantity;
    }

    public String getQuantity() 
    {
        return quantity;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("proRushSaleId", getProRushSaleId())
            .append("goodsName", getGoodsName())
            .append("specs", getSpecs())
            .append("picture", getPicture())
            .append("color", getColor())
            .append("purchasePrice", getPurchasePrice())
            .append("inPrice", getInPrice())
            .append("outPrice", getOutPrice())
            .append("purchasePeople", getPurchasePeople())
            .append("inPeople", getInPeople())
            .append("outPeople", getOutPeople())
            .append("inSettleModel", getInSettleModel())
            .append("outSettleModel", getOutSettleModel())
            .append("buyTime", getBuyTime())
            .append("inTime", getInTime())
            .append("outTime", getOutTime())
            .append("outMomeyTime", getOutMomeyTime())
            .append("inMomeyTime", getInMomeyTime())
            .append("buyFrom", getBuyFrom())
            .append("quantity", getQuantity())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
