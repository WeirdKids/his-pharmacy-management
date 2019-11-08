// 作者：徐奥飞
// 时间：2019-11-4 16:00

// 引入封装好的 axios 请求
import request from '@/utils/request'

// 登录接口
export function login (username, password) {
  // 使用封装好的 axios 进行网络请求
  return request({
    url: '/admin/login',
    method: 'post',
    data: { // 提交的数据
      username,
      password
    }
  })
}
