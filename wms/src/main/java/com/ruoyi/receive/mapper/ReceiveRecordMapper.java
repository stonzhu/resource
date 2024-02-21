package com.ruoyi.receive.mapper;

import java.util.List;
import com.ruoyi.receive.domain.ReceiveRecord;

/**
 * 领用记录Mapper接口
 * 
 * @author ruoyi
 * @date 2024-02-19
 */
public interface ReceiveRecordMapper 
{
    /**
     * 查询领用记录
     * 
     * @param id 领用记录主键
     * @return 领用记录
     */
    public ReceiveRecord selectReceiveRecordById(Long id);

    /**
     * 查询领用记录列表
     * 
     * @param receiveRecord 领用记录
     * @return 领用记录集合
     */
    public List<ReceiveRecord> selectReceiveRecordList(ReceiveRecord receiveRecord);

    /**
     * 新增领用记录
     * 
     * @param receiveRecord 领用记录
     * @return 结果
     */
    public int insertReceiveRecord(ReceiveRecord receiveRecord);

    /**
     * 修改领用记录
     * 
     * @param receiveRecord 领用记录
     * @return 结果
     */
    public int updateReceiveRecord(ReceiveRecord receiveRecord);

    /**
     * 删除领用记录
     * 
     * @param id 领用记录主键
     * @return 结果
     */
    public int deleteReceiveRecordById(Long id);

    /**
     * 批量删除领用记录
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteReceiveRecordByIds(Long[] ids);
}
