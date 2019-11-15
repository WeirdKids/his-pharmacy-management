// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import './icons'
import store from './store'
// 引入ElementUI
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
// 设置反向代理，前端请求默认发送到 http://localhost:8088/api
var axios = require('axios')
axios.defaults.baseURL = 'http://localhost:8088/api'
// 全局注册，之后可在其他组件中通过 this.$axios 发送数据
Vue.prototype.$axios = axios

Vue.config.productionTip = false
Vue.use(ElementUI)

router.beforeEach((to, from, next) => {
  // 判断访问的路径是否需要登录
  if (to.meta.requireAuth) { // 需要登录
    // 判断 store 里有没有存储 user 的信息
    if (store.state.user.username) { // 如果存在
      next() // 放行
    } else { // 不存在
      next({
        path: 'login', // 跳转到登录页面
        query: {redirect: to.fullPath} // 存储访问的页面路径（以便在登录后跳转到访问页）
      })
    }
  } else { // 不需要登录
    next() // 放行
  }
})

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  store,
  components: { App },
  template: '<App/>'
})
