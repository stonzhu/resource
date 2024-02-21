package com.ruoyi.goodsStatis.mapper;

import java.util.List;
import com.ruoyi.goodsStatis.domain.GoodsStatis;

/**
 * 商品库存动态Mapper接口
 * 
 * @author ruoyi
 * @date 2024-02-19
 */
public interface GoodsStatisMapper 
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
    public List<GoodsStatis> selectGoodsStatisListByNameModel(GoodsStatis goodsStatis);

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
     * 删除商品库存动态
     * 
     * @param goodsStatisId 商品库存动态主键
     * @return 结果
     */
    public int deleteGoodsStatisByGoodsStatisId(Long goodsStatisId);

    /**
     * 批量删除商品库存动态
     * 
     * @param goodsStatisIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteGoodsStatisByGoodsStatisIds(Long[] goodsStatisIds);
}
