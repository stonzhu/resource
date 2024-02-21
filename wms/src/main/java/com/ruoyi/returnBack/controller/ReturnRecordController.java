package com.ruoyi.returnBack.controller;

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
import com.ruoyi.returnBack.domain.ReturnRecord;
import com.ruoyi.returnBack.service.IReturnRecordService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 交还记录Controller
 * 
 * @author ruoyi
 * @date 2024-02-19
 */
@RestController
@RequestMapping("/return/returnRecord")
public class ReturnRecordController extends BaseController
{
    @Autowired
    private IReturnRecordService returnRecordService;

    /**
     * 查询交还记录列表
     */
    @PreAuthorize("@ss.hasPermi('return:returnRecord:list')")
    @GetMapping("/list")
    public TableDataInfo list(ReturnRecord returnRecord)
    {
        startPage();
        List<ReturnRecord> list = returnRecordService.selectReturnRecordList(returnRecord);
        return getDataTable(list);
    }

    /**
     * 导出交还记录列表
     */
    @PreAuthorize("@ss.hasPermi('return:returnRecord:export')")
    @Log(title = "交还记录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ReturnRecord returnRecord)
    {
        List<ReturnRecord> list = returnRecordService.selectReturnRecordList(returnRecord);
        ExcelUtil<ReturnRecord> util = new ExcelUtil<ReturnRecord>(ReturnRecord.class);
        util.exportExcel(response, list, "交还记录数据");
    }

    /**
     * 获取交还记录详细信息
     */
    @PreAuthorize("@ss.hasPermi('return:returnRecord:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(returnRecordService.selectReturnRecordById(id));
    }

    /**
     * 新增交还记录
     */
    @PreAuthorize("@ss.hasPermi('return:returnRecord:add')")
    @Log(title = "交还记录", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ReturnRecord returnRecord)
    {
        return toAjax(returnRecordService.insertReturnRecord(returnRecord));
    }

    /**
     * 修改交还记录
     */
    @PreAuthorize("@ss.hasPermi('return:returnRecord:edit')")
    @Log(title = "交还记录", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ReturnRecord returnRecord)
    {
        return toAjax(returnRecordService.updateReturnRecord(returnRecord));
    }

    /**
     * 删除交还记录
     */
    @PreAuthorize("@ss.hasPermi('return:returnRecord:remove')")
    @Log(title = "交还记录", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(returnRecordService.deleteReturnRecordByIds(ids));
    }
}
