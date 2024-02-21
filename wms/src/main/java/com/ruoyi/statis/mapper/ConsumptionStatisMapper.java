package com.ruoyi.statis.mapper;

import java.util.List;
import com.ruoyi.statis.domain.ConsumptionStatis;

/**
 * 商品消耗统计Mapper接口
 * 
 * @author 朱建伟
 * @date 2024-02-19
 */
public interface ConsumptionStatisMapper 
{
    /**
     * 查询商品消耗统计
     * 
     * @param consumptionStatisId 商品消耗统计主键
     * @return 商品消耗统计
     */
    public ConsumptionStatis selectConsumptionStatisByConsumptionStatisId(Long consumptionStatisId);

    /**
     * 查询商品消耗统计列表
     * 
     * @param consumptionStatis 商品消耗统计
     * @return 商品消耗统计集合
     */
    public List<ConsumptionStatis> selectConsumptionStatisList(ConsumptionStatis consumptionStatis);

    /**
     * 新增商品消耗统计
     * 
     * @param consumptionStatis 商品消耗统计
     * @return 结果
     */
    public int insertConsumptionStatis(ConsumptionStatis consumptionStatis);

    /**
     * 修改商品消耗统计
     * 
     * @param consumptionStatis 商品消耗统计
     * @return 结果
     */
    public int updateConsumptionStatis(ConsumptionStatis consumptionStatis);

    /**
     * 删除商品消耗统计
     * 
     * @param consumptionStatisId 商品消耗统计主键
     * @return 结果
     */
    public int deleteConsumptionStatisByConsumptionStatisId(Long consumptionStatisId);

    /**
     * 批量删除商品消耗统计
     * 
     * @param consumptionStatisIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteConsumptionStatisByConsumptionStatisIds(Long[] consumptionStatisIds);
}
