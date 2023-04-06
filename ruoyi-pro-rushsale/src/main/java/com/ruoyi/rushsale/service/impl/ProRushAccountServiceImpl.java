package com.ruoyi.rushsale.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.rushsale.mapper.ProRushAccountMapper;
import com.ruoyi.rushsale.domain.ProRushAccount;
import com.ruoyi.rushsale.service.IProRushAccountService;

/**
 * 抢购资金账户Service业务层处理
 * 
 * @author ruoyi
 * @date 2023-04-03
 */
@Service
public class ProRushAccountServiceImpl implements IProRushAccountService 
{
    @Autowired
    private ProRushAccountMapper proRushAccountMapper;

    /**
     * 查询抢购资金账户
     * 
     * @param accountId 抢购资金账户主键
     * @return 抢购资金账户
     */
    @Override
    public ProRushAccount selectProRushAccountByAccountId(Long accountId)
    {
        return proRushAccountMapper.selectProRushAccountByAccountId(accountId);
    }
    @Override
    public ProRushAccount selectProRushAccountByAccountNum(String accountNum)
    {
        return proRushAccountMapper.selectProRushAccountByAccountNum(accountNum);
    }


    /**
     * 查询抢购资金账户列表
     * 
     * @param proRushAccount 抢购资金账户
     * @return 抢购资金账户
     */
    @Override
    public List<ProRushAccount> selectProRushAccountList(ProRushAccount proRushAccount)
    {
        return proRushAccountMapper.selectProRushAccountList(proRushAccount);
    }

    /**
     * 新增抢购资金账户
     * 
     * @param proRushAccount 抢购资金账户
     * @return 结果
     */
    @Override
    public int insertProRushAccount(ProRushAccount proRushAccount)
    {
        proRushAccount.setCreateTime(DateUtils.getNowDate());
        return proRushAccountMapper.insertProRushAccount(proRushAccount);
    }

    /**
     * 修改抢购资金账户
     * 
     * @param proRushAccount 抢购资金账户
     * @return 结果
     */
    @Override
    public int updateProRushAccount(ProRushAccount proRushAccount)
    {
        proRushAccount.setUpdateTime(DateUtils.getNowDate());
        return proRushAccountMapper.updateProRushAccount(proRushAccount);
    }

    /**
     * 批量删除抢购资金账户
     * 
     * @param accountIds 需要删除的抢购资金账户主键
     * @return 结果
     */
    @Override
    public int deleteProRushAccountByAccountIds(Long[] accountIds)
    {
        return proRushAccountMapper.deleteProRushAccountByAccountIds(accountIds);
    }

    /**
     * 删除抢购资金账户信息
     * 
     * @param accountId 抢购资金账户主键
     * @return 结果
     */
    @Override
    public int deleteProRushAccountByAccountId(Long accountId)
    {
        return proRushAccountMapper.deleteProRushAccountByAccountId(accountId);
    }
}
