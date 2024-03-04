package com.ruoyi.purchase.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

import java.util.Date;

/**
 * 采购记录对象 purchase_record
 * 
 * @author ruoyi
 * @date 2024-02-19
 */
public class PurchaseRecord extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 记录ID */
    private Long purchaseRecordId;

    /** 商品ID */
    private Long goodsId;

    /** 商品名 */
    @Excel(name = "商品名")
    private String goodsName;

    /** 规格型号 */
    @Excel(name = "规格型号")
    private String normsModel;

    /** 单位 */
    @Excel(name = "单位")
    private String unit;

    /** 数量 */
    @Excel(name = "数量")
    private String quantity;

    /** 散装数量 */
    @Excel(name = "散装数量")
    private String xishu;

    /** 单价 */
    @Excel(name = "单价")
    private String price;

    /** 类别 */
    @Excel(name = "类别")
    private String classType;

    /** 采购日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "采购日期", width = 30, dateFormat = "yyyy-MM-dd")
    private String inDate;

    /** 供货方 */
    @Excel(name = "供货方")
    private String supplier;

    /** 采购人 */
    @Excel(name = "采购人")
    private String purchaser;

    /** 用途（一次性消耗或日常消耗） */
    @Excel(name = "用途", readConverterExp = "一=次性消耗或日常消耗")
    private String useTo;

    /** 有无发票 */
    @Excel(name = "有无发票")
    private String hasFapiao;

    /** 相应发票 */
    @Excel(name = "相应发票")
    private String fapiao;

    /** 商品图片 */
    private String picture;

    public void setPurchaseRecordId(Long purchaseRecordId) 
    {
        this.purchaseRecordId = purchaseRecordId;
    }

    public Long getPurchaseRecordId() 
    {
        return purchaseRecordId;
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
    public void setUnit(String unit) 
    {
        this.unit = unit;
    }

    public String getUnit() 
    {
        return unit;
    }
    public void setQuantity(String quantity) 
    {
        this.quantity = quantity;
    }

    public String getQuantity() 
    {
        return quantity;
    }
    public void setPrice(String price) 
    {
        this.price = price;
    }

    public String getPrice() 
    {
        return price;
    }
    public void setClassType(String classType) 
    {
        this.classType = classType;
    }

    public String getClassType() 
    {
        return classType;
    }
    public void setSupplier(String supplier) 
    {
        this.supplier = supplier;
    }

    public String getSupplier() 
    {
        return supplier;
    }
    public void setPurchaser(String purchaser) 
    {
        this.purchaser = purchaser;
    }

    public String getPurchaser() 
    {
        return purchaser;
    }
    public void setUseTo(String useTo) 
    {
        this.useTo = useTo;
    }

    public String getUseTo() 
    {
        return useTo;
    }
    public void setHasFapiao(String hasFapiao) 
    {
        this.hasFapiao = hasFapiao;
    }

    public String getHasFapiao() 
    {
        return hasFapiao;
    }
    public void setFapiao(String fapiao) 
    {
        this.fapiao = fapiao;
    }

    public String getFapiao() 
    {
        return fapiao;
    }
    public void setPicture(String picture) 
    {
        this.picture = picture;
    }

    public String getPicture() 
    {
        return picture;
    }


    public String getInDate() {
        return inDate;
    }

    public void setInDate(String inDate) {
        this.inDate = inDate;
    }

    public String getXishu() {
        return xishu;
    }

    public void setXishu(String xishu) {
        this.xishu = xishu;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("purchaseRecordId", getPurchaseRecordId())
            .append("goodsId", getGoodsId())
            .append("goodsName", getGoodsName())
            .append("normsModel", getNormsModel())
            .append("unit", getUnit())
            .append("quantity", getQuantity())
            .append("price", getPrice())
            .append("classType", getClassType())
            .append("supplier", getSupplier())
            .append("purchaser", getPurchaser())
            .append("useTo", getUseTo())
            .append("hasFapiao", getHasFapiao())
            .append("fapiao", getFapiao())
            .append("picture", getPicture())
            .toString();
    }
}
