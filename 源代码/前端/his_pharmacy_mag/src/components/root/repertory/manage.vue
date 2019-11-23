<template>
  <div>
    <el-row style="margin-top: -15px">
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
        max-height="1000"
        style="width: 100%; margin-bottom: 10px; margin-top: 5px"
        @selection-change="handleSelectionChange">
        <el-table-column
          type="selection" fixed>
        </el-table-column>
        <el-table-column
          prop="DrugsCode"
          label="药品编码">
        </el-table-column>
        <el-table-column
          prop="DrugsName"
          label="药品名称">
        </el-table-column>
        <el-table-column
          prop="DrugsFormat"
          label="药品规格">
        </el-table-column>
        <el-table-column
          prop="DrugsUnit"
          label="药品单位">
        </el-table-column>
        <el-table-column
          prop="DrugsPrice"
          label="药品单价">
        </el-table-column>
        <el-table-column
          prop="DrugsDosageID"
          label="药品剂型">
        </el-table-column>
        <el-table-column
          prop="DrugsTypeID"
          label="药品类型">
        </el-table-column>
        <el-table-column
          prop="DrugsNum"
          label="药品数量">
        </el-table-column>
        <el-table-column
          prop="SaveRequire"
          label="保存条件">
        </el-table-column>
        <el-table-column
          prop="Warehouse"
          label="存放位置">
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
          :page-sizes="[100, 200, 300, 400]"
          :page-size="100"
          layout="total, sizes, prev, pager, next, jumper"
          :total="total">
        </el-pagination>
      </div>
    </template>
  </div>
</template>

<script>
export default {
  name: 'manage',
  data () {
    return {
      loading: false,
      tableData: [],
      total: 0
    }
  },
  methods: {
    queryAll () {
      this.loading = true
    },
    handleSelectionChange (val) {
      this.multipleSelection = val
    },
    handleSizeChange (val) {
      console.log(`每页 ${val} 条`)
    },
    handleCurrentChange (val) {
      console.log(`当前页: ${val}`)
    },
    handleEdit (index, row) {
      console.log(index, row)
    },
    handleDelete (index, row) {
      console.log(index, row)
    }
  }
}
</script>
<style scoped>
</style>
