import request from '@/utils/request'

// 查询出售交易信息列表
export function listRushsale(query) {
  return request({
    url: '/rushsale/rushsale/list',
    method: 'get',
    params: query
  })
}

// 查询出售交易信息详细
export function getRushsale(rushsaleId) {
  return request({
    url: '/rushsale/rushsale/' + rushsaleId,
    method: 'get'
  })
}

// 新增出售交易信息
export function addRushsale(data) {
  return request({
    url: '/rushsale/rushsale',
    method: 'post',
    data: data
  })
}

// 修改出售交易信息
export function updateRushsale(data) {
  return request({
    url: '/rushsale/rushsale',
    method: 'put',
    data: data
  })
}

// 删除出售交易信息
export function delRushsale(rushsaleId) {
  return request({
    url: '/rushsale/rushsale/' + rushsaleId,
    method: 'delete'
  })
}
