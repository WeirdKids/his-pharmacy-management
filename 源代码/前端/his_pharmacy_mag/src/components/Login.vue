<!--
    作者：徐奥飞
    时间：2019-11-4 15:00
    描述：创建登录组件Login.vue
-->
<template>
<!--  <div id="login">-->
<!--    <h1>{{ msg }}</h1>-->
<!--  </div>-->
  <el-form
    ref="AccountForm"
    :model="account"
    :rules="rules"
    label-position="left"
    label-width="0px"
    class="demo-ruleForm login-container"
  >
    <h3 class="title">药房管理系统——登陆首页</h3>
    <el-form-item prop="username">
      <el-input type="text" v-model="account.username" auto-complete="off" aria-placeholder="账号"></el-input>
    </el-form-item>
    <el-form-item prop="pwd">
      <el-input type="password" v-model="account.pwd" auto-complete="off" aria-placeholder="密码"></el-input>
    </el-form-item>
    <el-checkbox v-model="checked" checked class="remember">记住密码</el-checkbox>
    <el-form-item style="width: 100%;">
      <el-button type="primary" style="width: 100%;" @click.native.prevent="handleLogin" :loading="logining">登录</el-button>
    </el-form-item>
  </el-form>
</template>

<script>
// 引入api.js 好调用里面的接口
import {requestLogin} from '../api/api'
export default {
  name: 'login',
  data () {
    return {
      logining: false,
      account: {
        username: '',
        pwd: ''
      },
      rules: {
        username: [{
          required: true,
          message: '请输入账号',
          trigger: 'blur'
        }],
        pwd: {
          required: true,
          message: '请输入密码',
          trigger: 'blur'
        }
      },
      checked: true
    }
  },
  methods: {
    handleLogin () {
      this.$refs.AccountForm.validate((valid) => {
        if (valid) {
          // 验证通过，可以提交
          this.logining = true
          // 将提交的数据进行封装
          var loginParams = {cUsername: this.account.username, cPwd: this.account.pwd}
          // 调用函数，传递参数，获取结果
          requestLogin(loginParams).then(data => {
            this.logining = false
            if (data.code === '200') {
              // 登录成功
              sessionStorage.setItem('access-token', data.token)
              // 用vue路由跳转到后台主界面
              this.$router.push({path: '/home'})
            } else {
              this.$message({
                message: data.msg,
                type: 'error'
              })
            }
          })
        } else {
          console.log('error submit')
          return false
        }
      })
    }
  }
}
</script>

<style>
  body {
    background: #DFE9FB;
  }

  .login-container {
    width: 350px;
    margin-left: 35%;
  }
</style>
