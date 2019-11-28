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
    window.sessionStorage.removeItem('repertory')
  },
  repertory (state, data) {
    state.repertory = data
    window.sessionStorage.setItem('repertory', JSON.stringify(data))
  },
  updatePageSize (state, data) {
    state.repTable.pageSize = data
    window.sessionStorage.setItem('repTable', JSON.stringify(state.repTable))
  },
  updateCurrentPage (state, data) {
    state.repTable.currentPage = data
    window.sessionStorage.setItem('repTable', JSON.stringify(state.repTable))
  }
}
export default mutations
