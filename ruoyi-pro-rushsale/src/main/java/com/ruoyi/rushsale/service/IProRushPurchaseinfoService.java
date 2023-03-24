package com.ruoyi.rushsale.service;

import java.util.List;
import com.ruoyi.rushsale.domain.ProRushPurchaseinfo;

/**
 * 抢购人购买结算信息Service接口
 * 
 * @author ruoyi
 * @date 2023-03-24
 */
public interface IProRushPurchaseinfoService 
{
    /**
     * 查询抢购人购买结算信息
     * 
     * @param purchaseId 抢购人购买结算信息主键
     * @return 抢购人购买结算信息
     */
    public ProRushPurchaseinfo selectProRushPurchaseinfoByPurchaseId(Long purchaseId);

    /**
     * 查询抢购人购买结算信息列表
     * 
     * @param proRushPurchaseinfo 抢购人购买结算信息
     * @return 抢购人购买结算信息集合
     */
    public List<ProRushPurchaseinfo> selectProRushPurchaseinfoList(ProRushPurchaseinfo proRushPurchaseinfo);

    /**
     * 新增抢购人购买结算信息
     * 
     * @param proRushPurchaseinfo 抢购人购买结算信息
     * @return 结果
     */
    public int insertProRushPurchaseinfo(ProRushPurchaseinfo proRushPurchaseinfo);

    /**
     * 修改抢购人购买结算信息
     * 
     * @param proRushPurchaseinfo 抢购人购买结算信息
     * @return 结果
     */
    public int updateProRushPurchaseinfo(ProRushPurchaseinfo proRushPurchaseinfo);

    /**
     * 批量删除抢购人购买结算信息
     * 
     * @param purchaseIds 需要删除的抢购人购买结算信息主键集合
     * @return 结果
     */
    public int deleteProRushPurchaseinfoByPurchaseIds(Long[] purchaseIds);

    /**
     * 删除抢购人购买结算信息信息
     * 
     * @param purchaseId 抢购人购买结算信息主键
     * @return 结果
     */
    public int deleteProRushPurchaseinfoByPurchaseId(Long purchaseId);
}
