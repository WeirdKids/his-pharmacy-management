// 作者：徐奥飞
// 时间：2019-11-4 17：20

let baseUrl = ''
switch (process.env.NODE_ENV) {
  case 'dev':
    baseUrl = 'http://localhost:8088/' // 开发环境url
    break
  case 'serve':
    baseUrl = 'http://localhost:8089/' // 生产环境url
    break
}

export default baseUrl
