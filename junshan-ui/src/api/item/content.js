import request from '@/utils/request'

// 查询文本列表
export function listContent(query) {
  return request({
    url: '/item/content/list',
    method: 'get',
    params: query
  })
}

// 查询文本详细
export function getContent(id) {
  return request({
    url: '/item/content/' + id,
    method: 'get'
  })
}

// 新增文本
export function addContent(data) {
  return request({
    url: '/item/content',
    method: 'post',
    data: data
  })
}

// 修改文本
export function updateContent(data) {
  return request({
    url: '/item/content',
    method: 'put',
    data: data
  })
}

// 删除文本
export function delContent(id) {
  return request({
    url: '/item/content/' + id,
    method: 'delete'
  })
}

// 导出文本
export function exportContent(query) {
  return request({
    url: '/item/content/export',
    method: 'get',
    params: query
  })
}