import request from '@/utils/request'

// 查询商品消耗统计列表
export function listConsumestatis(query) {
  return request({
    url: '/wms/consumestatis/list',
    method: 'get',
    params: query
  })
}

// 查询商品消耗统计详细
export function getConsumestatis(consumptionStatisId) {
  return request({
    url: '/wms/consumestatis/' + consumptionStatisId,
    method: 'get'
  })
}

// 新增商品消耗统计
export function addConsumestatis(data) {
  return request({
    url: '/wms/consumestatis',
    method: 'post',
    data: data
  })
}

// 修改商品消耗统计
export function updateConsumestatis(data) {
  return request({
    url: '/wms/consumestatis',
    method: 'put',
    data: data
  })
}

// 删除商品消耗统计
export function delConsumestatis(consumptionStatisId) {
  return request({
    url: '/wms/consumestatis/' + consumptionStatisId,
    method: 'delete'
  })
}
