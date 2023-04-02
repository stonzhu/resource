package com.ruoyi.rushsale.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.common.annotation.DataScope;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.rushsale.common.constant.Constants;
import com.ruoyi.rushsale.domain.ProRushDealinfo;
import com.ruoyi.rushsale.domain.ProRushGoods;
import com.ruoyi.rushsale.domain.ProRushRushsale;
import com.ruoyi.rushsale.service.IProRushDealinfoService;
import com.ruoyi.rushsale.service.IProRushGoodsService;
import com.ruoyi.rushsale.service.IProRushRushsaleService;
import com.ruoyi.system.service.ISysUserService;
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
import com.ruoyi.rushsale.domain.ProRushPurchaseinfo;
import com.ruoyi.rushsale.service.IProRushPurchaseinfoService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 抢购人购买结算信息Controller
 * 
 * @author ruoyi
 * @date 2023-03-24
 */
@RestController
@RequestMapping("/rushsale/purchaseinfo")
public class ProRushPurchaseinfoController extends BaseController
{
    @Autowired
    private IProRushPurchaseinfoService proRushPurchaseinfoService;
    @Autowired
    private IProRushGoodsService proRushGoodsService;

    @Autowired
    private ISysUserService userService;
    @Autowired
    private IProRushRushsaleService proRushRushsaleService;

    @Autowired
    private IProRushDealinfoService proRushDealinfoService;
    /**
     * 查询抢购人购买结算信息列表
     */
    @PreAuthorize("@ss.hasPermi('rushsale:purchaseinfo:list')")
    @GetMapping("/list")
    public TableDataInfo list(ProRushPurchaseinfo proRushPurchaseinfo)
    {
        startPage();
        List<ProRushPurchaseinfo> list = proRushPurchaseinfoService.selectProRushPurchaseinfoList(proRushPurchaseinfo);
        return getDataTable(list);
    }

