package com.ruoyi.rushsale.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.rushsale.mapper.ProRushDealinfoMapper;
import com.ruoyi.rushsale.domain.ProRushDealinfo;
import com.ruoyi.rushsale.service.IProRushDealinfoService;

/**
 * 抢购交易信息Service业务层处理
 * 
 * @author ruoyi
 * @date 2023-03-24
 */
@Service
public class ProRushDealinfoServiceImpl implements IProRushDealinfoService 
{
    @Autowired
    private ProRushDealinfoMapper proRushDealinfoMapper;

    /**
     * 查询抢购交易信息
     * 
     * @param dealId 抢购交易信息主键
     * @return 抢购交易信息
     */
    @Override
    public ProRushDealinfo selectProRushDealinfoByDealId(Long dealId)
    {
        return proRushDealinfoMapper.selectProRushDealinfoByDealId(dealId);
    }

    /**
     * 查询抢购交易信息列表
     * 
     * @param proRushDealinfo 抢购交易信息
     * @return 抢购交易信息
     */
    @Override
    public List<ProRushDealinfo> selectProRushDealinfoList(ProRushDealinfo proRushDealinfo)
    {
        return proRushDealinfoMapper.selectProRushDealinfoList(proRushDealinfo);
    }

    /**
     * 新增抢购交易信息
     * 
     * @param proRushDealinfo 抢购交易信息
     * @return 结果
     */
    @Override
    public int insertProRushDealinfo(ProRushDealinfo proRushDealinfo)
    {
        //创建一个主要记录
        proRushDealinfo.setCreateTime(DateUtils.getNowDate());
        //保存到数据库中
        return proRushDealinfoMapper.insertProRushDealinfo(proRushDealinfo);
    }

    /**
     * 修改抢购交易信息
     * @param proRushDealinfo 抢购交易信息
     * @return 结果
     */
    @Override
    public int updateProRushDealinfo(ProRushDealinfo proRushDealinfo)
    {
        proRushDealinfo.setUpdateTime(DateUtils.getNowDate());
        return proRushDealinfoMapper.updateProRushDealinfo(proRushDealinfo);
    }
    @Override
    public int updateProRushDealinfoByCondition(ProRushDealinfo proRushDealinfo)
    {
        proRushDealinfo.setUpdateTime(DateUtils.getNowDate());
        return proRushDealinfoMapper.updateProRushDealinfoByCondition(proRushDealinfo);
    }
    /**
     * 批量删除抢购交易信息
     * 
     * @param dealIds 需要删除的抢购交易信息主键
     * @return 结果
     */
    @Override
    public int deleteProRushDealinfoByDealIds(Long[] dealIds)
    {
        return proRushDealinfoMapper.deleteProRushDealinfoByDealIds(dealIds);
    }

    /**
     * 删除抢购交易信息信息
     * 
     * @param dealId 抢购交易信息主键
     * @return 结果
     */
    @Override
    public int deleteProRushDealinfoByDealId(Long dealId)
    {
        return proRushDealinfoMapper.deleteProRushDealinfoByDealId(dealId);
    }
}
