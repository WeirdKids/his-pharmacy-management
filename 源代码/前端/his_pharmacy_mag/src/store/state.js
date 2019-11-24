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
  drugs: {
    // id: window.sessionStorage.getItem('drugs' || '[]') == null ? '' : JSON.parse(window.sessionStorage.getItem('drugs' || '[]')).id,
    drugsCode: window.sessionStorage.getItem('drugs' || '[]') == null ? '' : JSON.parse(window.sessionStorage.getItem('drugs' || '[]')).drugsCode,
    drugsName: window.sessionStorage.getItem('drugs' || '[]') == null ? '' : JSON.parse(window.sessionStorage.getItem('drugs' || '[]')).drugsName,
    drugsFormat: window.sessionStorage.getItem('drugs' || '[]') == null ? '' : JSON.parse(window.sessionStorage.getItem('drugs' || '[]')).drugsFormat,
    drugsUnit: window.sessionStorage.getItem('drugs' || '[]') == null ? '' : JSON.parse(window.sessionStorage.getItem('drugs' || '[]')).drugsUnit,
    drugsDosageID: window.sessionStorage.getItem('drugs' || '[]') == null ? '' : JSON.parse(window.sessionStorage.getItem('drugs' || '[]')).drugsDosageID,
    drugsTypeID: window.sessionStorage.getItem('drugs' || '[]') == null ? '' : JSON.parse(window.sessionStorage.getItem('drugs' || '[]')).drugsTypeID,
    drugsPrice: window.sessionStorage.getItem('drugs' || '[]') == null ? '' : JSON.parse(window.sessionStorage.getItem('drugs' || '[]')).drugsPrice,
    mnemonicCode: window.sessionStorage.getItem('drugs' || '[]') == null ? '' : JSON.parse(window.sessionStorage.getItem('drugs' || '[]')).mnemonicCode,
    num: window.sessionStorage.getItem('drugs' || '[]') == null ? '' : JSON.parse(window.sessionStorage.getItem('drugs' || '[]')).num,
    saveRequire: window.sessionStorage.getItem('drugs' || '[]') == null ? '' : JSON.parse(window.sessionStorage.getItem('drugs' || '[]')).saveRequire
  }
}

export default state
