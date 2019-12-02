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
              @click.native.prevent="queryNotSent">显示未发药处方单信息
            </el-button>
          </el-form-item>
          <el-form-item>
            <el-button
              type="success"
              round
              icon="el-icon-edit"
              @click="handleReturnAllPres(multipleSelection)"
              :disabled="this.multipleSelection.length === 0">批量退处方
            </el-button>
          </el-form-item>
          <el-form-item>
            <el-button
              type="success"
              round
              icon="el-icon-edit"
              @click="handleAddPres(item)"
              :disabled="this.multipleSelection.length === 0">新增处方信息
            </el-button>
          </el-form-item>
        </el-col>
      </el-row>
    </el-form>
    <template>
      <el-table
        ref="=Table"
        :data="tableData"
        :row-style="{height: 90 + 'px'}"
        tooltip-effect="dark"
        height="520"
        style="width: 100%; margin-bottom: 10px; margin-top: 5px;"
        @selection-change="handleSelectionChange"
      >
        <el-table-column type="selection" width="35" v-model="multipleSelection"></el-table-column>
        <el-table-column
          prop="id"
          label="行号"
          width="90px"
          align="center">
        </el-table-column>
        <el-table-column
          prop="prescriptionCode"
          label="处方单号"
          width="130px"
          align="center">
        </el-table-column>
        <el-table-column
          prop="doctorID"
          label="开立医生编号"
          width="130px"
          align="center">
        </el-table-column>
        <el-table-column
          prop="charger"
          label="开立医生"
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
          label="状态"
          width="130px"
          align="center">
        </el-table-column>
        <el-table-column
          prop="totalStage"
          label="总疗程"
          width="130px"
          align="center">
        </el-table-column>
        <el-table-column
          prop="currentStage"
          label="当前疗程"
          width="130px"
          align="center">
        </el-table-column>
        <el-table-column
          prop="num"
          label="总数量"
          width="130px"
          align="center">
        </el-table-column>
        <el-table-column
          prop="sentNum"
          label="发送数量"
          width="130px"
          align="center"></el-table-column>
        <el-table-column
          prop="drugName"
          label="药品名称"
          width="130px"
          align="center">
        </el-table-column>
        <el-table-column
          prop="drugId"
          label="药品编号"
          width="130px"
          align="center">
        </el-table-column>
        <el-table-column label="操作" fixed="right" width="150" align="center">
          <template slot-scope="scope">
            <el-button
              size="mini"
              type="success"
              @click="handleReturn(scope.$index, scope.row)">退处方
            </el-button>
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
let allData
export default {
  name: 'managePres',
  data () {
    const checkPrescriptionCode = (rule, value, callback) => {
      if (value === '') {
        return callback(Error('请输入处方编号'))
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
          { validator: checkPrescriptionCode, trigger: 'blur' }
        ]
      },
      // 动态数据
      tableData: [],
      multipleSelection: []
    }
  },
  methods: {
    handleSelectionChange (val) {
      this.multipleSelection = val
    },
    handleReturnAllPres (multipleSelection) {
      this.$confirm('此操作将永久删除该处方单，是否继续', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        let _this = this
        this.loading1 = true
        var arr = multipleSelection
        let presIds = []
        for (var i = 0; i < arr.length; i++) {
          presIds.push(arr[i].id)
        }
        this.$axios.post('service/managePres/deleteAllPres', {
          presId: presIds
        })
          .then(res => {
            this.loading1 = false
            // console.log(res)
            if (res.data.code === 200) {
              _this.$store.commit('prescription', res.data.prescriptions)
              allData = res.data.prescriptions
              _this.tableData = res.data.prescriptions
              _this.total = this.tableData.length
              this.tableChange()
              this.$message.error(res.data.message)
            } else {
              this.$message.error(res.data.message)
            }
          })
          .catch(failResponse => {
            this.loading = false
            this.$message.error('无法连接服务器')
          })
      })
    },
    // handleAddPres (item) {
    //   this.$refs.edit.dialogFormVisible = true
    //   this.$refs.edit.form = {
    //     id: item.id,
    //     prescriptionCode: item.prescriptionCode,
    //     doctorID: item.doctorID,
    //     chargeTime: item.chargeTime,
    //     charger: item.charger,
    //     statue: item.statue,
    //     totalStage: item.totalStage,
    //     currentStage: item.currentStage,
    //     num: item.num,
    //     sentNum: item.sentNum,
    //     drugName: item.drugName,
    //     drugId: item.drugId
    //   }
    // },
    onSubmit () {
      let _this = this
      this.$refs.formInline.validate((valid) => {
        // 验证参数是否合法
        if (valid) { // 合法
          this.loading1 = true
          // 向后端发送数据
          this.$axios
            .post('/query/prescription_query/query', {
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
    queryNotSent () {
      this.loading = true
      let _this = this
      this.$axios.post('/query/prescription_query/queryNotSent')
        .then(res => {
          this.loading = false
          _this.$store.commit('repertory', res.data.prescriptions)
          allData = res.data.prescriptions
          _this.tableData = res.data.prescriptions
          _this.total = this.tableData.length
          this.tableChange()
          this.$message.success(res.data.message)
        })
        .catch(failResponse => {
          this.loading = false
          this.$message.error('服务器被干掉了！')
        })
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
    },
    handleReturn (index, row) {
      this.$confirm('此操作将永久删除该处方单，是否继续', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.$axios.post('/service/managePres/deletePres', {
          id: row.id,
          drugId: row.drugId,
          num: row.sentNum
        })
          .then(res => {
            this.loading1 = false
            console.log(res)
            if (res.data.code === 200) {
              this.$store.commit('prescription', res.data.prescriptions)
              allData = res.data.prescriptions
              this.tableData = res.data.prescriptions
              this.total = this.tableData.length
              this.tableChange()
              this.$message.success(res.data.message)
            } else {
              this.$message.error(res.data.message)
            }
          })
          .catch(failResponse => {
            this.loading = false
            this.$message.error('无法连接服务器')
          })
      })
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
      this.total = this.tableData.prescription
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
