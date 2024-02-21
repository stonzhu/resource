package com.ruoyi.goodsStatis.service;

import java.util.List;
import com.ruoyi.goodsStatis.domain.GoodsStatis;

/**
 * 商品库存动态Service接口
 * 
 * @author ruoyi
 * @date 2024-02-19
 */
public interface IGoodsStatisService 
{
    /**
     * 查询商品库存动态
     * 
     * @param goodsStatisId 商品库存动态主键
     * @return 商品库存动态
     */
    public GoodsStatis selectGoodsStatisByGoodsStatisId(Long goodsStatisId);

    /**
     * 查询商品库存动态列表
     * 
     * @param goodsStatis 商品库存动态
     * @return 商品库存动态集合
     */
    public List<GoodsStatis> selectGoodsStatisList(GoodsStatis goodsStatis);

    /**
     * 新增商品库存动态
     * 
     * @param goodsStatis 商品库存动态
     * @return 结果
     */
    public int insertGoodsStatis(GoodsStatis goodsStatis);

    /**
     * 修改商品库存动态
     * 
     * @param goodsStatis 商品库存动态
     * @return 结果
     */
    public int updateGoodsStatis(GoodsStatis goodsStatis);

    /**
     * 批量删除商品库存动态
     * 
     * @param goodsStatisIds 需要删除的商品库存动态主键集合
     * @return 结果
     */
    public int deleteGoodsStatisByGoodsStatisIds(Long[] goodsStatisIds);

    /**
     * 删除商品库存动态信息
     * 
     * @param goodsStatisId 商品库存动态主键
     * @return 结果
     */
    public int deleteGoodsStatisByGoodsStatisId(Long goodsStatisId);
}
