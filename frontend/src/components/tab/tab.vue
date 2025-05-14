<template>
  <div class="tab">
    <div class="logo">
      <div class="img">
        <img src="@/assets/img/logo.svg" style="width: 100%; height: 100%" />
      </div>
      <div class="text">百全</div>
    </div>
    <feature />
    <div class="bottom" @click="openDialog">
      <el-button color="rgba(0, 0, 0, 0.05)" size="large" circle>登录</el-button>
    </div>
  </div>
  <login @closeDialog="closeDialog" v-if="showDialog" />
</template>

<script lang="ts" setup>
import { ref } from 'vue'
import feature from './feature/feature.vue'
import login from './login/login.vue'

const showDialog = ref(false)
const closeDialog = () => {
  showDialog.value = false
}
const openDialog = () => {
  showDialog.value = true
}
</script>

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

  .bottom {
    position: absolute;
    bottom: 20px;
    left: 50%;
    transform: translate(-50%, 0);
  }
}
</style>
