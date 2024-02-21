package com.ruoyi.purchase.service.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.bean.BeanValidators;
import com.ruoyi.goods.domain.OfficeGoods;
import com.ruoyi.goods.mapper.OfficeGoodsMapper;
import com.ruoyi.goodsStatis.domain.GoodsStatis;
import com.ruoyi.goodsStatis.mapper.GoodsStatisMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.purchase.mapper.PurchaseRecordMapper;
import com.ruoyi.purchase.domain.PurchaseRecord;
import com.ruoyi.purchase.service.IPurchaseRecordService;

/**
 * 采购记录Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-02-19
 */
@Service
public class PurchaseRecordServiceImpl implements IPurchaseRecordService 
{
    @Autowired
    private PurchaseRecordMapper purchaseRecordMapper;

    @Autowired
    private OfficeGoodsMapper officeGoodsMapper;
    @Autowired
    private GoodsStatisMapper goodsStatisMapper;


    /**
     * 查询采购记录
     * 
     * @param purchaseRecordId 采购记录主键
     * @return 采购记录
     */
    @Override
    public PurchaseRecord selectPurchaseRecordByPurchaseRecordId(Long purchaseRecordId)
    {
        return purchaseRecordMapper.selectPurchaseRecordByPurchaseRecordId(purchaseRecordId);
    }

    /**
     * 查询采购记录列表
     * 
     * @param purchaseRecord 采购记录
     * @return 采购记录
     */
    @Override
    public List<PurchaseRecord> selectPurchaseRecordList(PurchaseRecord purchaseRecord)
    {
        return purchaseRecordMapper.selectPurchaseRecordList(purchaseRecord);
    }

    /**
     * 新增采购记录
     * 
     * @param purchaseRecord 采购记录
     * @return 结果
     */
    @Override
    public String insertPurchaseRecord(PurchaseRecord purchaseRecord)
    {
        StringBuilder successMsg = new StringBuilder();
        StringBuilder failureMsg = new StringBuilder();
        try
        {
            //更新商品表，如果已存在返回id
            Long goodsId = updateOfficeGoods(purchaseRecord);
            // 新增采购记录
            purchaseRecord.setGoodsId(goodsId);
            purchaseRecordMapper.insertPurchaseRecord(purchaseRecord);
            //更新商品库存统计表
            updateGoodsStatis(purchaseRecord,goodsId);
            successMsg.append("<br/>采购记录新增成功");
        }catch (Exception e){
            failureMsg.append("<br/>采购记录新增失败：")
                    .append(e.getMessage());
        }

        return successMsg.toString();
    }

    /**
     * 修改采购记录
     * 
     * @param purchaseRecord 采购记录
     * @return 结果
     */
    @Override
    public int updatePurchaseRecord(PurchaseRecord purchaseRecord)
    {
        return purchaseRecordMapper.updatePurchaseRecord(purchaseRecord);
    }

    /**
     * 批量删除采购记录
     * 
     * @param purchaseRecordIds 需要删除的采购记录主键
     * @return 结果
     */
    @Override
    public int deletePurchaseRecordByPurchaseRecordIds(Long[] purchaseRecordIds)
    {
        return purchaseRecordMapper.deletePurchaseRecordByPurchaseRecordIds(purchaseRecordIds);
    }

    /**
     * 删除采购记录信息
     * 
     * @param purchaseRecordId 采购记录主键
     * @return 结果
     */
    @Override
    public int deletePurchaseRecordByPurchaseRecordId(Long purchaseRecordId)
    {
        return purchaseRecordMapper.deletePurchaseRecordByPurchaseRecordId(purchaseRecordId);
    }

