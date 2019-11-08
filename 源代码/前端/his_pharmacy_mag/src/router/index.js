import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)

export const constantRouterMap = [
  // 配置默认的路径，默认显示登录页面
  {path: '/', component: () => import('@/components/login')},
  // 配置登录成功页面，使用时需要使用path路径来实现跳转
  {path: '/success', component: () => import('@/components/success')},
  // 配置登录失败页面
  {path: '/error', component: () => import('@/components/error'), hidden: true}
]
export default new Router({
  // mode: 'history', //后端支持可开
  // eslint-disable-next-line standard/object-curly-even-spacing
  scrollBehavior: () => ({ y: 0}),
  routes: constantRouterMap // 指定路由列表
})
