import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    user: {
      // 这里用到了localStorage，即本地存储
      // 在项目打开的时候会判断本地存储中是否有 user 这个对象存在
      // 如果存在就取出来并获得 username 的值，否则则把 username 设置为空
      // 这样我们只要不清除缓存，登录的状态就会一直保存
      username: window.localStorage.getItem('user' || '[]') == null ? '' : JSON.parse(window.localStorage.getItem('user' || '[]')).username,
      realName: window.localStorage.getItem('user' || '[]') == null ? '' : JSON.parse(window.localStorage.getItem('user' || '[]')).realName
      // state: window.localStorage.getItem('user' || '[]') == null ? '' : JSON.parse(window.localStorage.getItem('user' || '[]')).state
    },
    drugs: {
      drugsCode: window.localStorage.getItem('drugs' || '[]') == null ? '' : JSON.parse(window.localStorage.getItem('drugs' || '[]')).drugsCode,
      drugsName: window.localStorage.getItem('drugs' || '[]') == null ? '' : JSON.parse(window.localStorage.getItem('drugs' || '[]')).drugsName,
      drugsFormat: window.localStorage.getItem('drugs' || '[]') == null ? '' : JSON.parse(window.localStorage.getItem('drugs' || '[]')).drugsFormat,
      drugsUnit: window.localStorage.getItem('drugs' || '[]') == null ? '' : JSON.parse(window.localStorage.getItem('drugs' || '[]')).drugsUnit,
      drugsDosageID: window.localStorage.getItem('drugs' || '[]') == null ? '' : JSON.parse(window.localStorage.getItem('drugs' || '[]')).drugsDosageID,
      drugsTypeID: window.localStorage.getItem('drugs' || '[]') == null ? '' : JSON.parse(window.localStorage.getItem('drugs' || '[]')).drugsTypeID,
      drugsPrice: window.localStorage.getItem('drugs' || '[]') == null ? '' : JSON.parse(window.localStorage.getItem('drugs' || '[]')).drugsPrice,
      mnemonicCode: window.localStorage.getItem('drugs' || '[]') == null ? '' : JSON.parse(window.localStorage.getItem('drugs' || '[]')).mnemonicCode,
      num: window.localStorage.getItem('drugs' || '[]') == null ? '' : JSON.parse(window.localStorage.getItem('drugs' || '[]')).num,
      saveRequire: window.localStorage.getItem('drugs' || '[]') == null ? '' : JSON.parse(window.localStorage.getItem('drugs' || '[]')).saveRequire
    }
  },
  mutations: {
    login (state, user) {
      state.user = user
      window.localStorage.setItem('user', JSON.stringify(user))
    },
    logout (state) {
      window.localStorage.removeItem('user')
    },
    queryAll (state, drugs) {
      state.drugs = drugs
      window.localStorage.setItem('drugs', JSON.stringify(drugs))
    }
  }
})
