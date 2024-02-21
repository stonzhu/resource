import request from '@/utils/request'

// 查询商品库存动态列表
export function listGoodsStatis(query) {
  return request({
    url: '/goodsStatis/goodsStatis/list',
    method: 'get',
    params: query
  })
}

// 查询商品库存动态详细
export function getGoodsStatis(goodsStatisId) {
  return request({
    url: '/goodsStatis/goodsStatis/' + goodsStatisId,
    method: 'get'
  })
}

// 新增商品库存动态
export function addGoodsStatis(data) {
  return request({
    url: '/goodsStatis/goodsStatis',
    method: 'post',
    data: data
  })
}

// 修改商品库存动态
export function updateGoodsStatis(data) {
  return request({
    url: '/goodsStatis/goodsStatis',
    method: 'put',
    data: data
  })
}

// 删除商品库存动态
export function delGoodsStatis(goodsStatisId) {
  return request({
    url: '/goodsStatis/goodsStatis/' + goodsStatisId,
    method: 'delete'
  })
}
