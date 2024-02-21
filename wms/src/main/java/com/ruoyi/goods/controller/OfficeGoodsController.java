package com.ruoyi.goods.controller;

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
import com.ruoyi.goods.domain.OfficeGoods;
import com.ruoyi.goods.service.IOfficeGoodsService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 办公用品商品Controller
 * 
 * @author ruoyi
 * @date 2024-02-19
 */
@RestController
@RequestMapping("/goods/goods")
public class OfficeGoodsController extends BaseController
{
    @Autowired
    private IOfficeGoodsService officeGoodsService;

    /**
     * 查询办公用品商品列表
     */
    @PreAuthorize("@ss.hasPermi('goods:goods:list')")
    @GetMapping("/list")
    public TableDataInfo list(OfficeGoods officeGoods)
    {
        startPage();
        List<OfficeGoods> list = officeGoodsService.selectOfficeGoodsList(officeGoods);
        return getDataTable(list);
    }

    /**
     * 导出办公用品商品列表
     */
    @PreAuthorize("@ss.hasPermi('goods:goods:export')")
    @Log(title = "办公用品商品", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, OfficeGoods officeGoods)
    {
        List<OfficeGoods> list = officeGoodsService.selectOfficeGoodsList(officeGoods);
        ExcelUtil<OfficeGoods> util = new ExcelUtil<OfficeGoods>(OfficeGoods.class);
        util.exportExcel(response, list, "办公用品商品数据");
    }

    /**
     * 获取办公用品商品详细信息
     */
    @PreAuthorize("@ss.hasPermi('goods:goods:query')")
    @GetMapping(value = "/{goodsId}")
    public AjaxResult getInfo(@PathVariable("goodsId") Long goodsId)
    {
        return success(officeGoodsService.selectOfficeGoodsByGoodsId(goodsId));
    }

    /**
     * 新增办公用品商品
     */
    @PreAuthorize("@ss.hasPermi('goods:goods:add')")
    @Log(title = "办公用品商品", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody OfficeGoods officeGoods)
    {
        return toAjax(officeGoodsService.insertOfficeGoods(officeGoods));
    }

    /**
     * 修改办公用品商品
     */
    @PreAuthorize("@ss.hasPermi('goods:goods:edit')")
    @Log(title = "办公用品商品", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody OfficeGoods officeGoods)
    {
        return toAjax(officeGoodsService.updateOfficeGoods(officeGoods));
    }

    /**
     * 删除办公用品商品
     */
    @PreAuthorize("@ss.hasPermi('goods:goods:remove')")
    @Log(title = "办公用品商品", businessType = BusinessType.DELETE)
	@DeleteMapping("/{goodsIds}")
    public AjaxResult remove(@PathVariable Long[] goodsIds)
    {
        return toAjax(officeGoodsService.deleteOfficeGoodsByGoodsIds(goodsIds));
    }
}
