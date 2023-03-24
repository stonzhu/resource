package com.ruoyi.rushsale.mapper;

import java.util.List;
import com.ruoyi.rushsale.domain.ProRushGoods;

/**
 * 商品信息Mapper接口
 * 
 * @author ruoyi
 * @date 2023-03-24
 */
public interface ProRushGoodsMapper 
{
    /**
     * 查询商品信息
     * 
     * @param goodsId 商品信息主键
     * @return 商品信息
     */
    public ProRushGoods selectProRushGoodsByGoodsId(Long goodsId);

    /**
     * 查询商品信息列表
     * 
     * @param proRushGoods 商品信息
     * @return 商品信息集合
     */
    public List<ProRushGoods> selectProRushGoodsList(ProRushGoods proRushGoods);

    /**
     * 新增商品信息
     * 
     * @param proRushGoods 商品信息
     * @return 结果
     */
    public int insertProRushGoods(ProRushGoods proRushGoods);

    /**
     * 修改商品信息
     * 
     * @param proRushGoods 商品信息
     * @return 结果
     */
    public int updateProRushGoods(ProRushGoods proRushGoods);

    /**
     * 删除商品信息
     * 
     * @param goodsId 商品信息主键
     * @return 结果
     */
    public int deleteProRushGoodsByGoodsId(Long goodsId);

    /**
     * 批量删除商品信息
     * 
     * @param goodsIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteProRushGoodsByGoodsIds(Long[] goodsIds);
}
