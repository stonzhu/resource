package com.ruoyi.receive.controller;

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
import com.ruoyi.receive.domain.ReceiveRecord;
import com.ruoyi.receive.service.IReceiveRecordService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 领用记录Controller
 * 
 * @author ruoyi
 * @date 2024-02-19
 */
@RestController
@RequestMapping("/receive/receiveRecord")
public class ReceiveRecordController extends BaseController
{
    @Autowired
    private IReceiveRecordService receiveRecordService;

    /**
     * 查询领用记录列表
     */
    @PreAuthorize("@ss.hasPermi('receive:receiveRecord:list')")
    @GetMapping("/list")
    public TableDataInfo list(ReceiveRecord receiveRecord)
    {
        startPage();
        List<ReceiveRecord> list = receiveRecordService.selectReceiveRecordList(receiveRecord);
        return getDataTable(list);
    }

    /**
     * 导出领用记录列表
     */
    @PreAuthorize("@ss.hasPermi('receive:receiveRecord:export')")
    @Log(title = "领用记录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ReceiveRecord receiveRecord)
    {
        List<ReceiveRecord> list = receiveRecordService.selectReceiveRecordList(receiveRecord);
        ExcelUtil<ReceiveRecord> util = new ExcelUtil<ReceiveRecord>(ReceiveRecord.class);
        util.exportExcel(response, list, "领用记录数据");
    }

    /**
     * 获取领用记录详细信息
     */
    @PreAuthorize("@ss.hasPermi('receive:receiveRecord:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(receiveRecordService.selectReceiveRecordById(id));
    }

    /**
     * 新增领用记录
     */
    @PreAuthorize("@ss.hasPermi('receive:receiveRecord:add')")
    @Log(title = "领用记录", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ReceiveRecord receiveRecord)
    {
        return toAjax(receiveRecordService.insertReceiveRecord(receiveRecord));
    }

    /**
     * 修改领用记录
     */
    @PreAuthorize("@ss.hasPermi('receive:receiveRecord:edit')")
    @Log(title = "领用记录", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ReceiveRecord receiveRecord)
    {
        return toAjax(receiveRecordService.updateReceiveRecord(receiveRecord));
    }

    /**
     * 删除领用记录
     */
    @PreAuthorize("@ss.hasPermi('receive:receiveRecord:remove')")
    @Log(title = "领用记录", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(receiveRecordService.deleteReceiveRecordByIds(ids));
    }
}