    @Override
    public String importPurRecords(List<PurchaseRecord> purList, Boolean isUpdateSupport)
    {
        if (StringUtils.isNull(purList) || purList.size() == 0)
        {
            throw new ServiceException("导入数据不能为空！");
        }
        int successNum = 0;
        int failureNum = 0;
        StringBuilder successMsg = new StringBuilder();
        StringBuilder failureMsg = new StringBuilder();
        //String password = configService.selectConfigByKey("sys.user.initPassword");
        for (PurchaseRecord pur : purList)
        {
            try
            {
                if(pur.getGoodsName().isEmpty()&&pur.getNormsModel().isEmpty()){
                    continue;
                }
                if(StringUtils.isEmpty(pur.getInDate())){
                    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                    String formattedDate = formatter.format(new Date());
                    pur.setInDate(formattedDate);
                }
                // 验证是否存在这个采购记录
                if (!validpurExistInPurchaseRecord(pur))
                {
                    //更新商品表，如果已存在返回id
                    Long goodsId = updateOfficeGoods(pur);
                    // 新增采购记录
                    pur.setGoodsId(goodsId);
                    purchaseRecordMapper.insertPurchaseRecord(pur);
                    //更新商品库存统计表
                    updateGoodsStatis(pur,goodsId);
                    successNum++;
                }
            }
            catch (Exception e)
            {
                failureNum++;
                String msg = "<br/>" + failureNum + "、商品 " + pur.getGoodsName() + " 导入失败：";
                failureMsg.append(msg + e.getMessage());
                //log.error(msg, e);
            }
        }
        if (failureNum > 0)
        {
            failureMsg.insert(0, "很抱歉，导入失败！共 " + failureNum + " 条数据格式不正确，错误如下：");
            throw new ServiceException(failureMsg.toString());
        }
        else
        {
            successMsg.insert(0, "恭喜您，数据已全部导入成功！共 " + successNum + " 条，数据如下：");
        }
        return successMsg.toString();
    }

    public boolean validpurExistInPurchaseRecord(PurchaseRecord pur){
        PurchaseRecord p = purchaseRecordMapper.selectPurchaseRecordListForValid(pur);
        return p==null?false:true;
    }
    //更新商品表
    public Long updateOfficeGoods(PurchaseRecord pur){
        //更新商品表
        OfficeGoods goods = new OfficeGoods();
        goods.setGoodsName(pur.getGoodsName());
        goods.setNormsModel(pur.getNormsModel());
        goods.setPrice(pur.getPrice());
        List<OfficeGoods> gdList = officeGoodsMapper.selectOfficeGoodsListByNameModel(goods);
        Long goodsId = 0L;
        if(gdList.size() == 0){
            goods.setClassType("日常办公");
            goods.setIsPublic("公用");
            goods.setUnit(pur.getUnit());
            goods.setPrice(pur.getPrice());
            officeGoodsMapper.insertOfficeGoods(goods);
            goodsId = goods.getGoodsId();
        }else{
            goodsId = gdList.get(0).getGoodsId();
        }
        return goodsId;
    }
    //更新库存表
    public void updateGoodsStatis(PurchaseRecord pur,Long goodsId){
        //添加到库存表
        GoodsStatis gs = new GoodsStatis();
        gs.setGoodsName(pur.getGoodsName());
        gs.setNormsModel(pur.getNormsModel());
        List<GoodsStatis> gsList = goodsStatisMapper.selectGoodsStatisListByNameModel(gs);
        if(gsList.size() == 0){
            gs.setGoodsId(goodsId);
            gs.setTotal(new BigDecimal(pur.getQuantity()));
            gs.setRemain(new BigDecimal(pur.getQuantity()));
            gs.setCreateBy(pur.getCreateBy());
            gs.setCreateTime(new Date());
            goodsStatisMapper.insertGoodsStatis(gs);
        }else{
            GoodsStatis gs2 = gsList.get(0);
            gs2.setTotal(gs2.getTotal().add(new BigDecimal(pur.getQuantity())));
            gs2.setRemain(gs2.getRemain().add(new BigDecimal(pur.getQuantity())));
            goodsStatisMapper.updateGoodsStatis(gs2);
        }
    }
}
