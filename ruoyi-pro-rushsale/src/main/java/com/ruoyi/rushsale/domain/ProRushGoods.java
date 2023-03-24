package com.ruoyi.rushsale.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 商品信息对象 pro_rush_goods
 * 
 * @author ruoyi
 * @date 2023-03-24
 */
public class ProRushGoods extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 商品id */
    private Long goodsId;

    /** 用户id */
    private Long userId;

    /** 部门id */
    private Long deptId;

    /** 商品名称 */
    @Excel(name = "商品名称")
    private String name;

    /** 品牌 */
    @Excel(name = "品牌")
    private String brand;

    /** 规格型号 */
    @Excel(name = "规格型号")
    private String model;

    /** 颜色 */
    @Excel(name = "颜色")
    private String color;

    /** 尺寸 */
    @Excel(name = "尺寸")
    private String size;

    public void setGoodsId(Long goodsId) 
    {
        this.goodsId = goodsId;
    }

    public Long getGoodsId() 
    {
        return goodsId;
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
    public void setName(String name) 
    {
        this.name = name;
    }

    public String getName() 
    {
        return name;
    }
    public void setBrand(String brand) 
    {
        this.brand = brand;
    }

    public String getBrand() 
    {
        return brand;
    }
    public void setModel(String model) 
    {
        this.model = model;
    }

    public String getModel() 
    {
        return model;
    }
    public void setColor(String color) 
    {
        this.color = color;
    }

    public String getColor() 
    {
        return color;
    }
    public void setSize(String size) 
    {
        this.size = size;
    }

    public String getSize() 
    {
        return size;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("goodsId", getGoodsId())
            .append("userId", getUserId())
            .append("deptId", getDeptId())
            .append("name", getName())
            .append("brand", getBrand())
            .append("model", getModel())
            .append("color", getColor())
            .append("size", getSize())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
