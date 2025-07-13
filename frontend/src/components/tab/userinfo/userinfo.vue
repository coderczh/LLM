<template>
  <el-dialog width="500" v-model="showDialog" v-if="showUpdateUserInfo">
    <div style="text-align: center">
      <el-avatar :src="localCache.getCache('userInfo').avatar" />
    </div>

    <template #footer>
      <el-button type="primary"> 确认 </el-button>
    </template>
  </el-dialog>
</template>

<script lang="ts" setup>
import { localCache } from '@/assets/common/cache'
import { ref, watch } from 'vue'

const showDialog = ref<boolean>(false)

const props = defineProps({
  showUpdateUserInfo: {
    type: Boolean,
    required: true,
    default: false,
  },
})

watch(
  () => props.showUpdateUserInfo,
  (newVal) => {
    showDialog.value = newVal
  },
)
</script>

<style lang="scss" scoped></style>
