// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.

// 作者：徐奥飞
// 时间：2019-11-4 14:30

import Vue from 'vue'
import App from './App'
import router from './router'

// 引入element-ui控件
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'

// 使用use命令才能够起到作用
Vue.use(ElementUI)

Vue.config.productionTip = false

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  components: { App },
  template: '<App/>'
})
