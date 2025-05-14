<template>
  <div class="dialog">
    <el-dialog v-model="showDialog" :show-close="false" @close="closeDialog"> </el-dialog>
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
  margin: 25vh auto 50px;
  background: $theme-color-to-right;
}
</style>
