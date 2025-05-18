<template>
  <div class="login-account">
    <el-input
      v-model="phoneInfo.phoneNo"
      class="login-input"
      placeholder="请输入手机号"
      size="large"
    >
      <template #prefix>
        <span style="color: #343434">+86 <el-divider direction="vertical" /> </span>
      </template>
    </el-input>
    <el-input
      v-model="phoneInfo.verifyCode"
      class="login-input"
      placeholder="请输入验证码"
      size="large"
    >
      <template #suffix>
        <span style="font-size: 13px" @click="getVerifyCodeClick"
          ><el-divider direction="vertical" /> 获取验证码
        </span>
      </template>
    </el-input>
  </div>
</template>

<script lang="ts" setup>
import { reactive } from 'vue'

const phoneInfo = reactive({
  phoneNo: '',
  verifyCode: '',
})

const getVerifyCodeClick = () => {
  console.log('===')
}

defineExpose({
  phoneInfo,
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
