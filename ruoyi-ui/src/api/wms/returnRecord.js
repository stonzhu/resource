import request from '@/utils/request'

// 查询交还记录列表
export function listReturnRecord(query) {
  return request({
    url: '/return/returnRecord/list',
    method: 'get',
    params: query
  })
}

// 查询交还记录详细
export function getReturnRecord(id) {
  return request({
    url: '/return/returnRecord/' + id,
    method: 'get'
  })
}

// 新增交还记录
export function addReturnRecord(data) {
  return request({
    url: '/return/returnRecord',
    method: 'post',
    data: data
  })
}

// 修改交还记录
export function updateReturnRecord(data) {
  return request({
    url: '/return/returnRecord',
    method: 'put',
    data: data
  })
}

// 删除交还记录
export function delReturnRecord(id) {
  return request({
    url: '/return/returnRecord/' + id,
    method: 'delete'
  })
}
