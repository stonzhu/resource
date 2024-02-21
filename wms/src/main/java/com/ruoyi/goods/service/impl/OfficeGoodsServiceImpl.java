package com.ruoyi.goods.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.goods.mapper.OfficeGoodsMapper;
import com.ruoyi.goods.domain.OfficeGoods;
import com.ruoyi.goods.service.IOfficeGoodsService;

/**
 * 办公用品商品Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-02-19
 */
@Service
public class OfficeGoodsServiceImpl implements IOfficeGoodsService 
{
    @Autowired
    private OfficeGoodsMapper officeGoodsMapper;

    /**
     * 查询办公用品商品
     * 
     * @param goodsId 办公用品商品主键
     * @return 办公用品商品
     */
    @Override
    public OfficeGoods selectOfficeGoodsByGoodsId(Long goodsId)
    {
        return officeGoodsMapper.selectOfficeGoodsByGoodsId(goodsId);
    }

    /**
     * 查询办公用品商品列表
     * 
     * @param officeGoods 办公用品商品
     * @return 办公用品商品
     */
    @Override
    public List<OfficeGoods> selectOfficeGoodsList(OfficeGoods officeGoods)
    {
        return officeGoodsMapper.selectOfficeGoodsList(officeGoods);
    }

    /**
     * 新增办公用品商品
     * 
     * @param officeGoods 办公用品商品
     * @return 结果
     */
    @Override
    public int insertOfficeGoods(OfficeGoods officeGoods)
    {
        return officeGoodsMapper.insertOfficeGoods(officeGoods);
    }

    /**
     * 修改办公用品商品
     * 
     * @param officeGoods 办公用品商品
     * @return 结果
     */
    @Override
    public int updateOfficeGoods(OfficeGoods officeGoods)
    {
        return officeGoodsMapper.updateOfficeGoods(officeGoods);
    }

    /**
     * 批量删除办公用品商品
     * 
     * @param goodsIds 需要删除的办公用品商品主键
     * @return 结果
     */
    @Override
    public int deleteOfficeGoodsByGoodsIds(Long[] goodsIds)
    {
        return officeGoodsMapper.deleteOfficeGoodsByGoodsIds(goodsIds);
    }

    /**
     * 删除办公用品商品信息
     * 
     * @param goodsId 办公用品商品主键
     * @return 结果
     */
    @Override
    public int deleteOfficeGoodsByGoodsId(Long goodsId)
    {
        return officeGoodsMapper.deleteOfficeGoodsByGoodsId(goodsId);
    }
}
