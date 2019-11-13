<!--作者：徐奥飞-->
<!--时间：2019-11-4 8：31-->
<!--版本：1.0-->
<template>
  <div>
    <el-card class="login-form-layout">
      <el-form
        autocomplete="off"
        :model="loginForm"
        :rules="rules"
        status-icon
        ref="loginForm"
        label-position="left"
      >
        <div style="text-align: center">
          <svg-icon icon-class="platform" style="width: 100px;height: 100px;color: #409eff"></svg-icon>
        </div>
        <h2 class="login-title color-main">东软云HIS医疗管理系统</h2>
        <h3 class="login-title color-main">门诊药房工作站模块</h3>
        <el-form-item prop="username">
          <el-input
            name="username"
            type="text"
            v-model="loginForm.username"
            autocomplete="off"
            placeholder="请输入用户名"
          >
          <span slot="prefix">
            <svg-icon icon-class="username" class="color-main"></svg-icon>
          </span>
          </el-input>
        </el-form-item>
        <el-form-item prop="password">
          <el-input
            name="password"
            :type="pwdType"
            @keyup.enter.native="login"
            v-model="loginForm.password"
            autocomplete="off"
            placeholder="请输入密码"
          >
          <span slot="prefix">
            <svg-icon icon-class="password" class="color-main"></svg-icon>
          </span>
            <span slot="suffix" @click="showPwd">
            <svg-icon icon-class="eye"></svg-icon>
          </span>
          </el-input>
        </el-form-item>
        <el-form-item style="margin-bottom: 60px">
          <el-button
            style="width: 100%"
            type="primary"
            :loading="loading"
            @click.native.prevent="login"
          >登录</el-button>
        </el-form-item>
      </el-form>
    </el-card>
  </div>
</template>

<script>
export default {
  name: 'Login',
  data () {
    const checkUsername = (rule, value, callback) => {
      if (value === '') {
        return callback(new Error('请输入用户名'))
      } else {
        callback()
      }
    }
    const checkPassword = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请输入密码'))
      } else if (value.length < 3) {
        callback(new Error('密码不能少于3位'))
      } else {
        callback()
      }
    }
    return {
      loginForm: {
        username: '',
        password: ''
      },
      rules: {
        username: [
          { validator: checkUsername, trigger: 'blur' }
        ],
        password: [
          { validator: checkPassword, trigger: 'blur' }
        ]
      },
      loading: false,
      pwdType: 'password'
    }
  },
  methods: {
    showPwd () {
      if (this.pwdType === 'password') {
        this.pwdType = ''
      } else {
        this.pwdType = 'password'
      }
    },
    login () {
      this.$refs.loginForm.validate((valid) => {
        if (valid) {
          this.loading = true
          this.$axios
            .post('/login', {
              username: this.loginForm.username,
              password: this.loginForm.password
            })
            .then(successResponse => {
              this.loading = false
              if (successResponse.data.code === 200) {
                this.$router.push('/index')
              } else {
                this.$alert(successResponse.data.message, '提示', {
                  confirmButtonText: '确定',
                  callback: action => {
                    this.$refs.loginForm.resetFields()
                    this.pwdType = 'password'
                  }
                })
              }
            })
            .catch(failResponse => {
              this.loading = false
            })
        } else {
          // eslint-disable-next-line no-console
          console.log('参数验证不合法！')
          return false
        }
      })
    }
  }
}
</script>

<style>
  body{
    width: 100%;
    height: 100%;
    position: relative;
    background-color: white;
  }
</style>
<style scoped>
  .login-form-layout {
    position: absolute;
    left: 0;
    right: 0;
    width: 360px;
    margin: 10px auto;
  }

  .login-title {
    text-align: center;
  }

  .color-main {
    color: #409eff;
  }
</style>
