import request from '@/utils/request'

// 查询办公用品商品列表
export function listGoods(query) {
  return request({
    url: '/goods/goods/list',
    method: 'get',
    params: query
  })
}

// 查询办公用品商品详细
export function getGoods(goodsId) {
  return request({
    url: '/goods/goods/' + goodsId,
    method: 'get'
  })
}

// 新增办公用品商品
export function addGoods(data) {
  return request({
    url: '/goods/goods',
    method: 'post',
    data: data
  })
}

// 修改办公用品商品
export function updateGoods(data) {
  return request({
    url: '/goods/goods',
    method: 'put',
    data: data
  })
}

// 删除办公用品商品
export function delGoods(goodsId) {
  return request({
    url: '/goods/goods/' + goodsId,
    method: 'delete'
  })
}
