// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import './icons'
import store from './store'

// 作者：徐奥飞
// 时间：2019-11-4 14:00
// 描述：引入Element
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
// 只有使用use之后才能使用
Vue.use(ElementUI)

Vue.config.productionTip = false

/* eslint-disable no-new */
new Vue({
  render: h => h(App),
  router, // 使用路由配置
  store // 使用Vuex进行状态管理
}).$mount('#app')
