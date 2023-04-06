import request from '@/utils/request'

// 查询抢购资金账户列表
export function listAccount(query) {
  return request({
    url: '/rushsale/account/list',
    method: 'get',
    params: query
  })
}

export function listAccountXiala(query) {
  return request({
    url: '/rushsale/account/xiala',
    method: 'get',
    params: query
  })
}
// 查询抢购资金账户详细
export function getAccount(accountId) {
  return request({
    url: '/rushsale/account/' + accountId,
    method: 'get'
  })
}

// 新增抢购资金账户
export function addAccount(data) {
  return request({
    url: '/rushsale/account',
    method: 'post',
    data: data
  })
}

// 修改抢购资金账户
export function updateAccount(data) {
  return request({
    url: '/rushsale/account',
    method: 'put',
    data: data
  })
}



// 删除抢购资金账户
export function delAccount(accountId) {
  return request({
    url: '/rushsale/account/' + accountId,
    method: 'delete'
  })
}
