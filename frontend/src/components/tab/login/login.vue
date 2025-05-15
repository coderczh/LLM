<template>
  <div class="dialog">
    <el-dialog v-model="showDialog" :show-close="false" @close="closeDialog" class="dialog">
      <div class="dialog-content">
        <div class="content-login">11</div>
        <div class="content-img">222</div>
      </div>
    </el-dialog>
  </div>
</template>

<script lang="ts" setup>
import { ref } from 'vue'

const showDialog = ref(true)

const emit = defineEmits(['closeDialog'])
const closeDialog = () => {
  emit('closeDialog', true)
}
</script>

<style lang="scss" scoped>
.dialog :deep(.el-dialog) {
  width: 40%;
  height: 300px;
  margin: 25vh auto;
  padding: 10px 10px;
  background: $theme-color-to-right;
  .dialog-content {
    display: flex;
    justify-content: center;
    align-items: center;
    width: 100%;
    height: 100%;
    .content-login {
      width: 50%;
      height: 100%;
      background-color: red;
    }
    .content-img {
      flex: 1;
    }
  }
}

:deep(.el-dialog__header) {
  padding-bottom: 0;
}
</style>
