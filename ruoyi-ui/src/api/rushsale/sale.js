import request from '@/utils/request'

// 查询抢购买卖列表
export function listSale(query) {
  return request({
    url: '/system/sale/list',
    method: 'get',
    params: query
  })
}

// 查询抢购买卖详细
export function getSale(proRushSaleId) {
  return request({
    url: '/system/sale/' + proRushSaleId,
    method: 'get'
  })
}

// 新增抢购买卖
export function addSale(data) {
  return request({
    url: '/system/sale',
    method: 'post',
    data: data
  })
}

// 修改抢购买卖
export function updateSale(data) {
  return request({
    url: '/system/sale',
    method: 'put',
    data: data
  })
}

// 删除抢购买卖
export function delSale(proRushSaleId) {
  return request({
    url: '/system/sale/' + proRushSaleId,
    method: 'delete'
  })
}
