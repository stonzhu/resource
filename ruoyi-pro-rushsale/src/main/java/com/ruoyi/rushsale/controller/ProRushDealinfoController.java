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
        return toAjax(proRushDealinfoService.updateProRushDealinfo(proRushDealinfo));
    }

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
