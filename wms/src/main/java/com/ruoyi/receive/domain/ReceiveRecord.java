package com.ruoyi.receive.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 领用记录对象 receive_record
 * 
 * @author ruoyi
 * @date 2024-02-19
 */
public class ReceiveRecord extends BaseEntity
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

    /** 领取数量 */
    @Excel(name = "领取数量")
    private BigDecimal quantity;

    /** 领用人 */
    @Excel(name = "领用人")
    private String recipient;

    /** 部门 */
    @Excel(name = "部门")
    private String department;

    /** 领用时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "领用时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date receiveTime;

    /** 用途 */
    @Excel(name = "用途")
    private String useTo;

    /** 审批人 */
    @Excel(name = "审批人")
    private String approver;

    /** 交接办理人 */
    @Excel(name = "交接办理人")
    private String handover;
    /** 备注 */
    @Excel(name = "备注")
    private String remark;
    private String receiveType;


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
    public void setRecipient(String recipient) 
    {
        this.recipient = recipient;
    }

    public String getRecipient() 
    {
        return recipient;
    }
    public void setDepartment(String department) 
    {
        this.department = department;
    }

    public String getDepartment() 
    {
        return department;
    }
    public void setReceiveTime(Date receiveTime) 
    {
        this.receiveTime = receiveTime;
    }

    public Date getReceiveTime() 
    {
        return receiveTime;
    }
    public void setUseTo(String useTo) 
    {
        this.useTo = useTo;
    }

    public String getUseTo() 
    {
        return useTo;
    }
    public void setApprover(String approver) 
    {
        this.approver = approver;
    }

    public String getApprover() 
    {
        return approver;
    }
    public void setHandover(String handover) 
    {
        this.handover = handover;
    }

    public String getHandover() 
    {
        return handover;
    }

    public String getReceiveType() {
        return receiveType;
    }

    public void setReceiveType(String receiveType) {
        this.receiveType = receiveType;
    }

    @Override
    public String getRemark() {
        return remark;
    }

    @Override
    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("goodsId", getGoodsId())
            .append("goodsName", getGoodsName())
            .append("normsModel", getNormsModel())
            .append("quantity", getQuantity())
            .append("recipient", getRecipient())
            .append("department", getDepartment())
            .append("receiveTime", getReceiveTime())
            .append("useTo", getUseTo())
            .append("approver", getApprover())
            .append("handover", getHandover())
            .toString();
    }
}
