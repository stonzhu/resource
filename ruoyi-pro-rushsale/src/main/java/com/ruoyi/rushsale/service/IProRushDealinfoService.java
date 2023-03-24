package com.ruoyi.rushsale.service;

import java.util.List;
import com.ruoyi.rushsale.domain.ProRushDealinfo;

/**
 * 抢购交易信息Service接口
 * 
 * @author ruoyi
 * @date 2023-03-24
 */
public interface IProRushDealinfoService 
{
    /**
     * 查询抢购交易信息
     * 
     * @param dealId 抢购交易信息主键
     * @return 抢购交易信息
     */
    public ProRushDealinfo selectProRushDealinfoByDealId(Long dealId);

    /**
     * 查询抢购交易信息列表
     * 
     * @param proRushDealinfo 抢购交易信息
     * @return 抢购交易信息集合
     */
    public List<ProRushDealinfo> selectProRushDealinfoList(ProRushDealinfo proRushDealinfo);

    /**
     * 新增抢购交易信息
     * 
     * @param proRushDealinfo 抢购交易信息
     * @return 结果
     */
    public int insertProRushDealinfo(ProRushDealinfo proRushDealinfo);

    /**
     * 修改抢购交易信息
     * 
     * @param proRushDealinfo 抢购交易信息
     * @return 结果
     */
    public int updateProRushDealinfo(ProRushDealinfo proRushDealinfo);

    /**
     * 批量删除抢购交易信息
     * 
     * @param dealIds 需要删除的抢购交易信息主键集合
     * @return 结果
     */
    public int deleteProRushDealinfoByDealIds(Long[] dealIds);

    /**
     * 删除抢购交易信息信息
     * 
     * @param dealId 抢购交易信息主键
     * @return 结果
     */
    public int deleteProRushDealinfoByDealId(Long dealId);
}
