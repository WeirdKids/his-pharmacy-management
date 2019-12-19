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
              type="text"
              v-model="formInline.prescriptionCode"
              placeholder="处方单号">
              <span slot="prefix">
                <svg-icon icon-class="prescriptionCode" style="color: #409eff;"></svg-icon>
              </span>
            </el-input>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item>
            <el-button
              type="primary"
              :loading="loading1"
              @click="onSubmit"
              round
              icon="el-icon-search">查询
            </el-button>
          </el-form-item>
<!--          <el-form-item>-->
<!--            <input name="upload"-->
<!--                   type="file"-->
<!--                   @change="importfile(this)"-->
<!--                   accept=".csv, application/vnd.openxmlformats-officedocument.spreadsheetml.sheet, application/vnd.ms-excel" />-->
<!--            <el-button-->
<!--              type="primary"-->
<!--              round-->
<!--              :loading="loading"-->
<!--              icon="el-icon-download"-->
<!--              @click.native.prevent="updateByFile()">导入处方单信息-->
<!--            </el-button>-->
<!--          </el-form-item>-->
        </el-col>
        <el-col :span="10">
          <el-form-item>
            <el-button
              type="primary"
              round
              :loading="loading"
              icon="el-icon-download"
              @click.native.prevent="export2Excel(tableData,multipleSelection)">导出处方单信息
            </el-button>
          </el-form-item>
          <el-form-item>
            <el-button
              type="primary"
              round
              :loading="loading"
              icon="el-icon-search"
              @click.native.prevent="queryAll">显示全部处方单信息
            </el-button>
          </el-form-item>
          <el-form-item>
            <el-button
              type="primary"
              @click="addPres(form)"
              round
              align="center"
              icon="el-icon-plus">添加处方单</el-button>
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
              icon="el-icon-printer"
              @click="showPrintData(multipleSelection)"
              :disabled="this.multipleSelection.length === 0">打印
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
        <el-table-column type="selection" fixed="left" width="50px" v-model="multipleSelection"></el-table-column>
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
      <el-dialog
        title="处方信息"
        :visible.sync="dialogFormVisible">
        <el-form
          :model="form"
          ref="form"
          label-width="100px"
          status-icon
          :rules="rules1">
          <el-row>
            <el-col :span="12">
              <el-form-item label="处方编码" prop="prescriptionCode">
                <el-input v-model="form.prescriptionCode" autocomplete="off" :readonly="readonly"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item label="总疗程" prop="totalStage">
                <el-input v-model="form.totalStage" autocomplete="off" :readonly="readonly"></el-input>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="12">
              <el-form-item label="开立医生" prop="charger">
                <el-input v-model="form.charger" autocomplete="off" :readonly="readonly"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item label="医生编码" prop="doctorID">
                <el-input v-model="form.doctorID" autocomplete="off" :readonly="readonly"></el-input>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="12">
              <el-form-item label="药品编码" prop="drugId">
                <el-input v-model="form.drugId" autocomplete="off" :readonly="readonly"></el-input>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="12">
            <el-form-item label="药品数量（单次疗程）" prop="num1">
              <el-input v-model="form.num1" autocomplete="off" :readonly="readonly"></el-input>
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
        title="处方信息单"
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
          <div style="font-size: 22px;padding-bottom:10px;font-weight:800;font-family:宋体; text-align:center">处方信息</div>
          <span style="font-size:16px;font-family:宋体;float:right;">打印时间：{{ getDate }}</span>
          <table>
            <thead>
            <th>处方单号</th>
            <th>开立医生</th>
            <th>开立时间</th>
            <th>状态</th>
            <th>总疗程</th>
            <th>当前疗程</th>
            <th>总数量</th>
            <th>药品名称</th>
            </thead>
            <tbody>
            <tr v-for="item in multipleSelection" :key="item.id">
              <td>{{ item.prescriptionCode }}</td>
              <td>{{ item.charger}}</td>
              <td>{{ item.chargeTime }}</td>
              <td>{{ item.statue }}</td>
              <td>{{ item.totalStage }}</td>
              <td>{{ item.currentStage }}</td>
              <td>{{ item.num }}</td>
              <td>{{ item.drugName }}</td>
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
let allData
var outDatas
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
        const re = /^([1-9][0-9]*)$/
        const reCheck = re.test(value)
        if (!reCheck) {
          return callback(new Error('请输入正整数'))
        } else {
          callback()
        }
      }, 300)
    }
    return {
      loading: false,
      loading1: false,
      total: 0,
      contentTxt: '',
      dialogFormVisible: false,
      dialogFormVisible1: false,
      printDialogVisible: false,
      readonly: true,
      currentPage: 1,
      pageSize: 5,
      formLabelWidth: '220px',
      form: {},
      formInline: {
        prescriptionCode: ''
      },
      rules: {
        prescriptionCode: [
          {validator: checkPrescriptionCode, trigger: 'blur'}
        ]
      },
      rules1: {
        prescriptionCode: [
          {validator: NotEmpty, trigger: 'blur'}
        ],
        doctorID: [
          {validator: NotEmpty, trigger: 'blur'}
        ],
        charger: [
          {trigger: 'blur'}
        ],
        totalStage: [
          {type: 'number', validator: isInteger, trigger: 'change'}
        ],
        drugId: [
          {type: 'number', validator: isInteger, trigger: 'change'}
        ],
        num1: [
          {type: 'number', validator: isInteger, trigger: 'change'}
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
      console.log(val)
    },
    showPrintData (multipleSelection) {
      this.printDialogVisible = true
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
          presIds: presIds
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
    queryAll () {
      this.loading = true
      let _this = this
      this.$axios.post('/query/prescription_query/queryAll')
        .then(res => {
          this.loading = false
          _this.$store.commit('prescription', res.data.prescriptions)
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
    addPres (form) {
      this.readonly = false
      this.dialogFormVisible = true
      this.form = {
        num: '0'
      }
    },
    handleCancel () {
      this.dialogFormVisible = false
      this.$message.info('操作已取消')
    },
    updateData (form) {
      let date = new Date()
      date = this.getNowFormatDate()
      this.$refs.form.validate((valid) => {
        if (valid) {
          form.num = parseInt(form.num1) * parseInt(form.totalStage)
          this.$axios.post('service/managePres/addPres', {
            prescriptionCode: form.prescriptionCode,
            doctorID: form.doctorID,
            charger: form.charger,
            chargeTime: date,
            totalStage: form.totalStage,
            drugId: form.drugId,
            num: form.num
          })
            .then(res => {
              this.loading = false
              this.$store.commit('repertory', res.data.prescriptions)
              allData = res.data.prescriptions
              this.tableData = res.data.prescriptions
              this.total = this.tableData.length
              this.tableChange()
              this.$message.success(res.data.message)
            })
            .catch(failResponse => {
              this.loading = false
              this.$message.error('服务器被干掉了！')
            })
        } else (this.$message.error('数据验证不合法！'))
      })
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
    },
    export2Excel (tableData, multipleSelection) {
      let tableDatas = []
      console.log(multipleSelection)
      if (multipleSelection.length > 0) {
        tableDatas = multipleSelection
      } else {
        tableDatas = tableData
      }
      console.log(tableDatas)
      require.ensure([], () => {
        // eslint-disable-next-line camelcase
        const {export_json_to_excel} = require('@/excel/Export2Excel')
        const tHeader = ['行号', '处方编号', '开立医生编号', '开立医生', '开立时间', '状态', '总疗程', '当前疗程', '发药总量', '已发数量', '药品名称', '药品编号']
        // 上面设置Excel的表格第一行的标题
        const filterVal = ['id', 'prescriptionCode', 'doctorId', 'charger', 'chargeTime', 'statue', 'totalStage', 'currentStage', 'num', 'sentNum', 'drugName', 'drugId']
        const list = tableDatas
        const data = this.formatJson(filterVal, list)
        export_json_to_excel(tHeader, data, '处方单明细')
        this.$message.success('已导出处方单')
      })
    },
    formatJson (filterVal, jsonData) {
      return jsonData.map(v => filterVal.map(j => v[j]))
    },
    importfile (obj) {
      // eslint-disable-next-line no-unused-vars
      let _this = this
      console.log('xxxxxxxxxxxxxxxxxxxxxxxxxxx')
      // eslint-disable-next-line no-unused-vars
      let inputDOM = this.$refs.inputer// 通过DOM取文件数据
      this.file = event.currentTarget.files[0]
      var rABS = false // 是否将文件读取为二进制字符串
      var f = this.file
      var reader = new FileReader()
      // (!FileReader.prototype.readAsBinaryString) {
      FileReader.prototype.readAsBinaryString = function (f) {
        var binary = ''
        var rABS = false // 是否将文件读取为二进制字符串
        // eslint-disable-next-line no-unused-vars
        var pt = this
        var wb // 读取完成的数据
        var outdata
        var reader = new FileReader()
        reader.onload = function (e) {
          var bytes = new Uint8Array(reader.result)
          var length = bytes.byteLength
          for (var i = 0; i < length; i++) {
            binary += String.fromCharCode(bytes[i])
          }
          var XLSX = require('xlsx')
          if (rABS) {
            wb = XLSX.read(btoa(binary), {// 手动转化
              type: 'base64'
            })
          } else {
            wb = XLSX.read(binary, {
              type: 'binary'
            })
          }
          outdata = XLSX.utils.sheet_to_json(wb.Sheets[wb.SheetNames[0]])// outdata就是你想要的东
          outDatas = outdata
          console.log(outDatas)
        }
        reader.readAsArrayBuffer(f)
      }
      if (rABS) {
        reader.readAsArrayBuffer(f)
      } else {
        reader.readAsBinaryString(f)
      }
    },
    updateByFile () {
      this.loading = true
      this.$axios.post('service/managePres/addPresByFile', {
        prescripitonCodes: outDatas.prescriptionCode,
        doctorIDs: outDatas.doctorID,
        chargers: outDatas.charger,
        chargeTimes: this.getNowFormatDate(),
        statues: outDatas.statue,
        totalStages: outDatas.totalStage,
        currentStages: outDatas.currentStage,
        nums: outDatas.num,
        sentNums: outDatas.sentNum,
        drugNames: outDatas.drugName,
        drugIds: outDatas.drugId
      }).then(res => {
        this.loading = false
        this.$store.commit('repertory', res.data.prescriptions)
        allData = res.data.prescriptions
        this.tableData = res.data.prescriptions
        this.total = this.tableData.length
        this.tableChange()
        this.$message.success(res.data.message)
      })
        .catch(failResponse => {
          this.loading = false
          this.$message.error('服务器被干掉了！')
        })
    }
    // activated () {
    //   this.queryAll()
    // }
  },
  computed: {
    prescriptions () {
      return this.$store.state.prescriptions
    },
    getDate () {
      return this.getNowFormatDate()
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
