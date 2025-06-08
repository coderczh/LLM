<template>
  <div class="login-account">
    <el-input
      v-model="accountInfo.accountNo"
      class="login-input"
      placeholder="请输入账号"
      size="large"
    />
    <el-input
      v-model="accountInfo.password"
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

const accountInfo = reactive({
  accountNo: '',
  password: '',
})

defineExpose({
  accountInfo,
})
</script>

<style lang="scss" scoped>
.login-account {
  width: 330px;
  margin: 0 auto;
  .login-input {
    height: 45px;
    margin-bottom: 20px;
    font-size: 15px;
    border-radius: 15px;
  }
}
</style>
