// 作者：徐奥飞
// 时间：2019-11-12
// 描述：定义 Vuex.Store

import Vue from 'vue'
import Vuex from 'vuex'
import state from './state'
import actions from './actions'
import mutations from './mutations'
import getters from './getters'

Vue.use(Vuex)

export default new Vuex.Store({
  state: state,
  actions: actions,
  mutations: mutations,
  getters: getters
})
