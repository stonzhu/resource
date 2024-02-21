import request from '@/utils/request'

// 查询领用记录列表
export function listReceiveRecord(query) {
  return request({
    url: '/receive/receiveRecord/list',
    method: 'get',
    params: query
  })
}

// 查询领用记录详细
export function getReceiveRecord(id) {
  return request({
    url: '/receive/receiveRecord/' + id,
    method: 'get'
  })
}

// 新增领用记录
export function addReceiveRecord(data) {
  return request({
    url: '/receive/receiveRecord',
    method: 'post',
    data: data
  })
}

// 修改领用记录
export function updateReceiveRecord(data) {
  return request({
    url: '/receive/receiveRecord',
    method: 'put',
    data: data
  })
}

// 删除领用记录
export function delReceiveRecord(id) {
  return request({
    url: '/receive/receiveRecord/' + id,
    method: 'delete'
  })
}
