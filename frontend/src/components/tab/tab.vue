<template>
  <div class="tab">
    <div class="logo">
      <div class="img">
        <img src="@/assets/img/logo.svg" style="width: 100%; height: 100%" />
      </div>
      <div class="text">百全</div>
    </div>
    <feature />
    <div class="bottom">
      <div @click="openDialog" v-if="showLogin">
        <el-button color="rgba(0, 0, 0, 0.05)" size="large" circle>登录</el-button>
      </div>
      <div v-else>
        <el-popover placement="right-end" popper-style="min-width: 110px" width="110">
          <div @click="updateUserInfo()">
            <el-button text style="color: #666">修改信息</el-button>
          </div>
          <div @click="realAuth()">
            <el-button text style="color: #666">实名认证</el-button>
          </div>
          <template #reference>
            <el-avatar :src="localCache.getCache('userInfo').avatar" />
          </template>
        </el-popover>
      </div>
    </div>
  </div>
  <login @closeDialog="closeDialog" @logged="logged" v-if="showDialog" />
  <userinfo @closeDialog="closeUpdateUserInfo" v-if="showUpdateUserInfo" />
</template>

<script lang="ts" setup>
import { onMounted, ref } from 'vue'
import feature from './feature/feature.vue'
import login from './login/login.vue'
import { localCache } from '@/assets/common/cache'
import userinfo from './userinfo/userinfo.vue'

const showLogin = ref<boolean>(true)
onMounted(() => {
  showLogin.value = !localCache.getCache('userInfo')
})

const showDialog = ref(false)
const closeDialog = () => {
  showDialog.value = false
}
const openDialog = () => {
  showDialog.value = true
}

const logged = (e: any) => {
  showLogin.value = !e
}

const showUpdateUserInfo = ref<boolean>(false)
const updateUserInfo = () => {
  showUpdateUserInfo.value = true
}
const closeUpdateUserInfo = (value: boolean) => {
  showUpdateUserInfo.value = value
}

const showRealAuth = ref<boolean>(false)
const realAuth = () => {
  showRealAuth.value = true
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
