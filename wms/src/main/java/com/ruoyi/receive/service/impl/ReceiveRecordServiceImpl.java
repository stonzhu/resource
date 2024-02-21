package com.ruoyi.receive.service.impl;

import java.math.BigDecimal;
import java.util.List;

import com.ruoyi.goodsStatis.domain.GoodsStatis;
import com.ruoyi.goodsStatis.mapper.GoodsStatisMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.receive.mapper.ReceiveRecordMapper;
import com.ruoyi.receive.domain.ReceiveRecord;
import com.ruoyi.receive.service.IReceiveRecordService;

/**
 * 领用记录Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-02-19
 */
@Service
public class ReceiveRecordServiceImpl implements IReceiveRecordService 
{
    @Autowired
    private ReceiveRecordMapper receiveRecordMapper;
    @Autowired
    private GoodsStatisMapper goodsStatisMapper;

    /**
     * 查询领用记录
     * 
     * @param id 领用记录主键
     * @return 领用记录
     */
    @Override
    public ReceiveRecord selectReceiveRecordById(Long id)
    {
        return receiveRecordMapper.selectReceiveRecordById(id);
    }

    /**
     * 查询领用记录列表
     * 
     * @param receiveRecord 领用记录
     * @return 领用记录
     */
    @Override
    public List<ReceiveRecord> selectReceiveRecordList(ReceiveRecord receiveRecord)
    {
        return receiveRecordMapper.selectReceiveRecordList(receiveRecord);
    }

    /**
     * 新增领用记录
     * 
     * @param receiveRecord 领用记录
     * @return 结果
     */
    @Override
    public int insertReceiveRecord(ReceiveRecord receiveRecord)
    {
        //修改商品库存记录
        GoodsStatis gs = new GoodsStatis();
        gs.setGoodsId(receiveRecord.getGoodsId());
        List<GoodsStatis> gsLIst = goodsStatisMapper.selectGoodsStatisListByNameModel(gs);
        gs = gsLIst.get(0);
        gs.setRemain(gs.getRemain().subtract(receiveRecord.getQuantity()));
        goodsStatisMapper.updateGoodsStatis(gs);
        return receiveRecordMapper.insertReceiveRecord(receiveRecord);
    }

    /**
     * 修改领用记录
     * 
     * @param receiveRecord 领用记录
     * @return 结果
     */
    @Override
    public int updateReceiveRecord(ReceiveRecord receiveRecord)
    {
        return receiveRecordMapper.updateReceiveRecord(receiveRecord);
    }

    /**
     * 批量删除领用记录
     * 
     * @param ids 需要删除的领用记录主键
     * @return 结果
     */
    @Override
    public int deleteReceiveRecordByIds(Long[] ids)
    {
        return receiveRecordMapper.deleteReceiveRecordByIds(ids);
    }

    /**
     * 删除领用记录信息
     * 
     * @param id 领用记录主键
     * @return 结果
     */
    @Override
    public int deleteReceiveRecordById(Long id)
    {
        return receiveRecordMapper.deleteReceiveRecordById(id);
    }
}
