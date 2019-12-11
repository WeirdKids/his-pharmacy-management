<template>
  <div>
    <el-input
      placeholder="输入关键字进行过滤"
      v-model="filterText">
    </el-input>

    <el-tree
      class="filter-tree"
      node-key="id"
      :data="data"
      :props="defaultProps"
      :filter-node-method="filterNode"
      lazy
      :load="loadNode"
      ref="tree">
    </el-tree>
  </div>
</template>

<script>
export default {
  watch: {
    filterText (val) {
      this.$refs.tree.filter(val)
    }
  },

  methods: {
    filterNode (value, data) {
      if (!value) return true
      return data.label.indexOf(value) !== -1
    },
    // 加载树节点
    loadNode (node, resolve) {
      // 如果是顶级的父节点
      if (node.level === 0) {
        // 查找顶级对象
        this.$axios.post('/getTree', {
          id: parseInt(node.level)
        })
          .then(res => {
            return resolve(res.data)
          })
          .catch(() => {
            let data = []
            return resolve(data)
          })
      } else {
        // 根据父节点 label 找寻下一级的所有节点
        this.$axios.post('/getTree', {
          id: parseInt(node.data.label)
        })
          .then(res => {
            console.log(res.data)
            return resolve(res.data)
          })
          .catch(() => {
            let data = []
            return resolve(data)
          })
      }
    }
  },

  data () {
    return {
      filterText: '',
      data: [],
      defaultProps: {
        children: 'children',
        label: 'label'
      },
      node: {} // tree 节点对象
    }
  }
}
</script>
