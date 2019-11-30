<!--作者：徐奥飞-->
<!--时间：2019-11-5 14:03-->
<!--描述：创建主页静态组件-->
<template>
  <div>
    <el-container class="home-container">
      <el-header class="home-header">
        <span class="home-title">门诊药房工作站</span>
        <div style="display: flex; align-items: center; margin-right: 7px">
          <el-dropdown @command="handleCommand">
            <span
              class="home-userInfo"
              style="display: flex; align-items: center">
              {{ user.realName }}
              <svg-icon
                icon-class="user"
                style="width: 40px; height: 40px; margin-right: 5px; margin-left: 5px; border-radius: 40px">
              </svg-icon>
            </span>
            <el-dropdown-menu slot="dropdown">
              <el-dropdown-item>个人中心</el-dropdown-item>
              <el-dropdown-item>设置</el-dropdown-item>
              <el-dropdown-item command="logout" divided>注销</el-dropdown-item>
            </el-dropdown-menu>
          </el-dropdown>
        </div>
      </el-header>
      <el-container>
        <el-aside width="200px" class="home-aside">
          <div style="display: flex; justify-content: flex-start; width: 200px; text-align: left">
            <el-menu
              :default-active="$route.path"
              style="background: #eae2ec; width: 200px"
              unique-opened
              router>
                <el-submenu index="query">
                  <template slot="title">
                    <svg-icon icon-class="query" class="svgInfo"></svg-icon>
                    <span class="title">查询统计</span>
                  </template>
                  <el-menu-item-group>
                      <el-menu-item index="/query/prescription_query">处方查询</el-menu-item>
                    <el-menu-item index="/query/drugs_query">药品信息查询</el-menu-item>
                  </el-menu-item-group>
                </el-submenu>
              <el-submenu index="repertory">
                <template slot="title">
                  <svg-icon icon-class="repertoryMag" class="svgInfo"></svg-icon>
                  <span class="title">库存管理</span>
                </template>
                <el-menu-item index="/repertory/manage">库存管理</el-menu-item>
                <el-menu-item index="/repertory/stockOut">缺货信息</el-menu-item>
                <el-menu-item index="/repertory/stock">进货信息</el-menu-item>
              </el-submenu>
              <el-submenu index="service">
                <template slot="title">
                  <svg-icon icon-class="service" class="svgInfo"></svg-icon>
                  <span class="title">门诊业务</span>
                </template>
                <el-menu-item index="/service/sendDrugs">配发药管理</el-menu-item>
                <el-menu-item index="/service/returnDrugs">药品退回审核</el-menu-item>
                <el-menu-item index="/service/returnPres">退回处方信息</el-menu-item>
              </el-submenu>
              <el-submenu index="catalog">
                <template slot="title">
                  <svg-icon icon-class="catalog" class="svgInfo"></svg-icon>
                  <span class="title">目录管理</span>
                </template>
                <el-menu-item index="/catalog/manage">目录管理</el-menu-item>
              </el-submenu>
            </el-menu>
          </div>
        </el-aside>
        <el-main class="home-main">
          <el-breadcrumb separator-class="el-icon-arrow-right">
            <el-breadcrumb-item :to="{ path: '/home'} ">首页</el-breadcrumb-item>
            <el-breadcrumb-item v-text="this.$router.currentRoute.name"></el-breadcrumb-item>
            <el-divider></el-divider>
          </el-breadcrumb>
          <keep-alive>
            <router-view v-if="this.$route.meta.keepAlive"></router-view>
          </keep-alive>
          <router-view v-if="!this.$route.meta.keepAlive"></router-view>
        </el-main>
      </el-container>
    </el-container>
  </div>
</template>
<script>
export default {
  methods: {
    handleCommand (cmd) {
      var _this = this
      if (cmd === 'logout') {
        this.$confirm('注销登录，是否继续？', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        })
          .then(() => {
            _this.$store.commit('logout')
            _this.$router.push('/login')
          })
          .catch(() => {
            _this.$message({
              type: 'info',
              message: '操作已取消'
            })
          })
      }
    }
  },
  data () {
    return {
      isDot: false
    }
  },
  computed: {
    user () {
      return this.$store.state.user
    }
  }
}
</script>
<style>
  .home-container{
    height: 100%;
    position: absolute;
    top: 0;  /*去掉页头*/
    left: 0;
    width: 100%;
  }
  .home-header{
    background-color: #545c64;
    color: #ffffff;
    text-align: center;
    display: flex;
    align-items: center;
    justify-content: space-between;
    box-sizing: content-box;
    padding: 0;
  }
  .home-title{
    color: #ffffff;
    font-size: 22px;
    display: inline;
    margin-left: 20px;
  }
  .home-userInfo{
    color: #ffffff;
    cursor: pointer;
  }
  .home-aside {
    background-color: #eae2ec;
  }
  .home-main {
    background-color: white;
  }
  .svgInfo {
    color: #657d80;
    margin-left: 17px;
    margin-bottom: -3px
  }
  .title {
    color: #657d80;
    font-size: 20px;
    margin-left: 10px;
  }
  .el-menu-item.is-active {
    color: red !important;
  }
</style>
