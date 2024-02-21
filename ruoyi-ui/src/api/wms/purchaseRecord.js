import request from '@/utils/request'

// 查询采购记录列表
export function listPurchaseRecord(query) {
  return request({
    url: '/purchase/purchaseRecord/list',
    method: 'get',
    params: query
  })
}

// 查询采购记录详细
export function getPurchaseRecord(purchaseRecordId) {
  return request({
    url: '/purchase/purchaseRecord/' + purchaseRecordId,
    method: 'get'
  })
}

// 新增采购记录
export function addPurchaseRecord(data) {
  return request({
    url: '/purchase/purchaseRecord',
    method: 'post',
    data: data
  })
}

// 导入采购记录
export function importPurchaseRecord(data) {
  return request({
    url: '/purchase/importData',
    method: 'post',
    data: data
  })
}
// 修改采购记录
export function updatePurchaseRecord(data) {
  return request({
    url: '/purchase/purchaseRecord',
    method: 'put',
    data: data
  })
}

// 删除采购记录
export function delPurchaseRecord(purchaseRecordId) {
  return request({
    url: '/purchase/purchaseRecord/' + purchaseRecordId,
    method: 'delete'
  })
}
