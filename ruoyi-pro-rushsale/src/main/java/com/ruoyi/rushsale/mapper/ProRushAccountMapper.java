package com.ruoyi.rushsale.mapper;

import java.util.List;
import com.ruoyi.rushsale.domain.ProRushAccount;

/**
 * 抢购资金账户Mapper接口
 * 
 * @author ruoyi
 * @date 2023-04-03
 */
public interface ProRushAccountMapper 
{
    /**
     * 查询抢购资金账户
     * 
     * @param accountId 抢购资金账户主键
     * @return 抢购资金账户
     */
    public ProRushAccount selectProRushAccountByAccountId(Long accountId);
    public ProRushAccount selectProRushAccountByAccountNum(String accountNum);

    /**
     * 查询抢购资金账户列表
     * 
     * @param proRushAccount 抢购资金账户
     * @return 抢购资金账户集合
     */
    public List<ProRushAccount> selectProRushAccountList(ProRushAccount proRushAccount);

    /**
     * 新增抢购资金账户
     * 
     * @param proRushAccount 抢购资金账户
     * @return 结果
     */
    public int insertProRushAccount(ProRushAccount proRushAccount);

    /**
     * 修改抢购资金账户
     * 
     * @param proRushAccount 抢购资金账户
     * @return 结果
     */
    public int updateProRushAccount(ProRushAccount proRushAccount);

    /**
     * 删除抢购资金账户
     * 
     * @param accountId 抢购资金账户主键
     * @return 结果
     */
    public int deleteProRushAccountByAccountId(Long accountId);

    /**
     * 批量删除抢购资金账户
     * 
     * @param accountIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteProRushAccountByAccountIds(Long[] accountIds);
}
