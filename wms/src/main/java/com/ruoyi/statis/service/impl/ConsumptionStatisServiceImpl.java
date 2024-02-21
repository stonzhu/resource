package com.ruoyi.statis.service.impl;

import java.util.List;

import com.ruoyi.statis.domain.ConsumptionStatis;
import com.ruoyi.statis.mapper.ConsumptionStatisMapper;
import com.ruoyi.statis.service.IConsumptionStatisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 商品消耗统计Service业务层处理
 * 
 * @author 朱建伟
 * @date 2024-02-19
 */
@Service
public class ConsumptionStatisServiceImpl implements IConsumptionStatisService
{
    @Autowired
    private ConsumptionStatisMapper consumptionStatisMapper;

    /**
     * 查询商品消耗统计
     * 
     * @param consumptionStatisId 商品消耗统计主键
     * @return 商品消耗统计
     */
    @Override
    public ConsumptionStatis selectConsumptionStatisByConsumptionStatisId(Long consumptionStatisId)
    {
        return consumptionStatisMapper.selectConsumptionStatisByConsumptionStatisId(consumptionStatisId);
    }

    /**
     * 查询商品消耗统计列表
     * 
     * @param consumptionStatis 商品消耗统计
     * @return 商品消耗统计
     */
    @Override
    public List<ConsumptionStatis> selectConsumptionStatisList(ConsumptionStatis consumptionStatis)
    {
        return consumptionStatisMapper.selectConsumptionStatisList(consumptionStatis);
    }

    /**
     * 新增商品消耗统计
     * 
     * @param consumptionStatis 商品消耗统计
     * @return 结果
     */
    @Override
    public int insertConsumptionStatis(ConsumptionStatis consumptionStatis)
    {
        return consumptionStatisMapper.insertConsumptionStatis(consumptionStatis);
    }

    /**
     * 修改商品消耗统计
     * 
     * @param consumptionStatis 商品消耗统计
     * @return 结果
     */
    @Override
    public int updateConsumptionStatis(ConsumptionStatis consumptionStatis)
    {
        return consumptionStatisMapper.updateConsumptionStatis(consumptionStatis);
    }

    /**
     * 批量删除商品消耗统计
     * 
     * @param consumptionStatisIds 需要删除的商品消耗统计主键
     * @return 结果
     */
    @Override
    public int deleteConsumptionStatisByConsumptionStatisIds(Long[] consumptionStatisIds)
    {
        return consumptionStatisMapper.deleteConsumptionStatisByConsumptionStatisIds(consumptionStatisIds);
    }

    /**
     * 删除商品消耗统计信息
     * 
     * @param consumptionStatisId 商品消耗统计主键
     * @return 结果
     */
    @Override
    public int deleteConsumptionStatisByConsumptionStatisId(Long consumptionStatisId)
    {
        return consumptionStatisMapper.deleteConsumptionStatisByConsumptionStatisId(consumptionStatisId);
    }
}
