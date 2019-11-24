// 作者：徐奥飞
// 时间：2019-11-12
// 描述：getters 是 store 的计算属性，即获取 state 中状态，但是不做修改

const getters = {
  doneTodos (state) {
    return state.drugs
  }
}

export default getters
