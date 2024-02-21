package com.ruoyi.rushsale.controller;

import java.math.BigDecimal;
import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.rushsale.common.constant.Constants;
import com.ruoyi.rushsale.domain.ProRushAccount;
import com.ruoyi.rushsale.domain.ProRushGoods;
import com.ruoyi.rushsale.service.IProRushAccountService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.rushsale.domain.ProRushDealinfo;
import com.ruoyi.rushsale.service.IProRushDealinfoService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 抢购交易信息Controller
 * 
 * @author ruoyi
 * @date 2023-03-24
 */
@RestController
@RequestMapping("/rushsale/dealinfo")
public class ProRushDealinfoController extends BaseController
{
    @Autowired
    private IProRushDealinfoService proRushDealinfoService;
    @Autowired
    private IProRushAccountService proRushAccountService;

    /**
     * 查询抢购交易信息列表
     */
    @PreAuthorize("@ss.hasPermi('rushsale:dealinfo:list')")
    @GetMapping("/list")
    public TableDataInfo list(ProRushDealinfo proRushDealinfo)
    {
        startPage();
        List<ProRushDealinfo> list = proRushDealinfoService.selectProRushDealinfoList(proRushDealinfo);
        return getDataTable(list);
    }

    /**
     * 导出抢购交易信息列表
     */
    @PreAuthorize("@ss.hasPermi('rushsale:dealinfo:export')")
    @Log(title = "抢购交易信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ProRushDealinfo proRushDealinfo)
    {
        List<ProRushDealinfo> list = proRushDealinfoService.selectProRushDealinfoList(proRushDealinfo);
        ExcelUtil<ProRushDealinfo> util = new ExcelUtil<ProRushDealinfo>(ProRushDealinfo.class);
        util.exportExcel(response, list, "抢购交易信息数据");
    }

    /**
     * 获取抢购交易信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('rushsale:dealinfo:query')")
    @GetMapping(value = "/{dealId}")
    public AjaxResult getInfo(@PathVariable("dealId") Long dealId)
    {
        return success(proRushDealinfoService.selectProRushDealinfoByDealId(dealId));
    }

    /**
     * 新增抢购交易信息
     */
    @PreAuthorize("@ss.hasPermi('rushsale:dealinfo:add')")
    @Log(title = "抢购交易信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ProRushDealinfo proRushDealinfo)
    {
        return toAjax(proRushDealinfoService.insertProRushDealinfo(proRushDealinfo));
    }

    /**
     * 修改抢购交易信息
     */
    @PreAuthorize("@ss.hasPermi('rushsale:dealinfo:edit')")
    @Log(title = "抢购交易信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ProRushDealinfo proRushDealinfo)
    {
        String type = proRushDealinfo.getType();
        //修改交易账户
        if(Constants.UPDATE_ACCOUNTNUM.equals(type) || Constants.UPDATE_DEALCANCEL.equals(type)){
            updateAccount(proRushDealinfo);
        }


        return toAjax(proRushDealinfoService.updateProRushDealinfo(proRushDealinfo));
    }

    public void updateAccount(ProRushDealinfo proRushDealinfo){
        String type = proRushDealinfo.getType();
        //交易信息
        String accoountNum = proRushDealinfo.getAccountNum();
        String dealNum = proRushDealinfo.getDealNum();
        BigDecimal dealPrice=new BigDecimal(dealNum);
        String dealType = proRushDealinfo.getDealType();
        //查询待修改账户信息
        ProRushAccount account = proRushAccountService.selectProRushAccountByAccountNum(accoountNum);
        BigDecimal netProfit = account.getNetProfit()==null?new BigDecimal(0):account.getNetProfit();//净利润
        BigDecimal profit = account.getProfit()==null?new BigDecimal(0):account.getProfit();//利润
        BigDecimal remainder = account.getRemainder()==null?new BigDecimal(0):account.getRemainder();//余额
        BigDecimal income = account.getIncome()==null?new BigDecimal(0):account.getIncome();//收入
        Long capital = account.getCapital()==null?0L:account.getCapital();//本金
        //交易生效
        if(Constants.UPDATE_ACCOUNTNUM.equals(type)){
            if(Constants.DEAL_TYPE_BUY.equals(dealType)){
                income = income.subtract(dealPrice);
                remainder = remainder.subtract(dealPrice);
            }
            if(Constants.DEAL_TYPE_SALE.equals(dealType)){
                income = income.add(dealPrice);
                remainder = remainder.add(dealPrice);
            }
        }

        //取消交易
        if(Constants.UPDATE_DEALCANCEL.equals(type)){
            proRushDealinfo.setAccountNum("");
            if(Constants.DEAL_TYPE_BUY.equals(dealType)){
                income = income.add(dealPrice);
                remainder = remainder.add(dealPrice);
            }
            if(Constants.DEAL_TYPE_SALE.equals(dealType)){
                income = income.subtract(dealPrice);
                remainder = remainder.subtract(dealPrice);
            }
        }
        //修改账户信息
        account.setIncome(income);
        account.setRemainder(remainder);
        proRushAccountService.updateProRushAccount(account);
    }

//    @PreAuthorize("@ss.hasPermi('rushsale:dealinfo:edit')")
//    @Log(title = "撤回交易信息", businessType = BusinessType.UPDATE)
//    @PutMapping
//    public AjaxResult handleReback(@RequestBody ProRushDealinfo proRushDealinfo)
//    {
//
//    }

    /**
     * 删除抢购交易信息
     */
    @PreAuthorize("@ss.hasPermi('rushsale:dealinfo:remove')")
    @Log(title = "抢购交易信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{dealIds}")
    public AjaxResult remove(@PathVariable Long[] dealIds)
    {
        return toAjax(proRushDealinfoService.deleteProRushDealinfoByDealIds(dealIds));
    }
}
