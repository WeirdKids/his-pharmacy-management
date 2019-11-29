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
  repertory: {
    // id: window.sessionStorage.getItem('drugs' || '[]') == null ? '' : JSON.parse(window.sessionStorage.getItem('drugs' || '[]')).id,
    drugsCode: window.sessionStorage.getItem('repertory' || '[]') == null ? '' : JSON.parse(window.sessionStorage.getItem('repertory' || '[]')).drugsCode,
    drugsName: window.sessionStorage.getItem('repertory' || '[]') == null ? '' : JSON.parse(window.sessionStorage.getItem('repertory' || '[]')).drugsName,
    drugsFormat: window.sessionStorage.getItem('repertory' || '[]') == null ? '' : JSON.parse(window.sessionStorage.getItem('repertory' || '[]')).drugsFormat,
    drugsUnit: window.sessionStorage.getItem('repertory' || '[]') == null ? '' : JSON.parse(window.sessionStorage.getItem('repertory' || '[]')).drugsUnit,
    drugsDosageID: window.sessionStorage.getItem('repertory' || '[]') == null ? '' : JSON.parse(window.sessionStorage.getItem('repertory' || '[]')).drugsDosageID,
    drugsTypeID: window.sessionStorage.getItem('repertory' || '[]') == null ? '' : JSON.parse(window.sessionStorage.getItem('repertory' || '[]')).drugsTypeID,
    drugsPrice: window.sessionStorage.getItem('repertory' || '[]') == null ? '' : JSON.parse(window.sessionStorage.getItem('repertory' || '[]')).drugsPrice,
    mnemonicCode: window.sessionStorage.getItem('repertory' || '[]') == null ? '' : JSON.parse(window.sessionStorage.getItem('repertory' || '[]')).mnemonicCode,
    totalNum: window.sessionStorage.getItem('repertory' || '[]') == null ? '' : JSON.parse(window.sessionStorage.getItem('repertory' || '[]')).totalNum,
    saveRequire: window.sessionStorage.getItem('repertory' || '[]') == null ? '' : JSON.parse(window.sessionStorage.getItem('repertory' || '[]')).saveRequire,
    warehouses: [
      {
        num: window.sessionStorage.getItem('repertory' || '[]') == null ? '' : JSON.parse(window.sessionStorage.getItem('repertory' || '[]')).num,
        warehouse: window.sessionStorage.getItem('repertory' || '[]') == null ? '' : JSON.parse(window.sessionStorage.getItem('repertory' || '[]')).warehouse
      }
    ],
    currentPage: window.sessionStorage.getItem('repertory' || '[]') == null ? '1' : JSON.parse(window.sessionStorage.getItem('repertory' || '[]')).currentPage,
    pageSize: window.sessionStorage.getItem('repertory' || '[]') == null ? '5' : JSON.parse(window.sessionStorage.getItem('repertory' || '[]')).pageSize
  },
  drug: {
    drugsCode: window.sessionStorage.getItem('drug' || '[]') == null ? '' : JSON.parse(window.sessionStorage.getItem('drug' || '[]')).drugsCode,
    drugsName: window.sessionStorage.getItem('drug' || '[]') == null ? '' : JSON.parse(window.sessionStorage.getItem('drug' || '[]')).drugsName,
    drugsFormat: window.sessionStorage.getItem('drug' || '[]') == null ? '' : JSON.parse(window.sessionStorage.getItem('drug' || '[]')).drugsFormat,
    drugsUnit: window.sessionStorage.getItem('drug' || '[]') == null ? '' : JSON.parse(window.sessionStorage.getItem('drug' || '[]')).drugsUnit,
    drugsDosageID: window.sessionStorage.getItem('drug' || '[]') == null ? '' : JSON.parse(window.sessionStorage.getItem('drug' || '[]')).drugsDosageID,
    drugsTypeID: window.sessionStorage.getItem('drug' || '[]') == null ? '' : JSON.parse(window.sessionStorage.getItem('drug' || '[]')).drugsTypeID,
    drugsPrice: window.sessionStorage.getItem('drug' || '[]') == null ? '' : JSON.parse(window.sessionStorage.getItem('drug' || '[]')).drugsPrice,
    mnemonicCode: window.sessionStorage.getItem('drug' || '[]') == null ? '' : JSON.parse(window.sessionStorage.getItem('drug' || '[]')).mnemonicCode,
    totalNum: window.sessionStorage.getItem('drug' || '[]') == null ? '' : JSON.parse(window.sessionStorage.getItem('drug' || '[]')).totalNum,
    saveRequire: window.sessionStorage.getItem('drug' || '[]') == null ? '' : JSON.parse(window.sessionStorage.getItem('drug' || '[]')).saveRequire,
    currentPage: window.sessionStorage.getItem('drug' || '[]') == null ? '1' : JSON.parse(window.sessionStorage.getItem('drug' || '[]')).currentPage,
    pageSize: window.sessionStorage.getItem('drug' || '[]') == null ? '5' : JSON.parse(window.sessionStorage.getItem('drug' || '[]')).pageSize
  },
  prescription: {
    prescriptionCode: window.sessionStorage.getItem('prescription' || '[]') == null ? '' : JSON.parse(window.sessionStorage.getItem('prescription' || '[]')).prescriptionCode,
    doctorID: window.sessionStorage.getItem('prescription' || '[]') == null ? '' : JSON.parse(window.sessionStorage.getItem('prescription' || '[]')).doctorID,
    chargeTime: window.sessionStorage.getItem('prescription' || '[]') == null ? '' : JSON.parse(window.sessionStorage.getItem('prescription' || '[]')).chargeTime,
    charger: window.sessionStorage.getItem('prescription' || '[]') == null ? '' : JSON.parse(window.sessionStorage.getItem('prescription' || '[]')).charger,
    statue: window.sessionStorage.getItem('prescription' || '[]') == null ? '' : JSON.parse(window.sessionStorage.getItem('prescription' || '[]')).statue,
    totalStage: window.sessionStorage.getItem('prescription' || '[]') == null ? '' : JSON.parse(window.sessionStorage.getItem('prescription' || '[]')).totalStage,
    currentStage: window.sessionStorage.getItem('prescription' || '[]') == null ? '' : JSON.parse(window.sessionStorage.getItem('prescription' || '[]')).currentStage,
    num: window.sessionStorage.getItem('prescription' || '[]') == null ? '' : JSON.parse(window.sessionStorage.getItem('prescription' || '[]')).num,
    sentNum: window.sessionStorage.getItem('prescription' || '[]') == null ? '' : JSON.parse(window.sessionStorage.getItem('prescription' || '[]')).sentNum,
    drugName: window.sessionStorage.getItem('prescription' || '[]') == null ? '' : JSON.parse(window.sessionStorage.getItem('prescription' || '[]')).drugName
  }
}
export default state
