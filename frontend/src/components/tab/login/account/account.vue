<template>
  <div class="login-account">
    <el-input
      v-model="userInfo.account"
      class="login-input"
      placeholder="请输入账号"
      size="large"
    />
    <el-input
      v-model="userInfo.password"
      class="login-input"
      placeholder="请输入密码"
      size="large"
      type="password"
      show-password
    />
  </div>
</template>

<script lang="ts" setup>
import { reactive } from 'vue'

const userInfo = reactive({
  account: '',
  password: '',
})
</script>

<style lang="scss" scoped>
.login-account {
  width: 300px;
  margin: 0 auto;
  .login-input {
    width: 300px;
    height: 65px;
    padding-bottom: 20px;
    font-size: 15px;
    border-radius: 15px;
  }
}
</style>
