import request from '@/utils/request'

// 查询课程列表
export function listSubject(query) {
  return request({
    url: '/item/subject/list',
    method: 'get',
    params: query
  })
}

// 查询课程详细
export function getSubject(id) {
  return request({
    url: '/item/subject/' + id,
    method: 'get'
  })
}

// 新增课程
export function addSubject(data) {
  return request({
    url: '/item/subject',
    method: 'post',
    data: data
  })
}

// 修改课程
export function updateSubject(data) {
  return request({
    url: '/item/subject',
    method: 'put',
    data: data
  })
}

// 删除课程
export function delSubject(id) {
  return request({
    url: '/item/subject/' + id,
    method: 'delete'
  })
}

// 导出课程
export function exportSubject(query) {
  return request({
    url: '/item/subject/export',
    method: 'get',
    params: query
  })
}