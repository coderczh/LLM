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
        <span style="font-size: 13px; cursor: pointer" @click="getVerifyCodeClick"
          ><el-divider direction="vertical" />
          <span v-if="countDown === 0">获取验证码</span>
          <span v-else>{{ countDown }}秒后重发</span>
        </span>
      </template>
    </el-input>
  </div>
</template>

<script lang="ts" setup>
import { SUCCESS_CODE, verifyPhoneNo } from '@/assets/common/constant'
import { getVerifyCodeReq } from '@/service/core/tab/tab'
import { ElMessage } from 'element-plus'
import { reactive, ref } from 'vue'

const phoneInfo = reactive({
  phoneNo: '',
  verifyCode: '',
})

const countDown = ref(0)
const getVerifyCodeClick = async () => {
  if (countDown.value !== 0) {
    return
  }
  if (!verifyPhoneNo(phoneInfo.phoneNo)) {
    ElMessage({
      type: 'warning',
      message: '手机号格式错误',
      plain: true,
    })
    return
  }
  countDown.value = 60
  const intervalId = window.setInterval(() => {
    if (countDown.value > 0) {
      countDown.value--
    } else {
      clearInterval(intervalId)
    }
  }, 1000)
  const res = await getVerifyCodeReq(phoneInfo.phoneNo)
  if (res.data.code === SUCCESS_CODE) {
    ElMessage({
      type: 'success',
      message: `您的验证码为：${res.data.data}，一分钟内有效`,
      plain: true,
      duration: 5000,
    })
  } else {
    ElMessage({
      type: 'error',
      message: res.data.message,
      plain: true,
    })
    countDown.value = 0
  }
}

defineExpose({
  phoneInfo,
})
</script>

<style lang="scss" scoped>
.login-account {
  width: 330px;
  margin: 0 auto;
  .login-input {
    width: 330px;
    height: 65px;
    padding-bottom: 20px;
    font-size: 15px;
    border-radius: 15px;
  }
}
</style>
