// 作者：徐奥飞
// 时间：2019-11-4 16:40

import Vue from 'vue'
import Vuex from 'vuex'
import user from './modules/user'

Vue.use(Vuex)

const store = new Vuex.Store({
  modules: {
    // 使用 user.js 中的 action
    user
  }
})

export default store
