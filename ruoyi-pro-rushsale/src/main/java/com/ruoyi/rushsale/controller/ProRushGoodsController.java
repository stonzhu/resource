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
import com.ruoyi.rushsale.domain.ProRushGoods;
import com.ruoyi.rushsale.service.IProRushGoodsService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 商品信息Controller
 * 
 * @author ruoyi
 * @date 2023-03-24
 */
@RestController
@RequestMapping("/rushsale/goods")
public class ProRushGoodsController extends BaseController
{
    @Autowired
    private IProRushGoodsService proRushGoodsService;

    /**
     * 查询商品信息列表
     */
    @PreAuthorize("@ss.hasPermi('rushsale:goods:list')")
    @GetMapping("/list")
    public TableDataInfo list(ProRushGoods proRushGoods)
    {
        startPage();
        List<ProRushGoods> list = proRushGoodsService.selectProRushGoodsList(proRushGoods);
        return getDataTable(list);
    }

    /**
     * 导出商品信息列表
     */
    @PreAuthorize("@ss.hasPermi('rushsale:goods:export')")
    @Log(title = "商品信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ProRushGoods proRushGoods)
    {
        List<ProRushGoods> list = proRushGoodsService.selectProRushGoodsList(proRushGoods);
        ExcelUtil<ProRushGoods> util = new ExcelUtil<ProRushGoods>(ProRushGoods.class);
        util.exportExcel(response, list, "商品信息数据");
    }

    /**
     * 获取商品信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('rushsale:goods:query')")
    @GetMapping(value = "/{goodsId}")
    public AjaxResult getInfo(@PathVariable("goodsId") Long goodsId)
    {
        return success(proRushGoodsService.selectProRushGoodsByGoodsId(goodsId));
    }

    /**
     * 新增商品信息
     */
    @PreAuthorize("@ss.hasPermi('rushsale:goods:add')")
    @Log(title = "商品信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ProRushGoods proRushGoods)
    {
        return toAjax(proRushGoodsService.insertProRushGoods(proRushGoods));
    }

    /**
     * 修改商品信息
     */
    @PreAuthorize("@ss.hasPermi('rushsale:goods:edit')")
    @Log(title = "商品信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ProRushGoods proRushGoods)
    {
        return toAjax(proRushGoodsService.updateProRushGoods(proRushGoods));
    }

    /**
     * 删除商品信息
     */
    @PreAuthorize("@ss.hasPermi('rushsale:goods:remove')")
    @Log(title = "商品信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{goodsIds}")
    public AjaxResult remove(@PathVariable Long[] goodsIds)
    {
        return toAjax(proRushGoodsService.deleteProRushGoodsByGoodsIds(goodsIds));
    }
}
