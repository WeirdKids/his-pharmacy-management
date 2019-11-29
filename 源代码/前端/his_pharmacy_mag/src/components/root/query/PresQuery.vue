<template>
  <div>
    <el-form
      :inline="true"
      :model="formInline"
      :rules="rules"
      status-icon
      style="margin-top: -15px; height: 35px;"
      ref="formInline"
      size="mini">
      <el-row>
        <el-col :span="1">
          <el-form-item prop="prescriptionCode" style="position: absolute;">
            <el-input
              name="prescriptionCode"
              type="number"
              v-model="formInline.prescriptionCode"
              placeholder="处方单号">
              <span slot="prefix">
                <svg-icon icon-class="prescriptionCode" style="color: #409eff;"></svg-icon>
              </span>
            </el-input>
          </el-form-item>
        </el-col>
        <el-col :span="10">
          <el-form-item>
            <el-button
              type="primary"
              :loading="loading1"
              @click="onSubmit"
              round
              icon="el-icon-search">查询
            </el-button>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item>
            <el-button
              type="primary"
              round
              :loading="loading"
              icon="el-icon-search"
              @click.native.prevent="queryAll">显示全部处方信息
            </el-button>
          </el-form-item>
        </el-col>
      </el-row>
    </el-form>
    <template>
      <el-table
        ref="multipleTable"
        :data="tableData"
        :row-style="{height: 90 + 'px'}"
        tooltip-effect="dark"
        height="520"
        style="width: 100%; margin-bottom: 10px; margin-top: 5px;"
        @selection-change="handleSelectionChange">
        <el-table-column
          type="selection" fixed>
        </el-table-column>
        <el-table-column
          prop="prescriptionCode"
          label="处方单号"
          width="130px"
          align="center">
        </el-table-column>
        <el-table-column
          prop="doctorID"
          label="医生编号"
          width="130px"
          align="center">
        </el-table-column>
        <el-table-column
          prop="charger"
          label="开药医生"
          width="130px"
          align="center">
        </el-table-column>
        <el-table-column
          prop="chargeTime"
          label="开立时间"
          width="130px"
          align="center">
        </el-table-column>
        <el-table-column
          prop="statue"
          label="发药状态"
          width="130px"
          align="center">
        </el-table-column>
        <el-table-column
          prop="stage"
          label="总疗程"
          width="130px"
          align="center">
        </el-table-column>
        <el-table-column
          prop="num"
          label="药品数量"
          width="130px"
          align="center">
        </el-table-column>
        <el-table-column
          prop="drugName"
          label="药品名称"
          width="130px"
          align="center">
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
let allData
export default {
  name: 'presQuery',
  data () {
    const checkMnemonic = (rule, value, callback) => {
      if (value === '') {
        return callback(Error('请输入处方单号'))
      } else {
        callback()
      }
    }
    return {
      loading: false,
      loading1: false,
      total: 0,
      currentPage: 1,
      pageSize: 5,
      formInline: {
        prescriptionCode: ''
      },
      rules: {
        prescriptionCode: [
          { validator: checkMnemonic, trigger: 'blur' }
        ]
      },
      // 动态数据
      tableData: []
    }
  },
  methods: {
    onSubmit () {
      let _this = this
      this.$refs.formInline.validate((valid) => {
        // 验证参数是否合法
        if (valid) { // 合法
          this.loading1 = true
          // 向后端发送数据
          this.$axios
            .post('/query/pres_query/query', {
              prescriptionCode: this.formInline.prescriptionCode
            })
            // 收到后端返回的成功代码
            .then(res => {
              this.loading1 = false
              if (res.data.code === 200) {
                _this.$store.commit('prescription', res.data.prescriptions)
                allData = res.data.prescriptions
                _this.tableData = res.data.prescriptions
                _this.total = this.tableData.length
                this.tableChange()
                this.$message.success(res.data.message)
              } else {
                this.$message.error(res.data.message)
              }
            })
            .catch(failResponse => {
              this.loading1 = false
              this.$message.error('服务器被干掉了！')
            })
        } else {
          // eslint-disable-next-line no-console
          console.log('参数验证不合法！')
          return false
        }
      })
    },
    queryAll () {
      this.loading = true
      this.$axios.post('/query/pres_query/queryAll')
        .then(res => {
          this.loading = false
          console.log(res)
          this.$store.commit('prescription', res.data.prescriptions)
          allData = res.data.prescriptions
          this.tableData = res.data.prescriptions
          this.total = this.tableData.length
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
      // this.currentPage = 1
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
    }
  },
  computed: {
    prescriptions () {
      return this.$store.state.prescriptions
    }
  },
  created () {
    if (sessionStorage.getItem('prescription')) {
      allData = this.$store.state.prescription
      this.tableData = this.$store.state.prescription
      this.total = this.tableData.length
      this.tableChange()
    }
  }
  // activated () {
  //   this.queryAll()
  // }
}
</script>
<style>
</style>
