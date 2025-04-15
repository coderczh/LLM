<template>
  <div class="content">
    <div class="top">
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
            <el-button :icon="Promotion" type="primary" circle @click="sendAnswer" />
          </el-form-item>
        </div>
      </el-form>
    </div>
  </div>
</template>

<script lang="ts" setup>
import { registry } from '@/service/dialogue/dialogue'
import { Upload, Promotion } from '@element-plus/icons-vue'
import { reactive } from 'vue'

const content = reactive<any>({
  question: '',
  deep: false,
})

const sendAnswer = () => {
  const dialogueInputDto: any = {
    question: '明月几时有的下一句',
    model: 0,
  }
  registry(dialogueInputDto)
}
</script>

<style lang="scss" scoped>
.content {
  padding: 20px;
  height: 100vh;
  display: flex;
  flex-direction: column;
  .top {
    flex-grow: 1;
    overflow-y: auto;
    width: 80%;
    margin: 0 auto;
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: flex-start;
    padding-bottom: 15px;
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
    height: 100px;
    width: 80%;
    margin: 0 auto;
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
