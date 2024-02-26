package com.ruoyi.purchase.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.common.core.domain.entity.SysUser;
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
import com.ruoyi.purchase.domain.PurchaseRecord;
import com.ruoyi.purchase.service.IPurchaseRecordService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;
import org.springframework.web.multipart.MultipartFile;

/**
 * 采购记录Controller
 * 
 * @author ruoyi
 * @date 2024-02-19
 */
@RestController
@RequestMapping("/purchase/purchaseRecord")
public class PurchaseRecordController extends BaseController
{
    @Autowired
    private IPurchaseRecordService purchaseRecordService;

    /**
     * 查询采购记录列表
     */
    @PreAuthorize("@ss.hasPermi('purchase:purchaseRecord:list')")
    @GetMapping("/list")
    public TableDataInfo list(PurchaseRecord purchaseRecord)
    {
        startPage();
        List<PurchaseRecord> list = purchaseRecordService.selectPurchaseRecordList(purchaseRecord);
        return getDataTable(list);
    }

    /**
     * 导出采购记录列表
     */
    @PreAuthorize("@ss.hasPermi('purchase:purchaseRecord:export')")
    @Log(title = "采购记录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, PurchaseRecord purchaseRecord)
    {
        List<PurchaseRecord> list = purchaseRecordService.selectPurchaseRecordList(purchaseRecord);
        ExcelUtil<PurchaseRecord> util = new ExcelUtil<PurchaseRecord>(PurchaseRecord.class);
        util.exportExcel(response, list, "采购记录数据");
    }

    /**
     * 获取采购记录详细信息
     */
    @PreAuthorize("@ss.hasPermi('purchase:purchaseRecord:query')")
    @GetMapping(value = "/{purchaseRecordId}")
    public AjaxResult getInfo(@PathVariable("purchaseRecordId") Long purchaseRecordId)
    {
        return success(purchaseRecordService.selectPurchaseRecordByPurchaseRecordId(purchaseRecordId));
    }

    /**
     * 新增采购记录
     */
    @PreAuthorize("@ss.hasPermi('purchase:purchaseRecord:add')")
    @Log(title = "采购记录", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody PurchaseRecord purchaseRecord)
    {
        return success(purchaseRecordService.insertPurchaseRecord(purchaseRecord));
    }

    /**
     * 修改采购记录
     */
    @PreAuthorize("@ss.hasPermi('purchase:purchaseRecord:edit')")
    @Log(title = "采购记录", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody PurchaseRecord purchaseRecord)
    {
        return toAjax(purchaseRecordService.updatePurchaseRecord(purchaseRecord));
    }

    /**
     * 删除采购记录
     */
    @PreAuthorize("@ss.hasPermi('purchase:purchaseRecord:remove')")
    @Log(title = "采购记录", businessType = BusinessType.DELETE)
	@DeleteMapping("/{purchaseRecordIds}")
    public AjaxResult remove(@PathVariable Long[] purchaseRecordIds)
    {
        return toAjax(purchaseRecordService.deletePurchaseRecordByPurchaseRecordIds(purchaseRecordIds));
    }

    /**
     * 导入采购信息
     */
    @Log(title = "采购信息", businessType = BusinessType.IMPORT)
    @PreAuthorize("@ss.hasPermi('purchase:purchaseRecord:import')")
    @PostMapping("/importData")
    public AjaxResult importData(MultipartFile file, boolean updateSupport) throws Exception
    {
        ExcelUtil<PurchaseRecord> util = new ExcelUtil<PurchaseRecord>(PurchaseRecord.class);
        List<PurchaseRecord> purList = util.importExcel(file.getInputStream());
        //String operName = getUsername();
        String message = purchaseRecordService.importPurRecords(purList, updateSupport);
        return success(message);
    }

    @PostMapping("/importTemplate")
    public void importTemplate(HttpServletResponse response)
    {
        ExcelUtil<PurchaseRecord> util = new ExcelUtil<PurchaseRecord>(PurchaseRecord.class);
        util.importTemplateExcel(response, "采购数据");
    }
}
