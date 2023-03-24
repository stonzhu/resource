package com.ruoyi.rushsale.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
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
import com.ruoyi.rushsale.domain.ProRushAccount;
import com.ruoyi.rushsale.service.IProRushAccountService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 抢购资金账户Controller
 * 
 * @author ruoyi
 * @date 2023-03-24
 */
@RestController
@RequestMapping("/rushsale/account")
public class ProRushAccountController extends BaseController
{
    @Autowired
    private IProRushAccountService proRushAccountService;

    /**
     * 查询抢购资金账户列表
     */
    @PreAuthorize("@ss.hasPermi('rushsale:account:list')")
    @GetMapping("/list")
    public TableDataInfo list(ProRushAccount proRushAccount)
    {
        startPage();
        List<ProRushAccount> list = proRushAccountService.selectProRushAccountList(proRushAccount);
        return getDataTable(list);
    }

    /**
     * 导出抢购资金账户列表
     */
    @PreAuthorize("@ss.hasPermi('rushsale:account:export')")
    @Log(title = "抢购资金账户", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ProRushAccount proRushAccount)
    {
        List<ProRushAccount> list = proRushAccountService.selectProRushAccountList(proRushAccount);
        ExcelUtil<ProRushAccount> util = new ExcelUtil<ProRushAccount>(ProRushAccount.class);
        util.exportExcel(response, list, "抢购资金账户数据");
    }

    /**
     * 获取抢购资金账户详细信息
     */
    @PreAuthorize("@ss.hasPermi('rushsale:account:query')")
    @GetMapping(value = "/{accountId}")
    public AjaxResult getInfo(@PathVariable("accountId") Long accountId)
    {
        return success(proRushAccountService.selectProRushAccountByAccountId(accountId));
    }

    /**
     * 新增抢购资金账户
     */
    @PreAuthorize("@ss.hasPermi('rushsale:account:add')")
    @Log(title = "抢购资金账户", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ProRushAccount proRushAccount)
    {
        return toAjax(proRushAccountService.insertProRushAccount(proRushAccount));
    }

    /**
     * 修改抢购资金账户
     */
    @PreAuthorize("@ss.hasPermi('rushsale:account:edit')")
    @Log(title = "抢购资金账户", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ProRushAccount proRushAccount)
    {
        return toAjax(proRushAccountService.updateProRushAccount(proRushAccount));
    }

    /**
     * 删除抢购资金账户
     */
    @PreAuthorize("@ss.hasPermi('rushsale:account:remove')")
    @Log(title = "抢购资金账户", businessType = BusinessType.DELETE)
	@DeleteMapping("/{accountIds}")
    public AjaxResult remove(@PathVariable Long[] accountIds)
    {
        return toAjax(proRushAccountService.deleteProRushAccountByAccountIds(accountIds));
    }
}
