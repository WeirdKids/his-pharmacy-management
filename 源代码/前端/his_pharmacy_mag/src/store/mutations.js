// 作者：徐奥飞
// 时间：2019-11-12
// 描述：定义了 state 数据的修改操作

const mutations = {
  login (state, data) {
    state.user.username = data.username
    state.user.realName = data.realName
    window.sessionStorage.setItem('user', JSON.stringify(state.user))
  },
  logout () {
    window.sessionStorage.removeItem('user')
    window.sessionStorage.removeItem('drugs')
  },
  repertory (state, data) {
    state.repertories = data
    window.sessionStorage.setItem('repertories', JSON.stringify(data))
  }
}

export default mutations
