package com.ruoyi.rushsale.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.rushsale.mapper.ProRushPurchaseinfoMapper;
import com.ruoyi.rushsale.domain.ProRushPurchaseinfo;
import com.ruoyi.rushsale.service.IProRushPurchaseinfoService;

/**
 * 抢购人购买结算信息Service业务层处理
 * 
 * @author ruoyi
 * @date 2023-03-24
 */
@Service
public class ProRushPurchaseinfoServiceImpl implements IProRushPurchaseinfoService 
{
    @Autowired
    private ProRushPurchaseinfoMapper proRushPurchaseinfoMapper;

    /**
     * 查询抢购人购买结算信息
     * 
     * @param purchaseId 抢购人购买结算信息主键
     * @return 抢购人购买结算信息
     */
    @Override
    public ProRushPurchaseinfo selectProRushPurchaseinfoByPurchaseId(Long purchaseId)
    {
        return proRushPurchaseinfoMapper.selectProRushPurchaseinfoByPurchaseId(purchaseId);
    }

    /**
     * 查询抢购人购买结算信息列表
     * 
     * @param proRushPurchaseinfo 抢购人购买结算信息
     * @return 抢购人购买结算信息
     */
    @Override
    public List<ProRushPurchaseinfo> selectProRushPurchaseinfoList(ProRushPurchaseinfo proRushPurchaseinfo)
    {
        return proRushPurchaseinfoMapper.selectProRushPurchaseinfoList(proRushPurchaseinfo);
    }

    /**
     * 新增抢购人购买结算信息
     * 
     * @param proRushPurchaseinfo 抢购人购买结算信息
     * @return 结果
     */
    @Override
    public int insertProRushPurchaseinfo(ProRushPurchaseinfo proRushPurchaseinfo)
    {
        proRushPurchaseinfo.setCreateTime(DateUtils.getNowDate());
        return proRushPurchaseinfoMapper.insertProRushPurchaseinfo(proRushPurchaseinfo);
    }

    /**
     * 修改抢购人购买结算信息
     * 
     * @param proRushPurchaseinfo 抢购人购买结算信息
     * @return 结果
     */
    @Override
    public int updateProRushPurchaseinfo(ProRushPurchaseinfo proRushPurchaseinfo)
    {
        proRushPurchaseinfo.setUpdateTime(DateUtils.getNowDate());
        return proRushPurchaseinfoMapper.updateProRushPurchaseinfo(proRushPurchaseinfo);
    }

    /**
     * 批量删除抢购人购买结算信息
     * 
     * @param purchaseIds 需要删除的抢购人购买结算信息主键
     * @return 结果
     */
    @Override
    public int deleteProRushPurchaseinfoByPurchaseIds(Long[] purchaseIds)
    {
        return proRushPurchaseinfoMapper.deleteProRushPurchaseinfoByPurchaseIds(purchaseIds);
    }

    /**
     * 删除抢购人购买结算信息信息
     * 
     * @param purchaseId 抢购人购买结算信息主键
     * @return 结果
     */
    @Override
    public int deleteProRushPurchaseinfoByPurchaseId(Long purchaseId)
    {
        return proRushPurchaseinfoMapper.deleteProRushPurchaseinfoByPurchaseId(purchaseId);
    }
}
