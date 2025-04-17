<template>
  <div class="left-aside">
    <div class="top">
      <div class="logo">
        <img src="@/assets/img/logo.svg" class="image" />
      </div>
      <div class="button">
        <el-icon size="18" style="margin-right: 8px" color="#343434"><Search /></el-icon>
        <el-icon size="18" color="#343434"><Collection /></el-icon>
      </div>
    </div>
    <div class="content">
      <el-scrollbar>
        <div v-for="(button, index) in 50" :key="index" class="record">
          <div class="text">dddddddddddddddddddddddddddddddddd</div>
          <div class="icon" color="#343434" @click="deleteRecord">
            <el-icon color="red"><Delete /></el-icon>
          </div>
        </div>
      </el-scrollbar>
    </div>
    <div class="bottom">
      <div class="user-info">
        <el-avatar :size="35" :src="userInfo.avatar" style="margin-right: 5px" />
        {{ userInfo.nickName }}
      </div>
      <div class="icon">
        <el-icon size="18" color="#343434" @click="showUserInfo = true"><Edit /></el-icon>
        <el-icon size="18" color="#343434" style="margin-left: 8px" @click="logout"
          ><SwitchButton
        /></el-icon>
      </div>
    </div>
  </div>
  <div class="user-info">
    <el-dialog v-model="showUserInfo" title="我的信息" width="500">
      <el-form :model="userInfo">
        <div class="avatar">
          <el-form-item>
            <el-avatar :size="70" :src="userInfo.avatar" />
          </el-form-item>
        </div>
        <el-form-item label="昵称：" required>
          <el-input
            v-model="userInfo.nickName"
            autocomplete="off"
            :placeholder="userInfo.nickName"
          />
        </el-form-item>
        <el-form-item label="性别：" required>
          <el-select v-model="userInfo.gender" :placeholder="userInfo.gender">
            <el-option label="女" value="0" />
            <el-option label="男" value="1" />
          </el-select>
        </el-form-item>
      </el-form>
      <template #footer>
        <div class="dialog-footer">
          <el-button @click="showUserInfo = false"> 取消 </el-button>
          <el-button type="primary" @click="updateUserInfo"> 确认 </el-button>
        </div>
      </template>
    </el-dialog>
  </div>
  <el-button v-if="false" />
</template>

<script lang="ts" setup>
import { localCache } from '@/assets/common/cache'
import type { UserInfo } from '@/assets/common/common'
import { USER_INFO } from '@/assets/common/constant'
import { useLoginStore } from '@/stores/aside/aside'
import { ElMessageBox, ElMessage } from 'element-plus'
import { onMounted, reactive, ref, watch } from 'vue'

const userInfo = reactive<UserInfo>({
  jobNo: '',
  avatar: '',
  nickName: '',
  gender: '',
  password: '',
})
const loginStore = useLoginStore()
onMounted(() => {
  const cacheInfo = localCache.getCache(USER_INFO)
  if (cacheInfo !== null) {
    Object.assign(userInfo, cacheInfo)
  } else {
  }
})

/**
 * 删除
 */
const deleteRecord = () => {
  console.log('delete')
}

/**
 * 退出登录
 */
const loginStatus = ref<boolean>(false)
const logout = () => {
  ElMessageBox.confirm('退出登录不会丢失任何数据，您仍可登录该账号。', '退出登录', {
    confirmButtonText: '确定',
    cancelButtonText: '取消',
    type: 'warning',
  }).then(() => {
    loginStatus.value = false
    ElMessage({
      type: 'success',
      message: '退出登录成功',
    })
  })
}

/**
 * 编辑用户信息
 */
const showUserInfo = ref(false)
const updateUserInfo = () => {
  showUserInfo.value = false
  ElMessage({
    message: '信息更新成功',
    type: 'success',
    plain: true,
  })
}

/**
 * 如果性别发生变动，头像也会变动
 */
watch(
  userInfo,
  (newVal) =>
    (userInfo.avatar =
      newVal.gender === '0'
        ? 'https://llm-1258823864.cos.ap-shanghai.myqcloud.com/girl.png'
        : 'https://llm-1258823864.cos.ap-shanghai.myqcloud.com/boy.png'),
)
</script>

<style lang="scss" scoped>
.left-aside {
  padding: 15px 15px;
  height: 100vh;
  display: flex;
  flex-direction: column;
  .top {
    display: flex;
    justify-content: space-between;
    align-items: center;
    .logo {
      .image {
        height: 25px;
        width: 30px;
      }
    }
  }
  .content {
    flex-grow: 1;
    overflow-y: auto;
    margin-top: 10px;
    margin-bottom: 20px;
    .record {
      padding: 0 5px;
      width: 260px;
      height: 45px;
      border-radius: 5px;
      transition: background-color 0.3s;
      display: flex;
      justify-content: space-between;
      align-items: center;
      .text {
        font-size: 16px;
        color: $font-icon-color;
        line-height: 1.5em;
        width: auto;
        line-height: 1.5em;
        overflow: hidden;
        white-space: nowrap;
        text-overflow: ellipsis;
      }
      .icon {
        opacity: 0;
        width: 40px;
        margin-left: 10px;
      }
    }
    .record:hover {
      background-color: #f3f3f3;
      .icon {
        opacity: 1;
      }
    }
  }
  .bottom {
    margin-top: 5px;
    display: flex;
    justify-content: space-between;
    align-items: center;
    .user-info {
      display: flex;
      justify-content: center;
      align-items: center;
      color: $font-icon-color;
    }
  }
}

.user-info {
  .avatar {
    display: flex;
    justify-content: center;
    align-items: center;
  }
}
</style>
