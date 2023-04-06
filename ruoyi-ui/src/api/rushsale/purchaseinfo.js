import request from '@/utils/request'

// 查询抢购人购买结算信息列表
export function listPurchaseinfo(query) {
  return request({
    url: '/rushsale/purchaseinfo/list',
    method: 'get',
    params: query
  })
}
//查询还未绑定收货人的抢购单
export function listPurchaseinfoByStatus(query) {
  return request({
    url: '/rushsale/purchaseinfo/listNotPreSell',
    method: 'get',
    params: query
  })
}


// 查询抢购人购买结算下拉列表信息,商品列表和购买人列表
export function listPurchase() {
  return request({
    url: '/rushsale/purchaseinfo/xiala',
    method: 'get',
  })
}


// 查询抢购人购买结算信息详细
export function getPurchaseinfo(purchaseId) {
  return request({
    url: '/rushsale/purchaseinfo/' + purchaseId,
    method: 'get'
  })
}

// 新增抢购人购买结算信息
export function addPurchaseinfo(data) {
  return request({
    url: '/rushsale/purchaseinfo',
    method: 'post',
    data: data
  })
}

// 修改抢购人购买结算信息
export function updatePurchaseinfo(data) {

  return request({
    url: '/rushsale/purchaseinfo',
    method: 'put',
    data: data
  })
}

export function handleCopy(purchaseId){
  return request({
    url: '/rushsale/purchaseinfo/handleCopy/'+purchaseId,
    method: 'get'
  })
}

// 删除抢购人购买结算信息
export function delPurchaseinfo(purchaseId) {
  return request({
    url: '/rushsale/purchaseinfo/' + purchaseId,
    method: 'delete'
  })
}
