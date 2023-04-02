package com.ruoyi.rushsale.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.rushsale.common.constant.Constants;
import com.ruoyi.rushsale.domain.ProRushDealinfo;
import com.ruoyi.rushsale.service.IProRushDealinfoService;
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
import com.ruoyi.rushsale.domain.ProRushRushsale;
import com.ruoyi.rushsale.service.IProRushRushsaleService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 出售交易信息Controller
 * 
 * @author zhujw
 * @date 2023-03-24
 */
@RestController
@RequestMapping("/rushsale/rushsale")
public class ProRushRushsaleController extends BaseController
{
    @Autowired
    private IProRushRushsaleService proRushRushsaleService;

    @Autowired
    private IProRushDealinfoService proRushDealinfoService;

    /**
     * 查询出售交易信息列表
     */
    @PreAuthorize("@ss.hasPermi('rushsale:rushsale:list')")
    @GetMapping("/list")
    public TableDataInfo list(ProRushRushsale proRushRushsale)
    {
        startPage();
        List<ProRushRushsale> list = proRushRushsaleService.selectProRushRushsaleList(proRushRushsale);
        return getDataTable(list);
    }

    /**
     * 导出出售交易信息列表
     */
    @PreAuthorize("@ss.hasPermi('rushsale:rushsale:export')")
    @Log(title = "出售交易信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ProRushRushsale proRushRushsale)
    {
        List<ProRushRushsale> list = proRushRushsaleService.selectProRushRushsaleList(proRushRushsale);
        ExcelUtil<ProRushRushsale> util = new ExcelUtil<ProRushRushsale>(ProRushRushsale.class);
        util.exportExcel(response, list, "出售交易信息数据");
    }

    /**
     * 获取出售交易信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('rushsale:rushsale:query')")
    @GetMapping(value = "/{rushsaleId}")
    public AjaxResult getInfo(@PathVariable("rushsaleId") Long rushsaleId)
    {
        return success(proRushRushsaleService.selectProRushRushsaleByRushsaleId(rushsaleId));
    }

    /**
     * 新增出售交易信息
     */
    @PreAuthorize("@ss.hasPermi('rushsale:rushsale:add')")
    @Log(title = "出售交易信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ProRushRushsale proRushRushsale)
    {
        return toAjax(proRushRushsaleService.insertProRushRushsale(proRushRushsale));
    }

    /**
     * 修改出售交易信息
     */
    @PreAuthorize("@ss.hasPermi('rushsale:rushsale:edit')")
    @Log(title = "出售交易信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ProRushRushsale proRushRushsale)
    {
        String type = proRushRushsale.getType();
        //修改结算状态，生成交易信息
        if(Constants.UPDATE_DEALSTATE.equals(type)){
            ProRushDealinfo proRushDealinfo = new ProRushDealinfo();
            //proRushDealinfo.setGoodsId(proRushRushsale.getGoodsId());
            proRushDealinfo.setDealFrom(proRushRushsale.getPayFrom());
            proRushDealinfo.setDealTo(getUserId().toString());
            proRushDealinfo.setDealNum(proRushRushsale.getDealPrice());
            proRushDealinfo.setDealType(Constants.DEAL_TYPE_SALE);
            if(Constants.DEAL_STATUS_COMPLETE.equals(proRushRushsale.getDealState())){
                proRushDealinfo.setDealStatus(Constants.DEAL_STATUS_VALID);
            }else{
                proRushDealinfo.setDealStatus(Constants.DEAL_STATUS_INVALID);
            }
            proRushDealinfo.setDealTime(DateUtils.getNowDate());
            proRushDealinfo.setHandleOrderId(proRushRushsale.getRushsaleId());
            if(proRushDealinfoService.updateProRushDealinfoByCondition(proRushDealinfo)<1){//修改交易单状态
                proRushDealinfoService.insertProRushDealinfo(proRushDealinfo);
            }

        }

        return toAjax(proRushRushsaleService.updateProRushRushsale(proRushRushsale));
    }




    /**
     * 删除出售交易信息
     */
    @PreAuthorize("@ss.hasPermi('rushsale:rushsale:remove')")
    @Log(title = "出售交易信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{rushsaleIds}")
    public AjaxResult remove(@PathVariable Long[] rushsaleIds)
    {
        return toAjax(proRushRushsaleService.deleteProRushRushsaleByRushsaleIds(rushsaleIds));
    }
}
