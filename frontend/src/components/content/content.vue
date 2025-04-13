<template>
  <div class="content">
    <div class="top" :style="{ height: `${pageHeight}px` }">
      <div class="speak">你可以这样说</div>
      <el-divider />
      <div class="template">今天合肥的天气如何</div>
      <div class="template">大模型领域的应用有哪些</div>
    </div>
    <div class="bottom">
      <el-form :model="content">
        <el-form-item style="margin-bottom: 5px">
          <el-input
            class="custom-borderless"
            size="large"
            v-model="content.question"
            style="border: 0 !important"
          />
        </el-form-item>
        <div style="display: flex; align-items: center; justify-content: space-between">
          <el-form-item style="margin-left: 15px" label="深度思考">
            <el-switch v-model="content.deep" />
          </el-form-item>
          <el-form-item style="margin-right: 15px">
            <el-button :icon="Upload" circle />
            <el-button :icon="Promotion" type="primary" circle />
          </el-form-item>
        </div>
      </el-form>
    </div>
  </div>
</template>

<script lang="ts" setup>
import { computed, reactive } from 'vue'
import { Upload, Promotion } from '@element-plus/icons-vue'

const content = reactive<any>({
  question: '',
  deep: false,
})

/**
 * 获取页面高度
 */
const pageHeight = computed(() => document.body.clientHeight - 140)
</script>

<style lang="scss" scoped>
.content {
  .top {
    margin: 0 400px 0 400px;
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: left;
    .speak {
      color: black;
      font-size: 30px;
    }
    .template {
      color: $font-icon-color;
      margin-bottom: 15px;
      width: 200px;
      height: 30px;
      background-color: #f5f5f5;
      border-radius: 5px;
      display: flex;
      align-items: center;
      padding-left: 10px;
    }
  }
  .bottom {
    margin: 0 400px 0 400px;
    height: 100px;
    border-radius: 10px;
    box-shadow: 0 0 2px 0 rgba(0, 0, 0, 0.3);
    .custom-borderless {
      height: 55px !important;
      font-size: 20px;
      color: $font-icon-color;
      :deep(.el-input__wrapper) {
        box-shadow: none !important;
        background: transparent;
        margin-bottom: none;
      }
    }
  }
}
</style>
