// 作者：徐奥飞
// 时间：2019-11-12
// 描述：相当于数据库，定义了数据的结构和初始值
const state = {
  user: {
    // 这里用到了 sessionStorage，即会话存储
    // 在项目打开的时候会判断会话存储中是否有 user 这个对象存在
    // 如果存在就取出来并获得 username 的值，否则则把 username 设置为空
    // 这样我们只要不清除缓存，登录的状态就会一直保存
    username: window.sessionStorage.getItem('user' || '[]') == null ? '' : JSON.parse(window.sessionStorage.getItem('user' || '[]')).username,
    realName: window.sessionStorage.getItem('user' || '[]') == null ? '' : JSON.parse(window.sessionStorage.getItem('user' || '[]')).realName
  },
  repertories: {
    // id: window.sessionStorage.getItem('drugs' || '[]') == null ? '' : JSON.parse(window.sessionStorage.getItem('drugs' || '[]')).id,
    drugsCode: window.sessionStorage.getItem('repertories' || '[]') == null ? '' : JSON.parse(window.sessionStorage.getItem('repertories' || '[]')).drugsCode,
    drugsName: window.sessionStorage.getItem('repertories' || '[]') == null ? '' : JSON.parse(window.sessionStorage.getItem('repertories' || '[]')).drugsName,
    drugsFormat: window.sessionStorage.getItem('repertories' || '[]') == null ? '' : JSON.parse(window.sessionStorage.getItem('repertories' || '[]')).drugsFormat,
    drugsUnit: window.sessionStorage.getItem('repertories' || '[]') == null ? '' : JSON.parse(window.sessionStorage.getItem('repertories' || '[]')).drugsUnit,
    drugsDosageID: window.sessionStorage.getItem('repertories' || '[]') == null ? '' : JSON.parse(window.sessionStorage.getItem('repertories' || '[]')).drugsDosageID,
    drugsTypeID: window.sessionStorage.getItem('repertories' || '[]') == null ? '' : JSON.parse(window.sessionStorage.getItem('repertories' || '[]')).drugsTypeID,
    drugsPrice: window.sessionStorage.getItem('repertories' || '[]') == null ? '' : JSON.parse(window.sessionStorage.getItem('repertories' || '[]')).drugsPrice,
    mnemonicCode: window.sessionStorage.getItem('repertories' || '[]') == null ? '' : JSON.parse(window.sessionStorage.getItem('repertories' || '[]')).mnemonicCode,
    totalNum: window.sessionStorage.getItem('repertories' || '[]') == null ? '' : JSON.parse(window.sessionStorage.getItem('repertories' || '[]')).totalNum,
    saveRequire: window.sessionStorage.getItem('repertories' || '[]') == null ? '' : JSON.parse(window.sessionStorage.getItem('repertories' || '[]')).saveRequire,
    num: window.sessionStorage.getItem('repertories' || '[]') == null ? '' : JSON.parse(window.sessionStorage.getItem('repertories' || '[]')).num,
    warehouse: window.sessionStorage.getItem('repertories' || '[]') == null ? '' : JSON.parse(window.sessionStorage.getItem('repertories' || '[]')).warehouse,
    currentPage: window.sessionStorage.getItem('repertories' || '[]') == null ? '1' : JSON.parse(window.sessionStorage.getItem('repertories' || '[]')).currentPage,
    pageSize: window.sessionStorage.getItem('repertories' || '[]') == null ? '5' : JSON.parse(window.sessionStorage.getItem('repertories' || '[]')).pageSize
  }
}

export default state
