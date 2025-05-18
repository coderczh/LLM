<template>
  <div class="login-account">
    <el-input
      v-model="userInfo.accountNo"
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
  accountNo: '',
  password: '',
})

defineExpose({
  userInfo,
})
</script>

<style lang="scss" scoped>
.login-account {
  width: 300px;
  margin: 0 auto;
  .login-input {
    height: 45px;
    margin-bottom: 20px;
    font-size: 15px;
    border-radius: 15px;
  }
}
</style>
