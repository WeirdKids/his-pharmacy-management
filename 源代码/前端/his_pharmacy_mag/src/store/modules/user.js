// 作者：徐奥飞
// 时间：2019-11-4 16:30

// 引入登录api接口
import {login} from '../../api/login'

const user = {
  actions: {
    // 登录
    // 定义Login方法，this.$store.dispatch("Login") 调用
    Login ({ commit }, userInfo) {
      const username = userInfo.username.trim()
      // 封装一个 Promise
      return new Promise((resolve, reject) => {
        // 使用 login 接口进行网络请求
        login(username, userInfo.password).then(response => {
          // 提交一个 mutation，通知状态改变
          commit('')
          // 将结果封装进 Promise
          resolve(response)
        }).catch(error => {
          reject(error)
        })
      })
    }
  }
}
export default user
