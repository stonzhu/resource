package com.ruoyi.rushsale.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.rushsale.mapper.ProRushGoodsMapper;
import com.ruoyi.rushsale.domain.ProRushGoods;
import com.ruoyi.rushsale.service.IProRushGoodsService;

/**
 * 商品信息Service业务层处理
 * 
 * @author ruoyi
 * @date 2023-03-24
 */
@Service
public class ProRushGoodsServiceImpl implements IProRushGoodsService 
{
    @Autowired
    private ProRushGoodsMapper proRushGoodsMapper;

    /**
     * 查询商品信息
     * 
     * @param goodsId 商品信息主键
     * @return 商品信息
     */
    @Override
    public ProRushGoods selectProRushGoodsByGoodsId(Long goodsId)
    {
        return proRushGoodsMapper.selectProRushGoodsByGoodsId(goodsId);
    }

    /**
     * 查询商品信息列表
     * 
     * @param proRushGoods 商品信息
     * @return 商品信息
     */
    @Override
    public List<ProRushGoods> selectProRushGoodsList(ProRushGoods proRushGoods)
    {
        return proRushGoodsMapper.selectProRushGoodsList(proRushGoods);
    }

    /**
     * 新增商品信息
     * 
     * @param proRushGoods 商品信息
     * @return 结果
     */
    @Override
    public int insertProRushGoods(ProRushGoods proRushGoods)
    {
        proRushGoods.setCreateTime(DateUtils.getNowDate());
        return proRushGoodsMapper.insertProRushGoods(proRushGoods);
    }

    /**
     * 修改商品信息
     * 
     * @param proRushGoods 商品信息
     * @return 结果
     */
    @Override
    public int updateProRushGoods(ProRushGoods proRushGoods)
    {
        proRushGoods.setUpdateTime(DateUtils.getNowDate());
        return proRushGoodsMapper.updateProRushGoods(proRushGoods);
    }

    /**
     * 批量删除商品信息
     * 
     * @param goodsIds 需要删除的商品信息主键
     * @return 结果
     */
    @Override
    public int deleteProRushGoodsByGoodsIds(Long[] goodsIds)
    {
        return proRushGoodsMapper.deleteProRushGoodsByGoodsIds(goodsIds);
    }

    /**
     * 删除商品信息信息
     * 
     * @param goodsId 商品信息主键
     * @return 结果
     */
    @Override
    public int deleteProRushGoodsByGoodsId(Long goodsId)
    {
        return proRushGoodsMapper.deleteProRushGoodsByGoodsId(goodsId);
    }
}