    /**
     * 导出抢购人购买结算信息列表
     */
    @PreAuthorize("@ss.hasPermi('rushsale:purchaseinfo:export')")
    @Log(title = "抢购人购买结算信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ProRushPurchaseinfo proRushPurchaseinfo)
    {
        List<ProRushPurchaseinfo> list = proRushPurchaseinfoService.selectProRushPurchaseinfoList(proRushPurchaseinfo);
        ExcelUtil<ProRushPurchaseinfo> util = new ExcelUtil<ProRushPurchaseinfo>(ProRushPurchaseinfo.class);
        util.exportExcel(response, list, "抢购人购买结算信息数据");
    }

    /**
     * 获取抢购人购买结算信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('rushsale:purchaseinfo:query')")
    @GetMapping(value = "/{purchaseId}")
    public AjaxResult getInfo(@PathVariable("purchaseId") Long purchaseId)
    {
        return success(proRushPurchaseinfoService.selectProRushPurchaseinfoByPurchaseId(purchaseId));
    }

    /**
     * 新增抢购人购买结算信息
     */
    @PreAuthorize("@ss.hasPermi('rushsale:purchaseinfo:add')")
    @Log(title = "抢购人购买结算信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ProRushPurchaseinfo proRushPurchaseinfo)
    {
        return toAjax(proRushPurchaseinfoService.insertProRushPurchaseinfo(proRushPurchaseinfo));
    }

    /**
     * 修改抢购人购买结算信息
     */
    @PreAuthorize("@ss.hasPermi('rushsale:purchaseinfo:edit')")
    @Log(title = "抢购人购买结算信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ProRushPurchaseinfo proRushPurchaseinfo)
    {
        String type = proRushPurchaseinfo.getType();
        if(Constants.PURCHASEINFO_UPDATE_ORDERNUM.equals(type)){//修改收购商
            proRushPurchaseinfo.setPurchName(null);//防止修改错用户名
            ProRushRushsale ProRushRushsale = new ProRushRushsale();
            ProRushRushsale.setPurchaseId(proRushPurchaseinfo.getPurchaseId());
            ProRushRushsale.setPayFrom(proRushPurchaseinfo.getOrderNum());
            if(proRushRushsaleService.updateProRushRushsaleByConditon(ProRushRushsale)<1){
                //创建出货信息单
                proRushRushsaleService.insertProRushRushsale(ProRushRushsale);
            }
            return toAjax(proRushPurchaseinfoService.updateProRushPurchaseinfo(proRushPurchaseinfo));
        }
        //修改结算状态，生成交易信息
        if(Constants.UPDATE_DEALSTATE.equals(type)){
            ProRushDealinfo proRushDealinfo = new ProRushDealinfo();
            proRushDealinfo.setGoodsId(proRushPurchaseinfo.getGoodsId());
            proRushDealinfo.setDealFrom(proRushPurchaseinfo.getPurchName());
            proRushDealinfo.setDealTo(getUserId().toString());
            proRushDealinfo.setDealNum(proRushPurchaseinfo.getDealPrice());
            proRushDealinfo.setDealType(Constants.DEAL_TYPE_BUY);
            if(Constants.DEAL_STATUS_COMPLETE.equals(proRushPurchaseinfo.getDealState())){
                proRushDealinfo.setDealStatus(Constants.DEAL_STATUS_VALID);
            }else{
                proRushDealinfo.setDealStatus(Constants.DEAL_STATUS_INVALID);
            }
            proRushDealinfo.setDealTime(DateUtils.getNowDate());
            proRushDealinfo.setHandleOrderId(proRushPurchaseinfo.getPurchaseId());
            if(proRushDealinfoService.updateProRushDealinfoByCondition(proRushDealinfo)<1){//修改交易单状态
                proRushDealinfoService.insertProRushDealinfo(proRushDealinfo);
            }
        }
        return toAjax(proRushPurchaseinfoService.updateProRushPurchaseinfo(proRushPurchaseinfo));

    }

    /**
     * 删除抢购人购买结算信息
     */
    @PreAuthorize("@ss.hasPermi('rushsale:purchaseinfo:remove')")
    @Log(title = "抢购人购买结算信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{purchaseIds}")
    public AjaxResult remove(@PathVariable Long[] purchaseIds)
    {
        return toAjax(proRushPurchaseinfoService.deleteProRushPurchaseinfoByPurchaseIds(purchaseIds));
    }

    @PreAuthorize("@ss.hasPermi('rushsale:purchaseinfo:list')")
    @GetMapping(value = "/xiala")
    public AjaxResult xiala()
    {
        AjaxResult ajax = AjaxResult.success();
        ProRushGoods proRushGoods = new ProRushGoods();
        //TODO 可以存在此处添加部门id，用于过滤部门数据
        List<ProRushGoods> list = proRushGoodsService.selectProRushGoodsList(proRushGoods);
        SysUser sysUser = new SysUser();
        //sysUser.setDeptId(getDeptId());
        //TODO 后续做区分
        List<SysUser> userLisr = userService.selectUserList(sysUser);
        List<SysUser> buyNameList = userService.selectUserList(sysUser);//收货人列表
        ajax.put("goodsList",list);
        ajax.put("purchNameList",userLisr);
        ajax.put("buyNameList",buyNameList);
        return ajax;
    }

    @DataScope()
    @PreAuthorize("@ss.hasPermi('rushsale:purchaseinfo:list')")
    @GetMapping(value = "/listNotPreSell")
    public AjaxResult listNotPreSell()
    {
        AjaxResult ajax = AjaxResult.success();
        ProRushPurchaseinfo proRushPurchaseinfo = new ProRushPurchaseinfo();
        List<ProRushPurchaseinfo> list = proRushPurchaseinfoService.selectProRushPurchaseinfoList(proRushPurchaseinfo);
        SysUser sysUser = new SysUser();
        List<SysUser> purchNameList = userService.selectUserList(sysUser);//购买人列表
        List<SysUser> buyNameList = userService.selectUserList(sysUser);//收货人列表
        ajax.put("goodsList",list);
        ajax.put("purchNameList",purchNameList);
        ajax.put("buyNameList",buyNameList);
        return ajax;
    }
    @PreAuthorize("@ss.hasPermi('rushsale:purchaseinfo:cheoceSellOut')")
    @GetMapping(value = "/cheoceSellOut")
    public AjaxResult cheoceSellOut()
    {
        AjaxResult ajax = AjaxResult.success();
        ProRushPurchaseinfo proRushPurchaseinfo = new ProRushPurchaseinfo();
        List<ProRushPurchaseinfo> list = proRushPurchaseinfoService.selectProRushPurchaseinfoList(proRushPurchaseinfo);
        SysUser sysUser = new SysUser();
        List<SysUser> userLisr = userService.selectUserList(sysUser);
        ajax.put("goodsList",list);
        ajax.put("purchNameList",userLisr);
        return ajax;
    }

    

}
