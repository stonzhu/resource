package com.ruoyi.system.controller;

import java.io.IOException;
import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.common.annotation.DataScope;
import com.ruoyi.common.core.domain.entity.SysUser;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
import com.ruoyi.system.domain.ProRushSale;
import com.ruoyi.system.service.IProRushSaleService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;
import org.springframework.web.multipart.MultipartFile;

/**
 * 抢购买卖Controller
 * 
 * @author ruoyi
 * @date 2023-03-22
 */


@Api("抢购买卖")
@RestController
@RequestMapping("/system/sale")
public class ProRushSaleController extends BaseController
{
    @Autowired
    private IProRushSaleService proRushSaleService;

    /**
     * 查询抢购买卖列表
     */

    @ApiOperation("获取展示数据")
    @PreAuthorize("@ss.hasPermi('system:sale:query')")
    @GetMapping("/list")
    public TableDataInfo list(ProRushSale proRushSale)
    {
        startPage();
        List<ProRushSale> list = proRushSaleService.selectProRushSaleList(proRushSale);
        return getDataTable(list);
    }

    /**
     * 导出抢购买卖列表
     */
    @ApiOperation("导出数据")
    @PreAuthorize("@ss.hasPermi('system:sale:export')")
    @Log(title = "抢购买卖", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ProRushSale proRushSale)
    {
        List<ProRushSale> list = proRushSaleService.selectProRushSaleList(proRushSale);
        ExcelUtil<ProRushSale> util = new ExcelUtil<ProRushSale>(ProRushSale.class);
        util.exportExcel(response, list, "抢购买卖数据");
    }

    /**
     * 获取抢购买卖详细信息
     */
    @ApiOperation("获取抢购买卖详细信息")
    @PreAuthorize("@ss.hasPermi('system:sale:query')")
    @GetMapping(value = "/{proRushSaleId}")
    public AjaxResult getInfo(@PathVariable("proRushSaleId") Long proRushSaleId)
    {
        return success(proRushSaleService.selectProRushSaleByProRushSaleId(proRushSaleId));
    }

    /**
     * 新增抢购买卖
     */
    @ApiOperation("新增抢购买卖")
    @PreAuthorize("@ss.hasPermi('system:sale:add')")
    @Log(title = "抢购买卖", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ProRushSale proRushSale)
    {
        return toAjax(proRushSaleService.insertProRushSale(proRushSale));
    }

    /**
     * 修改抢购买卖
     */
    @ApiOperation("修改抢购买卖")
    @PreAuthorize("@ss.hasPermi('system:sale:edit')")
    @Log(title = "抢购买卖", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ProRushSale proRushSale)
    {
        return toAjax(proRushSaleService.updateProRushSale(proRushSale));
    }

    /**
     * 删除抢购买卖
     */
    @ApiOperation("删除抢购买卖")
    @PreAuthorize("@ss.hasPermi('system:sale:remove')")
    @Log(title = "抢购买卖", businessType = BusinessType.DELETE)
	@DeleteMapping("/{proRushSaleIds}")
    public AjaxResult remove(@PathVariable Long[] proRushSaleIds)
    {
        return toAjax(proRushSaleService.deleteProRushSaleByProRushSaleIds(proRushSaleIds));
    }

    /**
     * 导入抢购买卖
     */
    @PreAuthorize("@ss.hasPermi('system:sale:import')")
    @Log(title = "抢购买卖", businessType = BusinessType.IMPORT)
    @PostMapping("/importRushData")
    public AjaxResult importRush(MultipartFile file, boolean updateSupport) throws Exception {
        ExcelUtil<ProRushSale> util = new ExcelUtil<ProRushSale>(ProRushSale.class);
        List<ProRushSale> ProRushSaleList = util.importExcel(file.getInputStream());

        return success("message");
    }
}
