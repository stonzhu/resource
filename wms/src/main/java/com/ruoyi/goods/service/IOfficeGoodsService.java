package com.ruoyi.goods.service;

import java.util.List;
import com.ruoyi.goods.domain.OfficeGoods;

/**
 * 办公用品商品Service接口
 * 
 * @author ruoyi
 * @date 2024-02-19
 */
public interface IOfficeGoodsService 
{
    /**
     * 查询办公用品商品
     * 
     * @param goodsId 办公用品商品主键
     * @return 办公用品商品
     */
    public OfficeGoods selectOfficeGoodsByGoodsId(Long goodsId);

    /**
     * 查询办公用品商品列表
     * 
     * @param officeGoods 办公用品商品
     * @return 办公用品商品集合
     */
    public List<OfficeGoods> selectOfficeGoodsList(OfficeGoods officeGoods);

    /**
     * 新增办公用品商品
     * 
     * @param officeGoods 办公用品商品
     * @return 结果
     */
    public int insertOfficeGoods(OfficeGoods officeGoods);

    /**
     * 修改办公用品商品
     * 
     * @param officeGoods 办公用品商品
     * @return 结果
     */
    public int updateOfficeGoods(OfficeGoods officeGoods);

    /**
     * 批量删除办公用品商品
     * 
     * @param goodsIds 需要删除的办公用品商品主键集合
     * @return 结果
     */
    public int deleteOfficeGoodsByGoodsIds(Long[] goodsIds);

    /**
     * 删除办公用品商品信息
     * 
     * @param goodsId 办公用品商品主键
     * @return 结果
     */
    public int deleteOfficeGoodsByGoodsId(Long goodsId);
}
