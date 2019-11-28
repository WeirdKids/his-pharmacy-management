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
          <el-form-item prop="mnemonicCode" style="position: absolute;">
            <el-input
              name="mnemonicCode"
              type="text"
              v-model="formInline.mnemonicCode"
              placeholder="药品助记码">
              <span slot="prefix">
                <svg-icon icon-class="mnemonicCode" style="color: #409eff;"></svg-icon>
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
              @click.native.prevent="queryAll">显示全部药品信息
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
          prop="drugsCode"
          label="药品编码"
          width="130px"
          align="center">
        </el-table-column>
        <el-table-column
          prop="drugsName"
          label="药品名称"
          width="130px"
          align="center">
        </el-table-column>
        <el-table-column
          prop="drugsFormat"
          label="药品规格"
          width="130px"
          align="center">
        </el-table-column>
        <el-table-column
          prop="drugsUnit"
          label="药品单位"
          width="130px"
          align="center">
        </el-table-column>
        <el-table-column
          prop="drugsPrice"
          label="药品单价"
          width="130px"
          align="center">
        </el-table-column>
        <el-table-column
          prop="drugsDosageID"
          label="药品剂型"
          width="130px"
          align="center">
        </el-table-column>
        <el-table-column
          prop="drugsTypeID"
          label="药品类型"
          width="130px"
          align="center">
        </el-table-column>
        <el-table-column
          prop="totalNum"
          label="药品总数量"
          width="130px"
          align="center"></el-table-column>
        <el-table-column
          prop="saveRequire"
          label="保存条件"
          width="130px"
          align="center">
        </el-table-column>
        <el-table-column
          type="expand"
          prop="warehouses"
          label="存放信息"
          width="130px"
          fixed="right"
          align="center">
          <template slot-scope="scope">
            <el-table :data="scope.row.warehouses">
              <el-table-column
                prop="num"
                label="数量"
                sortable
                align="center">
              </el-table-column>
              <el-table-column
                prop="warehouse"
                label="存放地点"
                sortable
                align="center">
              </el-table-column>
            </el-table>
          </template>
        </el-table-column>
        <el-table-column
          label="操作"
          fixed="right"
          width="150"
          align="center">
          <template slot-scope="scope">
            <el-button
              size="mini"
              type="success"
              @click="handleEdit(scope.$index, scope.row), dialogFormVisible = true"
            >编辑</el-button>
            <el-button
              size="mini"
              type="danger"
              @click="handleDelete(scope.$index, scope.row)"
            >删除</el-button>
          </template>
        </el-table-column>
      </el-table>
      <el-dialog
        title="药品信息"
        :visible.sync="dialogFormVisible">
        <el-form
          :model="form"
          ref="form"
          label-width="80px"
          status-icon
          :rules="rules1">
          <el-form-item v-model="form.id"></el-form-item>
          <el-row>
            <el-col :span="12">
              <el-form-item label="药品编码">
                <el-input v-model="form.drugsCode" autocomplete="off" readonly></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item label="药品名称">
                <el-input v-model="form.drugsName" autocomplete="off" readonly></el-input>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="12">
              <el-form-item label="药品规格">
                <el-input v-model="form.drugsFormat" autocomplete="off" readonly></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item label="保存条件">
                <el-input v-model="form.saveRequire" autocomplete="off" readonly></el-input>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="8">
              <el-form-item label="药品单位">
                <el-input v-model="form.drugsUnit" autocomplete="off" readonly></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item label="药品剂型">
                <el-input v-model="form.drugsDosageID" autocomplete="off" readonly></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item label="药品类型">
                <el-input v-model="form.drugsTypeID" autocomplete="off" readonly></el-input>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="12">
              <el-form-item label="药品单价" prop="drugsPrice">
                <el-input v-model="form.drugsPrice"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item label="总数量">
                <el-input
                  v-model="form.totalNum"
                  readonly
                  @focus="InputTotalNum"></el-input>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="12">
              <el-form-item label="配药房" prop="num1">
                <el-input
                  v-model="form.num1">
                </el-input>
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item label="储藏室" prop="num2">
                <el-input
                  v-model="form.num2">
                </el-input>
              </el-form-item>
            </el-col>
          </el-row>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button @click="handleCancel" size="small" round>取消</el-button>
          <el-button type="primary" @click="dialogFormVisible = false, updateData(form)" size="small" round>确定</el-button>
        </div>
      </el-dialog>
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
  name: 'manage',
  data () {
    const checkMnemonic = (rule, value, callback) => {
      if (value === '') {
        return callback(Error('请输入药品助记码'))
      } else {
        callback()
      }
    }
    const isNumber = (rule, value, callback) => {
      if (!value) {
        return callback(new Error('输入不可为空'))
      } else {
        setTimeout(() => {
          if (!Number(value)) {
            return callback(new Error('请输入正数'))
          } else {
            const re = /^[1-9]\d*(\.[0-9]+)?$|^0$/
            const reCheck = re.test(value)
            if (!reCheck) {
              return callback(new Error('请输入正数'))
            } else {
              callback()
            }
          }
        }, 300)
      }
    }
    const isInteger = (rule, value, callback) => {
      if (!value) {
        return callback(new Error('输入不可为空'))
      }
      setTimeout(() => {
        if (!Number(value)) {
          return callback(new Error('请输入正整数'))
        } else {
          const re = /^[1-9]\d*$|^0$/
          const reCheck = re.test(value)
          if (!reCheck) {
            return callback(new Error('请输入正整数'))
          } else {
            callback()
          }
        }
      }, 300)
    }
    return {
      loading: false,
      loading1: false,
      total: 0,
      currentPage: 1,
      pageSize: 5,
      dialogFormVisible: false,
      form: {
        drugsName: '',
        drugsCode: '',
        drugsDosageID: '',
        drugsFormat: '',
        drugsPrice: '',
        drugsTypeID: '',
        drugsUnit: '',
        saveRequire: '',
        totalNum: '',
        warehouse: '',
        num1: 0,
        num2: 0
      },
      formInline: {
        mnemonicCode: ''
      },
      rules: {
        mnemonicCode: [
          { validator: checkMnemonic, trigger: 'blur' }
        ]
      },
      rules1: {
        drugsPrice: [
          { validator: isNumber, trigger: 'blur' }
        ],
        num1: [
          { validator: isInteger, trigger: 'blur' }
        ],
        num2: [
          { validator: isInteger, trigger: 'blur' }
        ]
      },
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
    onSubmit () {
      let _this = this
      this.$refs.formInline.validate((valid) => {
        // 验证参数是否合法
        if (valid) { // 合法
          this.loading1 = true
          // 向后端发送数据
          this.$axios
            .post('/repertory/manage/query', {
              mnemonicCode: this.formInline.mnemonicCode
            })
            // 收到后端返回的成功代码
            .then(res => {
              this.loading1 = false
              if (res.data.code === 200) {
                _this.$store.commit('repertory', res.data.drugs)
                allData = res.data.drugs
                _this.tableData = res.data.drugs
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
      let _this = this
      this.$axios.post('/repertory/manage/queryAll')
        .then(res => {
          this.loading = false
          _this.$store.commit('repertory', res.data.drugs)
          allData = res.data.drugs
          _this.tableData = res.data.drugs
          _this.total = this.tableData.length
          this.tableChange()
          this.$message.success(res.data.message)
          // console.log(res)
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
    },
    InputTotalNum () {
      this.$refs.form.validate((valid) => {
        // 验证参数是否合法
        if (valid) { // 合法
          this.$set(this.form, 'totalNum', String((parseInt(this.form.num1) + parseInt(this.form.num2))))
        } else {
          return false
        }
      })
    },
    handleEdit (index, row) {
      this.form = {...row}
      this.$set(this.form, 'drugsPrice', row.drugsPrice)
      // console.log(row.warehouses)
      let warehouses = row.warehouses
      if (warehouses.length === 2) {
        // this.$set 解决给输入框赋值后无法修改的问题
        this.$set(this.form, 'num1', (warehouses[0].warehouse === '配药房') ? warehouses[0].num : warehouses[1].num)
        this.$set(this.form, 'num2', (warehouses[1].warehouse === '储藏室') ? warehouses[1].num : warehouses[0].num)
        // this.form.num1 = (warehouses[0].warehouse === '配药房') ? warehouses[0].num : warehouses[1].num
        // this.form.num2 = (warehouses[1].warehouse === '储藏室') ? warehouses[1].num : warehouses[0].num
      } else if (warehouses.length === 1) {
        this.$set(this.form, 'num1', (warehouses[0].warehouse === '配药房') ? warehouses[0].num : 0)
        this.$set(this.form, 'num2', (warehouses[0].warehouse === '储藏室') ? warehouses[0].num : 0)
      } else {
        this.$set(this.form, 'num1', 0)
        this.$set(this.form, 'num2', 0)
      }
    },
    handleCancel () {
      this.dialogFormVisible = false
    },
    updateData (form) {

    },
    handleDelete (index, row) {
      this.$confirm('此操作将永久删除该药品信息，是否继续？', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.$message({
          type: 'success',
          message: '删除成功'
        })
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消删除'
        })
      })
    }
  },
  computed: {
    repertory () {
      return this.$store.state.repertory
    },
    warehouses () {
      return this.$store.state.warehouses
    }
  },
  created () {
    if (sessionStorage.getItem('repertory')) {
      allData = this.$store.state.repertory
      this.tableData = this.$store.state.repertory
      this.total = this.tableData.length
      this.tableChange()
    }
  }
}
</script>
<style>
</style>
