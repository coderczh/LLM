<template>
  <div class="dialog">
    <el-dialog v-model="showDialog" :show-close="false" @close="closeDialog" class="dialog">
      <div class="dialog-content">
        <div class="content-login">
          <div class="login-logo">
            <img
              src="@/assets/img/facion.png"
              style="width: 55px; height: 55px; border-radius: 10px; margin: 0 auto"
            />
          </div>
          <div class="login-input">
            <el-input
              v-model="userInfo.account"
              style="
                width: 300px;
                height: 65px;
                padding-bottom: 20px;
                font-size: 15px;
                border-radius: 15px;
              "
              placeholder="账号"
              size="large"
            />
            <el-input
              v-model="userInfo.password"
              style="
                width: 300px;
                height: 65px;
                padding-bottom: 20px;
                font-size: 15px;
                border-radius: 15px;
              "
              placeholder="密码"
              size="large"
              show-password
            />
          </div>
          <el-divider style="width: 300px; margin: 20px auto"
            ><span style="color: #8f8f8f; font-size: 12px; font-weight: lighter"
              >其他登录方式</span
            ></el-divider
          >
          <div class="login-phone">
            <img src="@/assets/img/phone.png" style="width: 20px; height: 20px" />
          </div>
          <div class="login-warn">
            <el-checkbox v-model="register" label="是否注册为新用户" size="small" />
          </div>
          <div class="login-button">
            <el-button
              style="
                width: 260px;
                font-size: 16px;
                font-weight: lighter;
                color: #fff;
                background: #3a59d1;
              "
              size="large"
            >
              <span v-if="register">注册</span>
              <span v-else>登录</span>
            </el-button>
          </div>
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
import { reactive, ref } from 'vue'

const showDialog = ref(true)

const emit = defineEmits(['closeDialog'])
const closeDialog = () => {
  emit('closeDialog', true)
}

const userInfo = reactive({
  account: '',
  password: '',
})
const register = ref(false)
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
        margin: 30px auto;
        width: 55px;
        height: 55px;
      }
      .login-input {
        width: 300px;
        margin: 0 auto;
      }
      .login-phone {
        width: 40px;
        height: 40px;
        margin: 0 auto;
        display: flex;
        align-items: center;
        justify-content: center;
        background-color: rgba(0, 0, 0, 0.03);
        border-radius: 40px;
      }
      .login-warn {
        margin-top: 20px;
        font-size: 10px;
        text-align: center;
        margin-bottom: 30px;
      }
      .login-button {
        width: 260px;
        margin: 0 auto;
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
  font-weight: lighter;
}
</style>
