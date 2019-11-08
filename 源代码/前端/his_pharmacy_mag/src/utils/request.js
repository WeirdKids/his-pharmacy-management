// 作者：徐奥飞
// 时间：2019-11-4 15:30
// 描述：封装一个请求工具类

// 引入 axios
import axios from 'axios'
// 使用环境变量 + 模式的方式定义基础URL
import baseUrl from '../api/baseUrl'

// 创建 axios 实例
const service = axios.create({
  baseURL: baseUrl, // api 的 base_url
  timeout: 15000 // 请求超时时间
})

export default service
