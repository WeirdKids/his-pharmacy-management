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
      <el-form-item>
        <el-button
          style="margin-right: 100%"
          type="primary"
          round
          :loading="loading"
          icon="el-icon-search"
          @click.native.prevent="queryAll">显示全部药品信息
        </el-button>
      </el-form-item>
      <el-form-item prop="mnemonicCode">
        <el-input
          name="mnemonicCode"
          type="text"
          style="width: 200px"
          v-model="formInline.mnemonicCode"
          placeholder="药品助记码">
          <span slot="prefix">
            <svg-icon icon-class="mnemonicCode" style="color: #409eff;"></svg-icon>
          </span>
        </el-input>
      </el-form-item>
      <el-form-item>
        <el-button
          type="primary"
          :loading="loading1"
          @click="onSubmit"
          round
          align="center"
          icon="el-icon-search">查询
        </el-button>
      </el-form-item>
      <el-form-item>
        <el-button
          type="primary"
          @click="addDrug(form)"
          round
          align="center"
          icon="el-icon-plus">添加药品</el-button>
      </el-form-item>
      <el-form-item>
        <el-button
          type="danger"
          round
          :loading="loading2"
          icon="el-icon-delete"
          @click="handleDeleteAll(multipleSelection)"
          :disabled="this.multipleSelection.length === 0">批量删除
        </el-button>
      </el-form-item>
      <el-form-item>
        <el-button
          type="success"
          round
          icon="el-icon-edit"
          @click="handleEditAll(multipleSelection)"
          :disabled="this.multipleSelection.length === 0">批量修改
        </el-button>
      </el-form-item>
      <el-form-item>
        <el-button
          type="primary"
          round
          icon="el-icon-download"
          @click="handleImport">
          导入药品数据
        </el-button>
      </el-form-item>
      <el-form-item>
        <el-button
          type="primary"
          round
          icon="el-icon-upload2"
          @click.native.prevent="export2Excel(tableData,multipleSelection)">导出处方单信息
        </el-button>
      </el-form-item>
      <el-form-item>
        <el-button
          type="primary"
          round
          icon="el-icon-search"
          @click="painting">库存信息
        </el-button>
      </el-form-item>
      <el-form-item>
        <el-button
          type="success"
          round
          icon="el-icon-printer"
          @click="showPrintData(multipleSelection)"
          :disabled="this.multipleSelection.length === 0">打印
        </el-button>
      </el-form-item>
    </el-form>
    <template>
      <el-table
        ref="multipleTable"
        :data="tableData"
        :row-style="{height: 90 + 'px'}"
        tooltip-effect="dark"
        height="520"
        style="width: 100%; margin-bottom: 10px; margin-top: 5px;"
        :row-key="getRowKeys"
        @selection-change="handleSelectionChange">
        <el-table-column
          type="selection"
          reserve-selection
          fixed>
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
          prop="mnemonicCode"
          label="药品助记码"
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
          align="center">
        </el-table-column>
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
              <el-form-item label="药品编码" prop="drugsCode">
                <el-input v-model="form.drugsCode" autocomplete="off" :readonly="readonly"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item label="药品名称" prop="drugsName">
                <el-input v-model="form.drugsName" autocomplete="off" :readonly="readonly"></el-input>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="8">
              <el-form-item label="助记码" prop="mnemonicCode">
                <el-input v-model="form.mnemonicCode" autocomplete="off" :readonly="readonly"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item label="药品规格" prop="drugsFormat">
                <el-input v-model="form.drugsFormat" autocomplete="off" :readonly="readonly"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item label="保存条件" prop="saveRequire">
                <el-input v-model="form.saveRequire" autocomplete="off" :readonly="readonly"></el-input>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="8">
              <el-form-item label="药品单位" prop="drugsUnit">
                <el-input v-model="form.drugsUnit" autocomplete="off" :readonly="readonly"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item label="药品剂型" prop="drugsDosageID">
                <el-input v-model="form.drugsDosageID" autocomplete="off" :readonly="readonly"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item label="药品类型" prop="drugsTypeID">
                <el-input v-model="form.drugsTypeID" autocomplete="off" :readonly="readonly"></el-input>
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
        <span slot="footer" class="dialog-footer">
          <el-button @click="handleCancel" size="small" round>取消</el-button>
          <el-button type="primary" @click="dialogFormVisible = false, updateData(form)" size="small" round>确定</el-button>
        </span>
      </el-dialog>
      <el-dialog
        title="药品存放地点转移"
        :visible.sync="dialogFormVisible1">
        <el-form
          :model="form1"
          ref="form1"
          label-width="80px">
          <el-form-item label="转移地点" :label-width="formLabelWidth">
            <el-select v-model="form1.warehouse" placeholder="-----" style="margin-right: 200px" value-key="id" @change="currentSel">
              <el-option v-for="item in options" :key="item.id" :label="item.label" :value="item"></el-option>
            </el-select>
          </el-form-item>
        </el-form>
        <span slot="footer" class="dialog-footer">
          <el-button @click="dialogFormVisible1 = false" size="small" round>取消</el-button>
          <el-button type="primary" @click="dialogFormVisible1 = false, updateWarehouse(form)" size="small" round>确定</el-button>
        </span>
      </el-dialog>
      <el-dialog
        title="库存信息饼状图"
        :visible.sync="dialogFormVisible2">
        <el-row>
          <el-col :span="12">
            <div id="pieReport" style="width: 400px; height: 300px;"></div>
          </el-col>
          <el-col :span="12">
            <div id="pieReport1" style="width: 400px; height: 300px;"></div>
          </el-col>
        </el-row>
      </el-dialog>
      <el-dialog
        title="药品信息单"
        :close-on-click-modal="false"
        :visible.sync="printDialogVisible"
        width="60%">
        <el-button
          type="success"
          round
          size="small"
          style="margin-right: 100%"
          v-print="'#printTest'"
          icon="el-icon-printer">打印</el-button>
        <div id="printTest">
          <div style="font-size: 22px;padding-bottom:10px;font-weight:800;font-family:宋体; text-align:center">药品信息</div>
          <span style="font-size:16px;font-family:宋体;float:right;">打印时间：{{ getDate }}</span>
          <table>
            <thead>
            <th>药品编号</th>
            <th>药品名称</th>
            <th>药品规格</th>
            <th>药品单价</th>
            <th>药品总数量</th>
            <th>保存条件</th>
            <th>配药房</th>
            <th>储存室</th>
            </thead>
            <tbody>
            <tr v-for="item in multipleSelection" :key="item.id">
              <td>{{ item.drugsCode }}</td>
              <td>{{ item.drugsName }}</td>
              <td>{{ item.drugsFormat }}</td>
              <td>{{ item.drugsPrice }}</td>
              <td>{{ item.totalNum }}</td>
              <td>{{ item.saveRequire }}</td>
              <td>{{ item.warehouses[0].warehouse === '配药房' ? item.warehouses[0].num : item.warehouses[1].num }}</td>
              <td>{{ item.warehouses[0].warehouse === '储藏室' ? item.warehouses[0].num : item.warehouses[1].num }}</td>
            </tr>
            </tbody>
          </table>
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
import echarts from 'echarts'
let allData
let saveWarehouse
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
        return callback(new Error('输入不能为空'))
      } else {
        setTimeout(() => {
          const re = /^((0{1}\.\d{1,2})|([1-9]\d*\.{1}\d{1,2})|([1-9]+\d*)|0)$/
          const reCheck = re.test(value)
          if (!reCheck) {
            return callback(new Error('请输入正数'))
          } else {
            callback()
          }
        }, 300)
      }
    }
    const NotEmpty = (rule, value, callback) => {
      if (!value) {
        return callback(new Error('输入不能为空'))
      } else {
        callback()
      }
    }
    const isInteger = (rule, value, callback) => {
      if (value === '') {
        return callback(new Error('输入不能为空'))
      }
      setTimeout(() => {
        const re = /^(0|[1-9][0-9]*)$/
        const reCheck = re.test(value)
        if (!reCheck) {
          return callback(new Error('请输入正整数2'))
        } else {
          callback()
        }
      }, 300)
    }
    return {
      loading: false,
      loading1: false,
      loading2: false,
      total: 0,
      currentPage: 1,
      pageSize: 5,
      dialogFormVisible: false,
      dialogFormVisible1: false,
      dialogFormVisible2: false,
      printDialogVisible: false,
      readonly: true,
      formLabelWidth: '220px',
      form: {
      },
      form1: {

      },
      options: [
        {
          value: '选项1',
          id: 1,
          label: '配药房'
        },
        {
          value: '选项2',
          id: 2,
          label: '储藏室'
        }
      ],
      formInline: {
        mnemonicCode: ''
      },
      rules: {
        mnemonicCode: [
          { validator: checkMnemonic, trigger: 'blur' }
        ]
      },
      rules1: {
        drugsCode: [
          { validator: NotEmpty, trigger: 'blur' }
        ],
        drugsName: [
          { validator: NotEmpty, trigger: 'blur' }
        ],
        drugsUnit: [
          { validator: NotEmpty, trigger: 'blur' }
        ],
        drugsTypeID: [
          { validator: NotEmpty, trigger: 'blur' }
        ],
        drugsDosageID: [
          { validator: NotEmpty, trigger: 'blur' }
        ],
        mnemonicCode: [
          { validator: NotEmpty, trigger: 'blur' }
        ],
        drugsFormat: [
          { validator: NotEmpty, trigger: 'blur' }
        ],
        saveRequire: [
          { validator: NotEmpty, trigger: 'blur' }
        ],
        drugsPrice: [
          { type: 'number', validator: isNumber, trigger: 'change' }
        ],
        num1: [
          { type: 'number', validator: isInteger, trigger: 'change' }
        ],
        num2: [
          { type: 'number', validator: isInteger, trigger: 'change' }
        ]
      },
      // 动态数据
      tableData: [],
      multipleSelection: [],
      charts: '',
      opinion: ['剩余容量', '已占用容量'],
      opinionData: [],
      opinionData1: []
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
    handleSelectionChange (val) {
      this.multipleSelection = val
      // console.log(val)
    },
    getRowKeys (row) {
      return row.id
    },
    // 根据助记码查询
    onSubmit () {
      let _this = this
      this.$refs.formInline.validate((valid) => {
        // 验证参数是否合法
        if (valid) { // 合法
          this.loading1 = true
          // 向后端发送数据
          this.$axios
            .post('/query', {
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
                this.tableChange1()
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
      this.formInline.mnemonicCode = ''
      this.$axios.post('/queryAll')
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
    // 修改每页条数触发
    handleSizeChange (val) {
      this.pageSize = val
      this.$store.commit('updatePageSize', val)
      // this.tableData = allData.slice((this.currentPage - 1) * this.pageSize, this.currentPage * this.pagesize)
      this.currentPage = 1
      this.tableChange()
      // console.log(`每页 ${val} 条`)
    },
    // 翻页触发
    handleCurrentChange (val) {
      this.currentPage = val
      this.$store.commit('updateCurrentPage', val)
      // this.tableData = allData.slice((this.currentPage - 1) * this.pageSize, this.currentPage * this.pageSize)
      this.tableChange()
      // console.log(`当前页: ${val}`)
    },
    tableChange () {
      this.tableData = allData.slice((this.currentPage - 1) * this.pageSize, this.currentPage * this.pageSize)
    },
    tableChange1 () {
      this.tableData = allData.slice(0 * this.pageSize, 1 * this.pageSize)
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
      this.readonly = true
      this.form = {...row}
      this.$set(this.form, 'drugsPrice', row.drugsPrice)
      // // console.log(row.warehouses)
      let warehouses = row.warehouses
      if (warehouses.length === 2) {
        // this.$set 解决给输入框赋值后无法修改的问题
        this.$set(this.form, 'num1', (warehouses[0].warehouse === '配药房') ? warehouses[0].num : warehouses[1].num)
        this.$set(this.form, 'num2', (warehouses[1].warehouse === '储藏室') ? warehouses[1].num : warehouses[0].num)
      } else if (warehouses.length === 1) {
        this.$set(this.form, 'num1', (warehouses[0].warehouse === '配药房') ? warehouses[0].num : '0')
        this.$set(this.form, 'num2', (warehouses[0].warehouse === '储藏室') ? warehouses[0].num : '0')
      } else {
        this.$set(this.form, 'num1', '0')
        this.$set(this.form, 'num2', '0')
      }
    },
    handleEditAll () {
      this.dialogFormVisible1 = true
    },
    handleCancel () {
      this.dialogFormVisible = false
      this.$message.info('操作已取消')
    },
    addDrug (form) {
      this.readonly = false
      this.dialogFormVisible = true
      this.form = {
        drugsPrice: '0',
        totalNum: '0',
        num1: '0',
        num2: '0'
      }
    },
    currentSel (sel) {
      saveWarehouse = sel.label
    },
    updateWarehouse (form1) {
      let ids = []
      this.multipleSelection.forEach(element => {
        ids.push(element.id)
      })
      this.$confirm('此操作将更改被选中的药品存放地点信息，是否继续？', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.$axios.post('/updateWarehouse', {
          ids: ids,
          warehouse: saveWarehouse,
          mnemonicCode: this.formInline.mnemonicCode
        }).then((res) => {
          this.$store.commit('repertory', res.data.drugs)
          allData = res.data.drugs
          this.tableData = res.data.drugs
          this.total = this.tableData.length
          // allData.splice(index, 1)
          // this.tableData = allData
          // this.total = allData.length
          this.tableChange()
          // 操作完成清除勾选框
          this.$refs.multipleTable.clearSelection()
          this.$message.success(res.data.message)
        }).catch(() => {
          this.$message.error('服务器表示不想理你！')
        })
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '操作已取消'
        })
      })
    },
    // 单个修改
    updateData (form) {
      if (this.readonly) {
        this.$refs.form.validate((valid) => {
          // 验证参数是否合法
          if (valid) { // 合法
            form.totalNum = parseInt(form.num1) + parseInt(form.num2)
            this.$axios
              .post('/updateRepertory', {
                id: parseInt(form.id),
                totalNum: form.totalNum,
                drugsPrice: form.drugsPrice,
                mnemonicCode: this.formInline.mnemonicCode,
                warehouses: [
                  {
                    warehouse: '配药房',
                    num: parseInt(form.num1)
                  },
                  {
                    warehouse: '储藏室',
                    num: parseInt(form.num2)
                  }
                ]
              })
              .then(res => {
                this.$store.commit('repertory', res.data.drugs)
                allData = res.data.drugs
                this.tableData = res.data.drugs
                this.total = this.tableData.length
                this.tableChange()
                this.$message.success(res.data.message)
              })
              .catch(failResponse => {
                this.$message.error('服务器表示不想理你！')
              })
          }
        })
      } else {
        let date = new Date()
        date = this.getNowFormatDate()
        this.$refs.form.validate((valid) => {
          if (valid) {
            form.totalNum = parseInt(form.num1) + parseInt(form.num2)
            this.$axios
              .post('/addRepertory', {
                drugsName: form.drugsName,
                drugsCode: form.drugsCode,
                drugsDosageID: form.drugsDosageID,
                drugsFormat: form.drugsFormat,
                mnemonicCode: form.mnemonicCode,
                drugsPrice: form.drugsPrice,
                drugsTypeID: form.drugsTypeID,
                drugsUnit: form.drugsUnit,
                creationDate: date,
                saveRequire: form.saveRequire,
                totalNum: form.totalNum,
                warehouses: [
                  {
                    warehouse: '配药房',
                    num: parseInt(form.num1)
                  },
                  {
                    warehouse: '储藏室',
                    num: parseInt(form.num2)
                  }
                ]
              })
              .then((res) => {
                this.$store.commit('repertory', res.data.drugs)
                allData = res.data.drugs
                this.tableData = res.data.drugs
                this.total = this.tableData.length
                this.tableChange()
                this.$message.success(res.data.message)
              })
              .catch(failResponse => {
                this.$message.error('服务器表示不想理你！')
              })
          }
        })
      }
    },
    // 单个删除
    handleDelete (index, row) {
      // console.log(row)
      // console.log(row.id)
      this.$confirm('此操作将永久删除该药品信息，是否继续？', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.$axios
          .post('/deleteRepertory', {
            id: row.id,
            mnemonicCode: this.formInline.mnemonicCode
          })
          .then(res => {
            this.$store.commit('repertory', res.data.drugs)
            allData = res.data.drugs
            this.tableData = res.data.drugs
            this.total = this.tableData.length
            // allData.splice(index, 1)
            // this.tableData = allData
            // this.total = allData.length
            this.tableChange()
            this.$message.success(res.data.message)
          })
          .catch(failResponse => {
            this.$message.error('服务器表示不想理你！')
          })
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消删除'
        })
      })
    },
    // 批量删除
    handleDeleteAll (multipleSelection) {
      let ids = []
      multipleSelection.forEach(element => {
        ids.push(element.id)
      })
      this.$confirm('此操作将永久删除被选中的药品信息，是否继续？', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.$axios.post('/deleteOptions', {
          ids: ids,
          mnemonicCode: this.formInline.mnemonicCode
        }).then((res) => {
          this.$store.commit('repertory', res.data.drugs)
          allData = res.data.drugs
          this.tableData = res.data.drugs
          this.total = this.tableData.length
          // allData.splice(index, 1)
          // this.tableData = allData
          // this.total = allData.length
          this.tableChange()
          // 操作完成清除勾选框
          this.$refs.multipleTable.clearSelection()
          this.$message.success(res.data.message)
        }).catch(() => {
          this.$message.error('服务器表示不想理你！')
        })
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消删除'
        })
      })
    },
    handleImport () {
    },
    export2Excel (tableData, multipleSelection) {
      let tableDatas = []
      // console.log(multipleSelection)
      if (multipleSelection.length > 0) {
        tableDatas = multipleSelection
      } else {
        tableDatas = tableData
      }
      console.log(tableDatas)
      require.ensure([], () => {
        // eslint-disable-next-line camelcase
        const {export_json_to_excel} = require('@/excel/Export2Excel')
        const tHeader = ['id', '药品编号', '药品名称', '药品助记码', '药品规格', '药品单位', '药品单价', '药品剂型', '药品类型', '药品总数量', '保存条件']
        // 上面设置Excel的表格第一行的标题
        const filterVal = ['id', 'drugsCode', 'drugsName', 'mnemonicCode', 'drugsFormat', 'drugsUnit', 'drugsPrice', 'drugsDosageID', 'drugsTypeID', 'totalNum', 'saveRequire']
        const list = tableDatas
        const data = this.formatJson(filterVal, list)
        export_json_to_excel(tHeader, data, '药品单明细')
      })
    },
    formatJson (filterVal, jsonData) {
      return jsonData.map(v => filterVal.map(j => v[j]))
    },
    painting () {
      this.$axios.post('/getChart')
        .then(res => {
          this.opinionData = res.data.result1
          this.opinionData1 = res.data.result2
          this.dialogFormVisible2 = true
          this.$nextTick(function () {
            this.drawPie('pieReport')
            this.drawPie1('pieReport1')
          })
          console.log(res.data)
        })
        .catch(() => {
          this.$message('操作失败')
        })
    },
    drawPie (id) {
      this.charts = echarts.init(document.getElementById(id))
      this.charts.setOption({
        title: {
          text: '储藏室'
        },
        tooltip: {
          trigger: 'item'
        },
        legend: {
          orient: 'vertical',
          x: '60px',
          data: this.opinion
        },
        series: [
          {
            name: '储藏室',
            type: 'pie',
            radius: ['20%', '70%'],
            avoidLabelOverlap: false,
            label: {
              normal: {
                show: false,
                position: 'center'
              },
              emphasis: {
                show: true,
                textStyle: {
                  fontSize: '30',
                  fontWeight: 'blod'
                }
              }
            },
            labelLine: {
              normal: {
                show: false
              }
            },
            data: this.opinionData
          }
        ]
      })
    },
    drawPie1 (id) {
      this.charts = echarts.init(document.getElementById(id))
      this.charts.setOption({
        title: {
          text: '配药房'
        },
        tooltip: {
          trigger: 'item'
        },
        legend: {
          orient: 'vertical',
          x: '60px',
          data: this.opinion
        },
        series: [
          {
            name: '配药房',
            type: 'pie',
            radius: ['20%', '70%'],
            avoidLabelOverlap: false,
            label: {
              normal: {
                show: false,
                position: 'center'
              },
              emphasis: {
                show: true,
                textStyle: {
                  fontSize: '30',
                  fontWeight: 'blod'
                }
              }
            },
            labelLine: {
              normal: {
                show: false
              }
            },
            data: this.opinionData1
          }
        ]
      })
    },
    showPrintData (multipleSelection) {
      this.printDialogVisible = true
    }
  },
  computed: {
    repertory () {
      return this.$store.state.repertory
    },
    getDate () {
      return this.getNowFormatDate()
    }
  },
  created () {
    if (sessionStorage.getItem('repertory')) {
      allData = this.$store.state.repertory
      this.tableData = this.$store.state.repertory
      this.total = this.tableData.length
      this.pageSize = this.$store.state.repTable.pageSize
      this.currentPage = this.$store.state.repTable.currentPage
      this.tableChange()
    }
  }
}
</script>
<style>
  #printTest table{
    font-family:"宋体";
    border-collapse:collapse;
    width:99.5%;
  }
  #printTest table thead th{
    height: 40px;
    font-size: 13px;
    /* width: 10px; */
    text-align: center;
    border: 1px solid black;
  }
  #printTest table tbody tr{
    font-size:13px;
    border: 1px solid black;
    height: 40px;
    text-align: center;
  }
  #printTest table tbody td{
    font-size: 13px;
    text-align: center;
    border: 1px solid black;
  }
</style>
