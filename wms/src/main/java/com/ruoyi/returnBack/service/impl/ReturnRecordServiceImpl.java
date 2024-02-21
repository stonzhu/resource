package com.ruoyi.returnBack.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.returnBack.mapper.ReturnRecordMapper;
import com.ruoyi.returnBack.domain.ReturnRecord;
import com.ruoyi.returnBack.service.IReturnRecordService;

/**
 * 交还记录Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-02-19
 */
@Service
public class ReturnRecordServiceImpl implements IReturnRecordService 
{
    @Autowired
    private ReturnRecordMapper returnRecordMapper;

    /**
     * 查询交还记录
     * 
     * @param id 交还记录主键
     * @return 交还记录
     */
    @Override
    public ReturnRecord selectReturnRecordById(Long id)
    {
        return returnRecordMapper.selectReturnRecordById(id);
    }

    /**
     * 查询交还记录列表
     * 
     * @param returnRecord 交还记录
     * @return 交还记录
     */
    @Override
    public List<ReturnRecord> selectReturnRecordList(ReturnRecord returnRecord)
    {
        return returnRecordMapper.selectReturnRecordList(returnRecord);
    }

    /**
     * 新增交还记录
     * 
     * @param returnRecord 交还记录
     * @return 结果
     */
    @Override
    public int insertReturnRecord(ReturnRecord returnRecord)
    {
        return returnRecordMapper.insertReturnRecord(returnRecord);
    }

    /**
     * 修改交还记录
     * 
     * @param returnRecord 交还记录
     * @return 结果
     */
    @Override
    public int updateReturnRecord(ReturnRecord returnRecord)
    {
        return returnRecordMapper.updateReturnRecord(returnRecord);
    }

    /**
     * 批量删除交还记录
     * 
     * @param ids 需要删除的交还记录主键
     * @return 结果
     */
    @Override
    public int deleteReturnRecordByIds(Long[] ids)
    {
        return returnRecordMapper.deleteReturnRecordByIds(ids);
    }

    /**
     * 删除交还记录信息
     * 
     * @param id 交还记录主键
     * @return 结果
     */
    @Override
    public int deleteReturnRecordById(Long id)
    {
        return returnRecordMapper.deleteReturnRecordById(id);
    }
}
