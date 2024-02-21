import request from '@/utils/request'

// 查询抢购交易信息列表
export function listDealinfo(query) {
  return request({
    url: '/rushsale/dealinfo/list',
    method: 'get',
    params: query
  })
}



// 查询抢购交易信息详细
export function getDealinfo(dealId) {
  return request({
    url: '/rushsale/dealinfo/' + dealId,
    method: 'get'
  })
}

//撤销订单
export function handleReback(){
  return request({
    url: '/rushsale/dealinfo/handleReback/' + dealId,
    method: 'get'
  })
}

// 新增抢购交易信息
export function addDealinfo(data) {
  return request({
    url: '/rushsale/dealinfo',
    method: 'post',
    data: data
  })
}

// 修改抢购交易信息
export function updateDealinfo(data) {
  return request({
    url: '/rushsale/dealinfo',
    method: 'put',
    data: data
  })
}



// 删除抢购交易信息
export function delDealinfo(dealId) {
  return request({
    url: '/rushsale/dealinfo/' + dealId,
    method: 'delete'
  })
}
