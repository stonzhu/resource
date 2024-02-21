package com.ruoyi.purchase.service;

import java.util.List;

import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.purchase.domain.PurchaseRecord;

/**
 * 采购记录Service接口
 * 
 * @author ruoyi
 * @date 2024-02-19
 */
public interface IPurchaseRecordService 
{
    /**
     * 查询采购记录
     * 
     * @param purchaseRecordId 采购记录主键
     * @return 采购记录
     */
    public PurchaseRecord selectPurchaseRecordByPurchaseRecordId(Long purchaseRecordId);

    /**
     * 查询采购记录列表
     * 
     * @param purchaseRecord 采购记录
     * @return 采购记录集合
     */
    public List<PurchaseRecord> selectPurchaseRecordList(PurchaseRecord purchaseRecord);

    /**
     * 新增采购记录
     * 
     * @param purchaseRecord 采购记录
     * @return 结果
     */
    public String insertPurchaseRecord(PurchaseRecord purchaseRecord);

    /**
     * 修改采购记录
     * 
     * @param purchaseRecord 采购记录
     * @return 结果
     */
    public int updatePurchaseRecord(PurchaseRecord purchaseRecord);

    /**
     * 批量删除采购记录
     * 
     * @param purchaseRecordIds 需要删除的采购记录主键集合
     * @return 结果
     */
    public int deletePurchaseRecordByPurchaseRecordIds(Long[] purchaseRecordIds);

    /**
     * 删除采购记录信息
     * 
     * @param purchaseRecordId 采购记录主键
     * @return 结果
     */
    public int deletePurchaseRecordByPurchaseRecordId(Long purchaseRecordId);

    public String importPurRecords(List<PurchaseRecord> purList, Boolean isUpdateSupport);
}
