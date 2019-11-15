<!--作者：徐奥飞-->
<!--时间：2019-11-4 8：31-->
<!--版本：1.0-->
<template>
  <div class="index">
    <div id="index_pc_bj">
      <el-form
        class="login-container"
        autocomplete="off"
        :model="loginForm"
        :rules="rules"
        status-icon
        ref="loginForm"
        label-position="left"
        label-width="0px"
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
            <svg-icon icon-class="username" class="color-main1"></svg-icon>
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
            <svg-icon icon-class="password" class="color-main1"></svg-icon>
          </span>
            <span slot="suffix" @click="showPwd">
            <svg-icon icon-class="eye" class="color-main1"></svg-icon>
          </span>
          </el-input>
        </el-form-item>
        <el-form-item style="margin-bottom: 60px">
          <el-button
            style="width: 100%; background-color: #505458; border: none"
            type="primary"
            :loading="loading"
            @click.native.prevent="login"
          >登录</el-button>
        </el-form-item>
      </el-form>
    </div>
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
      var _this = this
      console.log(this.$store.state)
      this.$refs.loginForm.validate((valid) => {
        // 验证参数是否合法
        if (valid) { // 合法
          this.loading = true
          // 像后端发送数据
          this.$axios
            .post('/login', {
              username: this.loginForm.username,
              password: this.loginForm.password
            })
            // 收到后端返回的成功代码
            .then(successResponse => {
              this.loading = false
              if (successResponse.data.code === 200) {
                // 触发store中的login()方法，把loginForm对象传递给 store 中的 user 对象
                // var data = this.loginForm
                _this.$store.commit('login', successResponse.data.user)
                // 获取登录前页面的路径并跳转，如果该路径不存在，则跳转到首页
                var path = this.$route.query.redirect
                this.$router.replace({path: path === '/' || path === undefined ? '/home' : path})
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

<style scoped>
  .index {
    width: 100%;
    height: 100%;
    position: absolute;
    top: 0;
    bottom: 0;
    left: 0;
    text-align: center;
  }
  #index_pc_bj {
    width:100%;
    height:100%;
    background-size:cover;
    overflow: hidden;
    background-image: url(../assets/login.jpg);
    background-position:center center;
    box-shadow: 0 0px 3px rgba(0,0,0,.5);
    text-align: center;
  }
  .login-container {
    border-radius: 15px;
    background-clip: padding-box;
    margin: 90px auto;
    width: 350px;
    padding: 35px 35px 15px 35px;
    background: #fff;
    border: 1px solid #eaeaea;
    box-shadow: 0 0 25px #cac6c6;
  }
  .login-title {
    /*margin: 0px auto 40px auto;*/
    text-align: center;
    color: #505458;
  }

  .color-main {
    color: #505458;
  }
  .color-main1 {
    color: #409eff;
  }
</style>
