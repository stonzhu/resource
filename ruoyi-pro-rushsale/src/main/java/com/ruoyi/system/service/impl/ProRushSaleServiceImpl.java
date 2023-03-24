package com.ruoyi.system.service.impl;

import java.util.List;

import com.ruoyi.common.annotation.DataScope;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.bean.BeanValidators;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.ProRushSaleMapper;
import com.ruoyi.system.domain.ProRushSale;
import com.ruoyi.system.service.IProRushSaleService;

import javax.validation.Validator;

/**
 * 抢购买卖Service业务层处理
 * 
 * @author ruoyi
 * @date 2023-03-22
 */
@Service
public class ProRushSaleServiceImpl implements IProRushSaleService 
{
    @Autowired
    private ProRushSaleMapper proRushSaleMapper;

    @Autowired
    protected Validator validator;

    /**
     * 查询抢购买卖
     * 
     * @param proRushSaleId 抢购买卖主键
     * @return 抢购买卖
     */
    @Override
    public ProRushSale selectProRushSaleByProRushSaleId(Long proRushSaleId)
    {
        return proRushSaleMapper.selectProRushSaleByProRushSaleId(proRushSaleId);
    }

    /**
     * 查询抢购买卖列表
     * 
     * @param proRushSale 抢购买卖
     * @return 抢购买卖
     */
    @Override
    @DataScope(deptAlias = "pro_rush_sale")
    public List<ProRushSale> selectProRushSaleList(ProRushSale proRushSale)
    {
        return proRushSaleMapper.selectProRushSaleList(proRushSale);
    }

    /**
     * 新增抢购买卖
     * 
     * @param proRushSale 抢购买卖
     * @return 结果
     */
    @Override
    public int insertProRushSale(ProRushSale proRushSale)
    {
        proRushSale.setCreateTime(DateUtils.getNowDate());
        if ("".equals(proRushSale.getInPeople())){
            String userName = SecurityUtils.getUsername();
            proRushSale.setInPeople(userName);
        }
        return proRushSaleMapper.insertProRushSale(proRushSale);
    }

    /**
     * 修改抢购买卖
     * 
     * @param proRushSale 抢购买卖
     * @return 结果
     */
    @Override
    public int updateProRushSale(ProRushSale proRushSale)
    {
        proRushSale.setUpdateTime(DateUtils.getNowDate());
        return proRushSaleMapper.updateProRushSale(proRushSale);
    }

    /**
     * 批量删除抢购买卖
     * 
     * @param proRushSaleIds 需要删除的抢购买卖主键
     * @return 结果
     */
    @Override
    public int deleteProRushSaleByProRushSaleIds(Long[] proRushSaleIds)
    {
        return proRushSaleMapper.deleteProRushSaleByProRushSaleIds(proRushSaleIds);
    }

    /**
     * 删除抢购买卖信息
     * 
     * @param proRushSaleId 抢购买卖主键
     * @return 结果
     */
    @Override
    public int deleteProRushSaleByProRushSaleId(Long proRushSaleId)
    {
        return proRushSaleMapper.deleteProRushSaleByProRushSaleId(proRushSaleId);
    }

    @Override
    public String importRush(List<ProRushSale> rushList, Boolean isUpdateSupport, String operName) {
        if (StringUtils.isNull(rushList) || rushList.size() == 0)
        {
            throw new ServiceException("导入用户数据不能为空！");
        }
        int successNum = 0;
        StringBuilder successMsg = new StringBuilder();
        for(ProRushSale rush :rushList){
            BeanValidators.validateWithException(validator, rush);
            this.insertProRushSale(rush);
            successNum++;
        }
        if(successNum>0&&successNum==rushList.size()){
            successMsg.append("<br/>" + successNum + "条 导入成功");
        }else{
            return "fail";
        }
        return successMsg.toString();
    }
}
