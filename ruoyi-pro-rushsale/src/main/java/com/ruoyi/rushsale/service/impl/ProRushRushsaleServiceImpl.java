package com.ruoyi.rushsale.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.rushsale.mapper.ProRushRushsaleMapper;
import com.ruoyi.rushsale.domain.ProRushRushsale;
import com.ruoyi.rushsale.service.IProRushRushsaleService;

/**
 * 出售交易信息Service业务层处理
 * 
 * @author zhujw
 * @date 2023-03-24
 */
@Service
public class ProRushRushsaleServiceImpl implements IProRushRushsaleService 
{
    @Autowired
    private ProRushRushsaleMapper proRushRushsaleMapper;

    /**
     * 查询出售交易信息
     * 
     * @param rushsaleId 出售交易信息主键
     * @return 出售交易信息
     */
    @Override
    public ProRushRushsale selectProRushRushsaleByRushsaleId(Long rushsaleId)
    {
        return proRushRushsaleMapper.selectProRushRushsaleByRushsaleId(rushsaleId);
    }

    /**
     * 查询出售交易信息列表
     * 
     * @param proRushRushsale 出售交易信息
     * @return 出售交易信息
     */
    @Override
    public List<ProRushRushsale> selectProRushRushsaleList(ProRushRushsale proRushRushsale)
    {
        return proRushRushsaleMapper.selectProRushRushsaleList(proRushRushsale);
    }

    /**
     * 新增出售交易信息
     * 
     * @param proRushRushsale 出售交易信息
     * @return 结果
     */
    @Override
    public int insertProRushRushsale(ProRushRushsale proRushRushsale)
    {
        proRushRushsale.setCreateTime(DateUtils.getNowDate());
        return proRushRushsaleMapper.insertProRushRushsale(proRushRushsale);
    }

    /**
     * 修改出售交易信息
     * 
     * @param proRushRushsale 出售交易信息
     * @return 结果
     */
    @Override
    public int updateProRushRushsale(ProRushRushsale proRushRushsale)
    {
        proRushRushsale.setUpdateTime(DateUtils.getNowDate());
        return proRushRushsaleMapper.updateProRushRushsale(proRushRushsale);
    }
    @Override
    public int updateProRushRushsaleByConditon(ProRushRushsale proRushRushsale)
    {
        proRushRushsale.setUpdateTime(DateUtils.getNowDate());
        return proRushRushsaleMapper.updateProRushRushsaleByConditon(proRushRushsale);
    }
    /**
     * 批量删除出售交易信息
     * 
     * @param rushsaleIds 需要删除的出售交易信息主键
     * @return 结果
     */
    @Override
    public int deleteProRushRushsaleByRushsaleIds(Long[] rushsaleIds)
    {
        return proRushRushsaleMapper.deleteProRushRushsaleByRushsaleIds(rushsaleIds);
    }

    /**
     * 删除出售交易信息信息
     * 
     * @param rushsaleId 出售交易信息主键
     * @return 结果
     */
    @Override
    public int deleteProRushRushsaleByRushsaleId(Long rushsaleId)
    {
        return proRushRushsaleMapper.deleteProRushRushsaleByRushsaleId(rushsaleId);
    }
}
