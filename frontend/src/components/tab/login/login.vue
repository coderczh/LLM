<template>
  <div class="dialog">
    <el-dialog v-model="showDialog" :show-close="false" @close="closeDialog">
      <div class="dialog-content">
        <div class="content-login">
          <img src="@/assets/img/facion.png" class="login-logo" />
          <account v-if="loginByAccount" ref="accountRef" />
          <phone v-if="loginByPhone" ref="phoneRef" />
          <el-divider style="width: 300px; margin: 20px auto"
            ><span style="color: #8f8f8f; font-size: 12px; font-weight: normal"
              >其他登录方式</span
            ></el-divider
          >
          <div class="login-type">
            <div class="login-type-item" style="margin-right: 10px" @click="loginByAccountClick">
              <img src="@/assets/img/account.png" style="width: 20px; height: 20px" />
            </div>
            <div class="login-type-item" @click="loginByPhoneClick">
              <img src="@/assets/img/phone.png" style="width: 20px; height: 20px" />
            </div>
          </div>
          <el-checkbox
            v-model="register"
            label="是否注册为新用户"
            size="small"
            class="login-remind"
          />
          <el-button class="login-button" size="large" @click="submitClick">
            <span v-if="register">注&nbsp;册</span>
            <span v-else>登&nbsp;录</span>
          </el-button>
        </div>
        <div class="content-img">
          <img
            src="@/assets/img/login.png"
            style="width: 200px; height: 200px; border-radius: 20px"
          />
        </div>
      </div>
    </el-dialog>
  </div>
</template>

<script lang="ts" setup>
import { ElMessage } from 'element-plus'
import account from './account/account.vue'
import phone from './phone/phone.vue'
import { ref } from 'vue'
import { loginStore } from '@/stores/tab/tab'

const showDialog = ref(true)

const emit = defineEmits(['closeDialog'])
const closeDialog = () => {
  emit('closeDialog', true)
}

const register = ref(false)

const loginByAccount = ref(true)
const loginByPhone = ref(false)
const loginByAccountClick = () => {
  loginByPhone.value = false
  loginByAccount.value = true
}
const loginByPhoneClick = () => {
  loginByAccount.value = false
  loginByPhone.value = true
}

const accountRef = ref()
const phoneRef = ref()
const useLoginStore = loginStore()
const submitClick = () => {
  if (loginByAccount.value) {
    if (accountRef.value.userInfo.accountNo === '') {
      ElMessage({
        type: 'warning',
        message: '账号不能为空',
      })
    } else if (accountRef.value.userInfo.password === '') {
      ElMessage({
        type: 'warning',
        message: '密码不能为空',
      })
    } else {
      useLoginStore.getUserInfo(accountRef.value.userInfo)
    }
  } else {
    console.log(phoneRef.value.phoneInfo)
  }
}
</script>

<style lang="scss" scoped>
.dialog :deep(.el-dialog) {
  width: 40%;
  height: 500px;
  margin: 25vh auto 50px;
  padding: 10px 10px;
  background: $login-color-to-right;
  border-radius: 10px;
  .dialog-content {
    display: flex;
    justify-content: center;
    align-items: center;
    width: 100%;
    height: 480px;
    .content-login {
      width: 55%;
      height: 100%;
      background-color: #fff;
      border-radius: 10px;
      .login-logo {
        display: block;
        margin: 30px auto;
        width: 55px;
        height: 55px;
        border-radius: 10px;
      }
      .login-type {
        display: flex;
        justify-content: center;
        align-items: center;
        .login-type-item {
          width: 40px;
          height: 40px;
          display: flex;
          align-items: center;
          justify-content: center;
          background-color: rgba(0, 0, 0, 0.03);
          border-radius: 40px;
        }
      }

      .login-remind {
        display: block;
        margin-top: 20px;
        font-size: 10px;
        text-align: center;
        margin-bottom: 30px;
      }
      .login-button {
        display: block;
        margin: 0 auto;
        width: 260px;
        font-size: 16px;
        font-weight: normal;
        color: #fff;
        background: #3a59d1;
      }
    }
    .content-img {
      flex: 1;
      text-align: center;
    }
  }
}

:deep(.el-dialog__header) {
  padding-bottom: 0;
}

:deep(.el-checkbox) {
  color: #818181;
  font-weight: normal;
}

:deep(.el-input__wrapper) {
  border-radius: 10px;
}
</style>
