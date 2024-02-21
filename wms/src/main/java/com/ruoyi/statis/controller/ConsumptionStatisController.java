package com.ruoyi.statis.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.statis.domain.ConsumptionStatis;
import com.ruoyi.statis.service.IConsumptionStatisService;
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
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 商品消耗统计Controller
 * 
 * @author 朱建伟
 * @date 2024-02-19
 */
@RestController
@RequestMapping("/wms/consumestatis")
public class ConsumptionStatisController extends BaseController
{
    @Autowired
    private IConsumptionStatisService consumptionStatisService;

    /**
     * 查询商品消耗统计列表
     */
    @PreAuthorize("@ss.hasPermi('wms:consumestatis:list')")
    @GetMapping("/list")
    public TableDataInfo list(ConsumptionStatis consumptionStatis)
    {
        startPage();
        List<ConsumptionStatis> list = consumptionStatisService.selectConsumptionStatisList(consumptionStatis);
        return getDataTable(list);
    }

    /**
     * 导出商品消耗统计列表
     */
    @PreAuthorize("@ss.hasPermi('wms:consumestatis:export')")
    @Log(title = "商品消耗统计", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ConsumptionStatis consumptionStatis)
    {
        List<ConsumptionStatis> list = consumptionStatisService.selectConsumptionStatisList(consumptionStatis);
        ExcelUtil<ConsumptionStatis> util = new ExcelUtil<ConsumptionStatis>(ConsumptionStatis.class);
        util.exportExcel(response, list, "商品消耗统计数据");
    }

    /**
     * 获取商品消耗统计详细信息
     */
    @PreAuthorize("@ss.hasPermi('wms:consumestatis:query')")
    @GetMapping(value = "/{consumptionStatisId}")
    public AjaxResult getInfo(@PathVariable("consumptionStatisId") Long consumptionStatisId)
    {
        return success(consumptionStatisService.selectConsumptionStatisByConsumptionStatisId(consumptionStatisId));
    }

    /**
     * 新增商品消耗统计
     */
    @PreAuthorize("@ss.hasPermi('wms:consumestatis:add')")
    @Log(title = "商品消耗统计", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ConsumptionStatis consumptionStatis)
    {
        return toAjax(consumptionStatisService.insertConsumptionStatis(consumptionStatis));
    }

    /**
     * 修改商品消耗统计
     */
    @PreAuthorize("@ss.hasPermi('wms:consumestatis:edit')")
    @Log(title = "商品消耗统计", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ConsumptionStatis consumptionStatis)
    {
        return toAjax(consumptionStatisService.updateConsumptionStatis(consumptionStatis));
    }

    /**
     * 删除商品消耗统计
     */
    @PreAuthorize("@ss.hasPermi('wms:consumestatis:remove')")
    @Log(title = "商品消耗统计", businessType = BusinessType.DELETE)
	@DeleteMapping("/{consumptionStatisIds}")
    public AjaxResult remove(@PathVariable Long[] consumptionStatisIds)
    {
        return toAjax(consumptionStatisService.deleteConsumptionStatisByConsumptionStatisIds(consumptionStatisIds));
    }
}
