package com.ruoyi.receive.service.impl;

import java.math.BigDecimal;
import java.util.List;

import com.ruoyi.goods.domain.OfficeGoods;
import com.ruoyi.goods.mapper.OfficeGoodsMapper;
import com.ruoyi.goodsStatis.domain.GoodsStatis;
import com.ruoyi.goodsStatis.mapper.GoodsStatisMapper;
import com.ruoyi.purchase.domain.PurchaseRecord;
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
    @Autowired
    private OfficeGoodsMapper officeGoodsMapper;

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
        //更新商品表，如果已存在返回id
        Long goodsId = updateOfficeGoods(receiveRecord);
        receiveRecord.setGoodsId(goodsId);
        //普通领用记录，需要更新库存
        if("2".equals(receiveRecord.getReceiveType())||receiveRecord.getReceiveType()==null){
            updateGoodsStatis(receiveRecord);
        }
        // 新增固定资产领用记录
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
        String receiveType = receiveRecord.getReceiveType();
        if(receiveType.equals("1")){
            return receiveRecordMapper.updateReceiveRecord(receiveRecord);
        }else {
            //查询修改前的领用记录
            ReceiveRecord oldReceiveRecord = receiveRecordMapper.selectReceiveRecordById(receiveRecord.getId());
            BigDecimal difference = receiveRecord.getQuantity().subtract(oldReceiveRecord.getQuantity());
            ReceiveRecord  record = new ReceiveRecord();
            record.setGoodsId(receiveRecord.getGoodsId());
            record.setQuantity(difference);
            updateGoodsStatis(record);
            return receiveRecordMapper.updateReceiveRecord(receiveRecord);
        }

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

    //修改商品库存
    public void updateGoodsStatis(ReceiveRecord receiveRecord){
        //修改商品库存记录
        GoodsStatis gs = new GoodsStatis();
        gs.setGoodsId(receiveRecord.getGoodsId());
        List<GoodsStatis> gsLIst = goodsStatisMapper.selectGoodsStatisListByNameModel(gs);
        gs = gsLIst.get(0);
        gs.setRemain(gs.getRemain().subtract(receiveRecord.getQuantity()));
        goodsStatisMapper.updateGoodsStatis(gs);
    }

    //更新商品表
    public Long updateOfficeGoods(ReceiveRecord ReceiveRecord){
        //更新商品表
        OfficeGoods goods = new OfficeGoods();
        goods.setGoodsName(ReceiveRecord.getGoodsName());
        goods.setNormsModel(ReceiveRecord.getNormsModel());
        //goods.setPrice(ReceiveRecord.getPrice());
        List<OfficeGoods> gdList = officeGoodsMapper.selectOfficeGoodsListByNameModel(goods);
        Long goodsId = 0L;
        if(gdList.size() == 0){
            goods.setClassType("固定资产");
            goods.setIsPublic("个人用");
            //goods.setUnit(ReceiveRecord.getUnit());
            //goods.setPrice(pur.getPrice());
            officeGoodsMapper.insertOfficeGoods(goods);
            goodsId = goods.getGoodsId();
        }else{
            goodsId = gdList.get(0).getGoodsId();
        }
        return goodsId;
    }
}
