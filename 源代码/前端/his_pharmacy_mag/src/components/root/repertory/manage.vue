<template>
  <div>
    <el-row style="margin-top: -15px;">
      <el-button
        style="margin-right: 100%"
        type="primary"
        size="mini"
        round
        :loading="loading"
        icon="el-icon-search"
        @click.native.prevent="queryAll">显示全部药品信息
      </el-button>
    </el-row>
    <template>
      <el-table
        ref="multipleTable"
        :data="tableData"
        tooltip-effect="dark"
        max-height="500"
        style="width: 100%; margin-bottom: 10px; margin-top: 5px"
        row-style="height: 90px"
        @selection-change="handleSelectionChange">
        <el-table-column
          type="selection" fixed>
        </el-table-column>
        <el-table-column
          prop="drugsCode"
          label="药品编码"
          width="130px">
        </el-table-column>
        <el-table-column
          prop="drugsName"
          label="药品名称"
          width="130px">
        </el-table-column>
        <el-table-column
          prop="drugsFormat"
          label="药品规格"
          width="100%">
        </el-table-column>
        <el-table-column
          prop="drugsUnit"
          label="药品单位"
          width="100%">
        </el-table-column>
        <el-table-column
          prop="drugsPrice"
          label="药品单价"
          width="100%">
        </el-table-column>
        <el-table-column
          prop="drugsDosageID"
          label="药品剂型"
          width="100%">
        </el-table-column>
        <el-table-column
          prop="drugsTypeID"
          label="药品类型"
          width="100%">
        </el-table-column>
        <el-table-column
          prop="drugsNum"
          label="药品数量"
          width="100%">
        </el-table-column>
        <el-table-column
          prop="saveRequire"
          label="保存条件"
          width="100%">
        </el-table-column>
        <el-table-column
          prop="warehouse"
          label="存放位置"
          width="100%">
        </el-table-column>
        <el-table-column label="操作" fixed="right" width="150">
          <template slot-scope="scope">
            <el-button
              size="mini"
              type="success"
              @click="handleEdit(scope.$index, scope.row)">编辑</el-button>
            <el-button
              size="mini"
              type="danger"
              @click="handleDelete(scope.$index, scope.row)">删除</el-button>
          </template>
        </el-table-column>
      </el-table>
    </template>
    <template>
      <div class="block">
        <el-pagination
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
          :current-page="currentPage"
          :page-sizes="[5, 10, 20, 50]"
          :page-size="pageSize"
          layout="total, sizes, prev, pager, next, jumper"
          :total="total">
        </el-pagination>
      </div>
    </template>
  </div>
</template>

<script>
var allData
export default {
  name: 'manage',
  data () {
    return {
      loading: false,
      total: 0,
      currentPage: 1,
      pageSize: 5,
      // 动态数据
      tableData: [],
      multipleSelection: []
    }
  },
  methods: {
    toggleSelection (rows) {
      if (rows) {
        rows.forEach(function (row) {
          this.$refs.multipleTable.toggleRowSelection(row)
        })
      } else {
        this.$refs.multipleTable.clearSelection()
      }
    },
    queryAll () {
      this.loading = true
      var _this = this
      this.$axios.post('/repertory/manage/queryAll')
        .then(res => {
          this.loading = false
          _this.$store.commit('queryAll', res.data.drugs)
          allData = res.data.drugs
          _this.tableData = res.data.drugs
          _this.total = this.tableData.length
          this.tableChange()
        })
        .catch(failResponse => {
          this.loading = false
          this.$message.error('服务器被干掉了！')
        })
    },
    handleSelectionChange (val) {
      this.multipleSelection = val
    },
    // 修改每页条数触发
    handleSizeChange (val) {
      this.pageSize = val
      // this.tableData = allData.slice((this.currentPage - 1) * this.pageSize, this.currentPage * this.pagesize)
      this.currentPage = 1
      this.tableChange()
      // console.log(`每页 ${val} 条`)
    },
    // 翻页触发
    handleCurrentChange (val) {
      this.currentPage = val
      // this.tableData = allData.slice((this.currentPage - 1) * this.pageSize, this.currentPage * this.pageSize)
      this.tableChange()
      // console.log(`当前页: ${val}`)
    },
    tableChange () {
      this.tableData = allData.slice((this.currentPage - 1) * this.pageSize, this.currentPage * this.pageSize)
    },
    handleEdit (index, row) {
      console.log(index, row)
    },
    handleDelete (index, row) {
      console.log(index, row)
    }
  }
  // activated () {
  //   this.queryAll()
  // }
}
</script>
<style scoped>
</style>
