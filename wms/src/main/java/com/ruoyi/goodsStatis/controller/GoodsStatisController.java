package com.ruoyi.goodsStatis.controller;

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
import com.ruoyi.goodsStatis.domain.GoodsStatis;
import com.ruoyi.goodsStatis.service.IGoodsStatisService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 商品库存动态Controller
 * 
 * @author ruoyi
 * @date 2024-02-19
 */
@RestController
@RequestMapping("/goodsStatis/goodsStatis")
public class GoodsStatisController extends BaseController
{
    @Autowired
    private IGoodsStatisService goodsStatisService;

    /**
     * 查询商品库存动态列表
     */
    @PreAuthorize("@ss.hasPermi('goodsStatis:goodsStatis:list')")
    @GetMapping("/list")
    public TableDataInfo list(GoodsStatis goodsStatis)
    {
        startPage();
        List<GoodsStatis> list = goodsStatisService.selectGoodsStatisList(goodsStatis);
        return getDataTable(list);
    }

    /**
     * 导出商品库存动态列表
     */
    @PreAuthorize("@ss.hasPermi('goodsStatis:goodsStatis:export')")
    @Log(title = "商品库存动态", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, GoodsStatis goodsStatis)
    {
        List<GoodsStatis> list = goodsStatisService.selectGoodsStatisList(goodsStatis);
        ExcelUtil<GoodsStatis> util = new ExcelUtil<GoodsStatis>(GoodsStatis.class);
        util.exportExcel(response, list, "商品库存动态数据");
    }

    /**
     * 获取商品库存动态详细信息
     */
    @PreAuthorize("@ss.hasPermi('goodsStatis:goodsStatis:query')")
    @GetMapping(value = "/{goodsStatisId}")
    public AjaxResult getInfo(@PathVariable("goodsStatisId") Long goodsStatisId)
    {
        return success(goodsStatisService.selectGoodsStatisByGoodsStatisId(goodsStatisId));
    }

    /**
     * 新增商品库存动态
     */
    @PreAuthorize("@ss.hasPermi('goodsStatis:goodsStatis:add')")
    @Log(title = "商品库存动态", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody GoodsStatis goodsStatis)
    {
        return toAjax(goodsStatisService.insertGoodsStatis(goodsStatis));
    }

    /**
     * 修改商品库存动态
     */
    @PreAuthorize("@ss.hasPermi('goodsStatis:goodsStatis:edit')")
    @Log(title = "商品库存动态", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody GoodsStatis goodsStatis)
    {
        return toAjax(goodsStatisService.updateGoodsStatis(goodsStatis));
    }

    /**
     * 删除商品库存动态
     */
    @PreAuthorize("@ss.hasPermi('goodsStatis:goodsStatis:remove')")
    @Log(title = "商品库存动态", businessType = BusinessType.DELETE)
	@DeleteMapping("/{goodsStatisIds}")
    public AjaxResult remove(@PathVariable Long[] goodsStatisIds)
    {
        return toAjax(goodsStatisService.deleteGoodsStatisByGoodsStatisIds(goodsStatisIds));
    }
}
