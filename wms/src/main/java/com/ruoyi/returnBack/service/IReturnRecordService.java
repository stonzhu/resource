package com.ruoyi.returnBack.service;

import java.util.List;
import com.ruoyi.returnBack.domain.ReturnRecord;

/**
 * 交还记录Service接口
 * 
 * @author ruoyi
 * @date 2024-02-19
 */
public interface IReturnRecordService 
{
    /**
     * 查询交还记录
     * 
     * @param id 交还记录主键
     * @return 交还记录
     */
    public ReturnRecord selectReturnRecordById(Long id);

    /**
     * 查询交还记录列表
     * 
     * @param returnRecord 交还记录
     * @return 交还记录集合
     */
    public List<ReturnRecord> selectReturnRecordList(ReturnRecord returnRecord);

    /**
     * 新增交还记录
     * 
     * @param returnRecord 交还记录
     * @return 结果
     */
    public int insertReturnRecord(ReturnRecord returnRecord);

    /**
     * 修改交还记录
     * 
     * @param returnRecord 交还记录
     * @return 结果
     */
    public int updateReturnRecord(ReturnRecord returnRecord);

    /**
     * 批量删除交还记录
     * 
     * @param ids 需要删除的交还记录主键集合
     * @return 结果
     */
    public int deleteReturnRecordByIds(Long[] ids);

    /**
     * 删除交还记录信息
     * 
     * @param id 交还记录主键
     * @return 结果
     */
    public int deleteReturnRecordById(Long id);
}
