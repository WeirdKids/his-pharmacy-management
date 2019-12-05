import Vue from 'vue'
import Router from 'vue-router'
// 导入刚才编写的组件
import Login from '@/components/Login'
import Home from '@/components/Home'
import PresQuery from '@/components/root/query/PresQuery'
import DrugsQuery from '@/components/root/query/DrugsQuery'
import SendDrugs from '@/components/root/service/sendDrugs'
import RepertoryMag from '@/components/root/repertory/manage'
import Stockout from '@/components/root/repertory/stockout'
import Stock from '@/components/root/repertory/stock'
import ReturnDrugs from '@/components/root/service/returnDrugs'
import ManagePres from '@/components/root/service/managePres'
import catalogMag from '@/components/root/catalog/manage'

Vue.use(Router)

export default new Router({
  // 把Vue中配置的路由从默认 hash 模式切换为 history 模式
  mode: 'history',
  scrollBehavior: () => ({
    y: 0
  }),
  routes: [
    // 下面都是固定的写法
    {
      path: '/',
      // 页面跳转
      redirect: '/login'
    },
    {
      path: '/login',
      name: 'Login',
      component: Login,
      meta: {
        keepAlive: true
      }
    },
    {
      path: '/home',
      name: '主页',
      component: Home,
      meta: {
        requireAuth: true
      },
      children: [
        {
          path: '/query/prescription_query',
          name: '处方查询',
          component: PresQuery,
          meta: {
            // 在需要拦截的路由中加入一条元数据，设置一个 requireAuth 字段
            requireAuth: true,
            keepAlive: true,
            hidden: true
          }
        },
        {
          path: '/query/drugs_query',
          name: '药品信息查询',
          component: DrugsQuery,
          meta: {
            requireAuth: true,
            keepAlive: true,
            hidden: true
          }
        },
        {
          path: '/repertory/manage',
          name: '库存管理',
          component: RepertoryMag,
          meta: {
            requireAuth: true,
            keepAlive: true,
            hidden: true
          }
        },
        {
          path: '/repertory/stockOut',
          name: '缺货信息',
          component: Stockout,
          meta: {
            requireAuth: true,
            keepAlive: true,
            hidden: true
          }
        },
        {
          path: '/repertory/stock',
          name: '进货信息',
          component: Stock,
          meta: {
            requireAuth: true,
            keepAlive: true,
            hidden: true
          }
        },
        {
          path: '/service/sendDrugs',
          name: '药房发药',
          component: SendDrugs,
          meta: {
            requireAuth: true,
            keepAlive: true,
            hidden: true
          }
        },
        {
          path: '/service/returnDrugs',
          name: '药品退回审核',
          component: ReturnDrugs,
          meta: {
            requireAuth: true,
            keepAlive: true,
            hidden: true
          }
        },
        {
          path: '/service/managePres',
          name: '退回处方信息',
          component: ManagePres,
          meta: {
            requireAuth: true,
            keepAlive: true,
            hidden: true
          }
        },
        {
          path: '/catalog/manage',
          name: '目录管理',
          component: catalogMag,
          meta: {
            requireAuth: true,
            keepAlive: true,
            hidden: true
          }
        }
      ]
    }
  ]
})
