package com.ruoyi.rushsale.mapper;

import java.util.List;
import com.ruoyi.rushsale.domain.ProRushRushsale;

/**
 * 出售交易信息Mapper接口
 * 
 * @author zhujw
 * @date 2023-03-24
 */
public interface ProRushRushsaleMapper 
{
    /**
     * 查询出售交易信息
     * 
     * @param rushsaleId 出售交易信息主键
     * @return 出售交易信息
     */
    public ProRushRushsale selectProRushRushsaleByRushsaleId(Long rushsaleId);

    /**
     * 查询出售交易信息列表
     * 
     * @param proRushRushsale 出售交易信息
     * @return 出售交易信息集合
     */
    public List<ProRushRushsale> selectProRushRushsaleList(ProRushRushsale proRushRushsale);

    /**
     * 新增出售交易信息
     * 
     * @param proRushRushsale 出售交易信息
     * @return 结果
     */
    public int insertProRushRushsale(ProRushRushsale proRushRushsale);

    /**
     * 修改出售交易信息
     * 
     * @param proRushRushsale 出售交易信息
     * @return 结果
     */
    public int updateProRushRushsale(ProRushRushsale proRushRushsale);

    /**
     * 删除出售交易信息
     * 
     * @param rushsaleId 出售交易信息主键
     * @return 结果
     */
    public int deleteProRushRushsaleByRushsaleId(Long rushsaleId);

    /**
     * 批量删除出售交易信息
     * 
     * @param rushsaleIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteProRushRushsaleByRushsaleIds(Long[] rushsaleIds);
}
