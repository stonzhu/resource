package com.ruoyi.system.service;

import java.util.List;

import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.system.domain.ProRushSale;

/**
 * 抢购买卖Service接口
 * 
 * @author ruoyi
 * @date 2023-03-22
 */
public interface IProRushSaleService 
{
    /**
     * 查询抢购买卖
     * 
     * @param proRushSaleId 抢购买卖主键
     * @return 抢购买卖
     */
    public ProRushSale selectProRushSaleByProRushSaleId(Long proRushSaleId);

    /**
     * 查询抢购买卖列表
     * 
     * @param proRushSale 抢购买卖
     * @return 抢购买卖集合
     */
    public List<ProRushSale> selectProRushSaleList(ProRushSale proRushSale);

    /**
     * 新增抢购买卖
     * 
     * @param proRushSale 抢购买卖
     * @return 结果
     */
    public int insertProRushSale(ProRushSale proRushSale);

    /**
     * 修改抢购买卖
     * 
     * @param proRushSale 抢购买卖
     * @return 结果
     */
    public int updateProRushSale(ProRushSale proRushSale);

    /**
     * 批量删除抢购买卖
     * 
     * @param proRushSaleIds 需要删除的抢购买卖主键集合
     * @return 结果
     */
    public int deleteProRushSaleByProRushSaleIds(Long[] proRushSaleIds);

    /**
     * 删除抢购买卖信息
     * 
     * @param proRushSaleId 抢购买卖主键
     * @return 结果
     */
    public int deleteProRushSaleByProRushSaleId(Long proRushSaleId);

    /**
     * 导入抢购购买数据
     */
    public String importRush(List<ProRushSale> rushList, Boolean isUpdateSupport, String operName);
}
