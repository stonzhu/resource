package com.ruoyi.goods.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 办公用品商品对象 office_goods
 * 
 * @author ruoyi
 * @date 2024-02-19
 */
public class OfficeGoods extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 商品id */
    private Long goodsId;

    /** 商品名 */
    @Excel(name = "商品名")
    private String goodsName;

    /** 财务编码 */
    @Excel(name = "财务编码")
    private String financeCode;

    /** 规格型号 */
    @Excel(name = "规格型号")
    private String normsModel;

    /** 单位 */
    @Excel(name = "单位")
    private String unit;

    /** 单价 */
    @Excel(name = "单价")
    private String price;

    /** 类别（是否固定资产） */
    @Excel(name = "类别", readConverterExp = "是=否固定资产")
    private String classType;

    /** 公用or专用 */
    @Excel(name = "公用or专用")
    private String isPublic;

    /** 商品图片 */
    @Excel(name = "商品图片")
    private String picture;

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
    public void setFinanceCode(String financeCode) 
    {
        this.financeCode = financeCode;
    }

    public String getFinanceCode() 
    {
        return financeCode;
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
    public void setIsPublic(String isPublic) 
    {
        this.isPublic = isPublic;
    }

    public String getIsPublic() 
    {
        return isPublic;
    }
    public void setPicture(String picture) 
    {
        this.picture = picture;
    }

    public String getPicture() 
    {
        return picture;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("goodsId", getGoodsId())
            .append("goodsName", getGoodsName())
            .append("financeCode", getFinanceCode())
            .append("normsModel", getNormsModel())
            .append("unit", getUnit())
            .append("price", getPrice())
            .append("classType", getClassType())
            .append("isPublic", getIsPublic())
            .append("picture", getPicture())
            .toString();
    }
}
