package com.ruoyi.purchase.mapper;

import java.util.List;
import com.ruoyi.purchase.domain.PurchaseRecord;

/**
 * 采购记录Mapper接口
 * 
 * @author ruoyi
 * @date 2024-02-19
 */
public interface PurchaseRecordMapper 
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
    public PurchaseRecord selectPurchaseRecordListForValid(PurchaseRecord purchaseRecord);

    /**
     * 新增采购记录
     * 
     * @param purchaseRecord 采购记录
     * @return 结果
     */
    public int insertPurchaseRecord(PurchaseRecord purchaseRecord);

    /**
     * 修改采购记录
     * 
     * @param purchaseRecord 采购记录
     * @return 结果
     */
    public int updatePurchaseRecord(PurchaseRecord purchaseRecord);

    /**
     * 删除采购记录
     * 
     * @param purchaseRecordId 采购记录主键
     * @return 结果
     */
    public int deletePurchaseRecordByPurchaseRecordId(Long purchaseRecordId);

    /**
     * 批量删除采购记录
     * 
     * @param purchaseRecordIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deletePurchaseRecordByPurchaseRecordIds(Long[] purchaseRecordIds);
}
