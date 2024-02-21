package com.ruoyi.goodsStatis.domain;

import java.math.BigDecimal;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 商品库存动态对象 goods_statis
 * 
 * @author ruoyi
 * @date 2024-02-19
 */
public class GoodsStatis extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 记录ID */
    private Long goodsStatisId;

    /** 商品ID */
    private Long goodsId;

    /** 商品名 */
    @Excel(name = "商品名")
    private String goodsName;

    /** 规格型号 */
    @Excel(name = "规格型号")
    private String normsModel;

    /** 总数量 */
    @Excel(name = "总数量")
    private BigDecimal total;

    /** 剩余数量 */
    @Excel(name = "剩余数量")
    private BigDecimal remain;

    public void setGoodsStatisId(Long goodsStatisId) 
    {
        this.goodsStatisId = goodsStatisId;
    }

    public Long getGoodsStatisId() 
    {
        return goodsStatisId;
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
    public void setTotal(BigDecimal total) 
    {
        this.total = total;
    }

    public BigDecimal getTotal() 
    {
        return total;
    }
    public void setRemain(BigDecimal remain) 
    {
        this.remain = remain;
    }

    public BigDecimal getRemain() 
    {
        return remain;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("goodsStatisId", getGoodsStatisId())
            .append("goodsId", getGoodsId())
            .append("goodsName", getGoodsName())
            .append("normsModel", getNormsModel())
            .append("total", getTotal())
            .append("remain", getRemain())
            .toString();
    }
}
