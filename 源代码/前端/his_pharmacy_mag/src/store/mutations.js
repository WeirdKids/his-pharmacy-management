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
    state.repertory = data
    window.sessionStorage.setItem('repertory', JSON.stringify(data))
  },
  drug (state, data) {
    state.drug = data
    window.sessionStorage.setItem('drug', JSON.stringify((data)))
  },
  prescription (state, data) {
    state.prescription = data
    window.sessionStorage.setItem('prescription', JSON.stringify((data)))
  }
}

export default mutations
