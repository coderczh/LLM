<template>
  <el-dialog width="550" v-model="showDialog" @close="closeDialog()">
    <div style="text-align: center">
      <el-avatar :src="localCache.getCache('userInfo').avatar" />
    </div>
    <div style="text-align: center; margin-top: 20px">
      <el-form :model="userInfo" inline label-width="auto">
        <el-form-item label="昵称">
          <el-input v-model="userInfo.name" />
        </el-form-item>
        <el-form-item label="账号">
          <el-input v-model="userInfo.name" />
        </el-form-item>
      </el-form>
    </div>
  </el-dialog>
</template>

<script lang="ts" setup>
import { localCache } from '@/assets/common/cache'
import { ref } from 'vue'

const showDialog = ref<boolean>(true)

const emit = defineEmits(['closeDialog'])
const closeDialog = () => {
  emit('closeDialog', false)
}

import { reactive } from 'vue'

// do not use same name with ref
const userInfo = reactive({
  name: '',
  region: '',
  date1: '',
  date2: '',
  delivery: false,
  type: [],
  resource: '',
  desc: '',
})
</script>

<style lang="scss" scoped>
:deep(.el-form--inline .el-form-item) {
  margin-right: 0;
  padding: 5px 10px;
}
</style>
