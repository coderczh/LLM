<template>
  <div class="tab">
    <div class="logo">
      <div class="img">
        <img src="@/assets/img/logo.svg" style="width: 100%; height: 100%" />
      </div>
      <div class="text">百全</div>
    </div>
    <div class="feature">
      <el-button color="rgba(0, 0, 0, 0.05)" size="large" circle>
        <el-icon size="22"><Comment /></el-icon>
      </el-button>
      <div class="text">对话</div>
      <el-button color="rgba(0, 0, 0, 0.05)" size="large" circle style="margin-top: 30px">
        <el-icon size="22"><HelpFilled /></el-icon>
      </el-button>
      <div class="text">收藏</div>
    </div>
    <div class="bottom">
      <el-button color="rgba(0, 0, 0, 0.1)" size="large" circle>登录</el-button>
    </div>
  </div>
</template>

<script lang="ts" setup></script>

<style lang="scss" scoped>
.tab {
  height: 100vh;
  position: relative;
  .logo {
    padding-top: 35px;
    width: 60px;
    height: 60px;
    margin: 0 auto;
    .img {
      width: 35px;
      height: 35px;
      margin: 0 auto;
    }
    .text {
      padding-top: 8px;
      color: #fff;
      font-size: 18px;
      line-height: 1.2em;
      text-align: center;
    }
  }

  .feature {
    padding-top: 120px;
    text-align: center;
    .text {
      padding-top: 8px;
      text-align: center;
      color: #fff;
      font-size: 13px;
      line-height: 1.2em;
      text-align: center;
    }
  }

  .bottom {
    position: absolute;
    bottom: 20px;
    left: 50%;
    transform: translate(-50%, 0);
  }
}
</style>
