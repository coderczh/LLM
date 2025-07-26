<template>
  <!-- 用户信息弹窗 -->
  <el-dialog
    width="600"
    v-model="showDialog"
    @close="closeDialog()"
    style="background: linear-gradient(to right, #f6fef6, #fffafa); border-radius: 12px"
  >
    <!-- 头像展示区 -->
    <div style="text-align: center">
      <el-avatar :src="localCache.getCache('userInfo').avatar" size="large" />
    </div>

    <!-- 表单主体 -->
    <div style="text-align: center; margin-top: 20px">
      <!-- 账号，不可修改 -->
      <el-form ref="formRef" :rules="rules" :model="userInfo" inline label-width="auto">
        <el-form-item label="账号" prop="accountNo" label-position="left" required>
          <el-input v-model="userInfo.accountNo" disabled />
        </el-form-item>

        <!-- 昵称输入 -->
        <el-form-item label="昵称" prop="nickName" label-position="left">
          <el-input v-model="userInfo.nickName" />
        </el-form-item>

        <!-- 手机号 -->
        <el-form-item label="手机号" prop="phoneNo" label-position="left">
          <el-input v-model="userInfo.phoneNo" />
        </el-form-item>

        <!-- 验证码 -->
        <el-form-item label="验证码" prop="verifyCode" label-position="left" style="width: 50%">
          <el-input v-model="userInfo.verifyCode" style="width: 60%" />
          <el-button
            style="margin: 0 0 0 auto; width: 35%"
            type="primary"
            @click="getVerifyCode()"
            :disabled="isSending || !isPhoneValid"
          >
            {{ isSending ? `${countdown} 秒` : '获取' }}
          </el-button>
        </el-form-item>
      </el-form>
    </div>

    <!-- 弹窗底部按钮 -->
    <template #footer>
      <span class="dialog-footer">
        <el-button @click="handleCancel">取消</el-button>
        <el-button type="primary" @click="handleConfirm">确认</el-button>
      </span>
    </template>
  </el-dialog>
</template>

<script lang="ts" setup>
// 组件逻辑 ============================================================
/** 缓存管理 */
import { localCache } from '@/assets/common/cache'
import { SUCCESS_CODE } from '@/assets/common/constant'
import { getVerifyCodeReq } from '@/service/core/tab/tab'
import { loginStore } from '@/stores/tab/tab'
/** Element Plus 组件 */
import { ElForm, ElMessage } from 'element-plus'
/** Vue 响应式 API */
import { computed, reactive, ref } from 'vue'

// 弹窗控制 ----------------------------
const showDialog = ref<boolean>(true)
const emit = defineEmits(['closeDialog'])

// 用户信息表单 -------------------------
const userInfo = reactive({
  accountNo: localCache.getCache('userInfo').accountNo,
  nickName: localCache.getCache('userInfo').nickName,
  phoneNo: localCache.getCache('userInfo').phoneNo,
  verifyCode: '',
})

// 表单验证规则 ========================================================
const rules = {
  nickName: [
    {
      required: true,
      message: '请输入昵称',
      trigger: 'blur',
    },
  ],
  phoneNo: [
    {
      required: true,
      message: '请输入手机号',
      trigger: 'blur',
    },
    {
      pattern: /^1[3-9]\d{9}$/,
      message: '请输入有效的11位手机号',
      trigger: 'blur',
    },
  ],
  verifyCode: [
    {
      validator: (_: any, value: any, callback: any) => {
        const originalPhone = localCache.getCache('userInfo').phoneNo
        if (userInfo.phoneNo !== originalPhone && !value) {
          callback(new Error('请输入验证码'))
        } else {
          callback()
        }
      },
      trigger: 'blur',
    },
  ],
}

// 计算属性部分（新增）
const isPhoneValid = computed(() => {
  return /^1[3-9]\d{9}$/.test(userInfo.phoneNo)
})

// 事件处理 ============================================================
/** 表单引用 */
const formRef = ref<InstanceType<typeof ElForm>>()

/** 确认按钮点击事件 */
const useLoginStore = loginStore()
const handleConfirm = () => {
  formRef.value?.validate(async (valid: any) => {
    if (!valid) {
      ElMessage.error('表单校验失败')
    } else {
      const userInfoInputDTO = {
        accountNo: userInfo.accountNo,
        avatar: localCache.getCache('userInfo').avatar,
        nickName: userInfo.nickName,
        phoneNo: userInfo.phoneNo,
        verifyCode: userInfo.verifyCode,
      }
      await useLoginStore.updateUserInfo(userInfoInputDTO)
    }
  })
}

/** 取消按钮点击事件 */
const handleCancel = () => {
  formRef.value?.resetFields()
  closeDialog()
}

/** 关闭弹窗 */
const closeDialog = () => {
  emit('closeDialog', false)
}

// 新增状态
const countdown = ref(60)
const isSending = ref(false)
// 获取验证码逻辑
const getVerifyCode = async () => {
  try {
    console.log('获取验证码成功')
    isSending.value = true
    const res = await getVerifyCodeReq(userInfo.phoneNo)
    if (res.data.code === SUCCESS_CODE) {
      ElMessage.success(`您的验证码为：${res.data.data}，一分钟内有效`)
    } else {
      ElMessage.error('验证码发送失败')
    }
    // 开始倒计时
    const timer = setInterval(() => {
      countdown.value--
      if (countdown.value === 0) {
        clearInterval(timer)
        countdown.value = 60
        isSending.value = false
      }
    }, 1000)
  } catch (err) {
    console.log('验证码发送失败 ==== ', err)
    ElMessage.error('验证码发送失败')
    isSending.value = false
  }
}
</script>

<!-- 样式部分 ========================================================= -->
<style lang="scss" scoped>
.dialog-footer {
  display: flex;
  justify-content: center;
  gap: 24px;
  padding: 0 20px 20px;

  .el-button {
    padding: 10px 24px;
    border-radius: 3px;
    transition: all 0.3s;
    min-width: 100px;

    &:hover {
      transform: translateY(-2px);
      box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
    }
  }
}

:deep(.el-form--inline .el-form-item) {
  margin-right: 0;
  padding: 5px 10px;
}
</style>
