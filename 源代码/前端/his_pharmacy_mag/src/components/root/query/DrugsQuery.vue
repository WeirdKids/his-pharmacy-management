<template>
  <div>
   <el-select v-model="value4" clearable placeholder="请选择">
     <el-option
       v-for="item in options"
       :key="item.value"
       :label="item.label"
       :value="item.value">
     </el-option>
   </el-select>
 <template>
       <el-table
         ref="multipleTable"
         :data="tableData"
         tooltip-effect="dark"
         max-height="500"
         style="width: 100%; margin-bottom: 10px; margin-top: 5px"
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
  export default {
    data() {
      return {
        options: [{
          value: '选项1',
          label: '通过药品名称查询'
        }, {
          value: '选项2',
          label: '通过助记码查询'
        }],
        value4: ''
      }
    }
  }
</script>
