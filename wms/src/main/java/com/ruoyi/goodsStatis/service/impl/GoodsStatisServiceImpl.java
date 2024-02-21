package com.ruoyi.goodsStatis.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.goodsStatis.mapper.GoodsStatisMapper;
import com.ruoyi.goodsStatis.domain.GoodsStatis;
import com.ruoyi.goodsStatis.service.IGoodsStatisService;

/**
 * 商品库存动态Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-02-19
 */
@Service
public class GoodsStatisServiceImpl implements IGoodsStatisService 
{
    @Autowired
    private GoodsStatisMapper goodsStatisMapper;

    /**
     * 查询商品库存动态
     * 
     * @param goodsStatisId 商品库存动态主键
     * @return 商品库存动态
     */
    @Override
    public GoodsStatis selectGoodsStatisByGoodsStatisId(Long goodsStatisId)
    {
        return goodsStatisMapper.selectGoodsStatisByGoodsStatisId(goodsStatisId);
    }

    /**
     * 查询商品库存动态列表
     * 
     * @param goodsStatis 商品库存动态
     * @return 商品库存动态
     */
    @Override
    public List<GoodsStatis> selectGoodsStatisList(GoodsStatis goodsStatis)
    {
        return goodsStatisMapper.selectGoodsStatisList(goodsStatis);
    }

    /**
     * 新增商品库存动态
     * 
     * @param goodsStatis 商品库存动态
     * @return 结果
     */
    @Override
    public int insertGoodsStatis(GoodsStatis goodsStatis)
    {
        return goodsStatisMapper.insertGoodsStatis(goodsStatis);
    }

    /**
     * 修改商品库存动态
     * 
     * @param goodsStatis 商品库存动态
     * @return 结果
     */
    @Override
    public int updateGoodsStatis(GoodsStatis goodsStatis)
    {
        return goodsStatisMapper.updateGoodsStatis(goodsStatis);
    }

    /**
     * 批量删除商品库存动态
     * 
     * @param goodsStatisIds 需要删除的商品库存动态主键
     * @return 结果
     */
    @Override
    public int deleteGoodsStatisByGoodsStatisIds(Long[] goodsStatisIds)
    {
        return goodsStatisMapper.deleteGoodsStatisByGoodsStatisIds(goodsStatisIds);
    }

    /**
     * 删除商品库存动态信息
     * 
     * @param goodsStatisId 商品库存动态主键
     * @return 结果
     */
    @Override
    public int deleteGoodsStatisByGoodsStatisId(Long goodsStatisId)
    {
        return goodsStatisMapper.deleteGoodsStatisByGoodsStatisId(goodsStatisId);
    }
}
