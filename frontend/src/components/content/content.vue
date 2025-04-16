<template>
  <div class="content">
    <div class="top" v-if="record.question === ''">
      <div class="speak">你可以这样说</div>
      <el-divider />
      <div class="template">今天合肥的天气如何</div>
      <div class="template">大模型领域的应用有哪些</div>
    </div>
    <div class="qa" v-else>
      <div class="question">
        <div class="fill"></div>
        <div class="text">{{ record.question }}</div>
      </div>
      <div class="answer">
        <MdPreview :modelValue="record.answer" style="text" />
      </div>
    </div>
    <div class="bottom">
      <el-form :model="content" @submit.prevent>
        <el-form-item style="margin-bottom: 5px">
          <el-input
            class="custom-borderless"
            v-model="content.question"
            style="border: 0 !important; font-size: 16px"
          />
        </el-form-item>
        <div style="display: flex; align-items: center; justify-content: space-between">
          <el-form-item style="margin-left: 15px" label="深度思考">
            <el-switch v-model="content.model" :active-value="1" :inactive-value="0" />
          </el-form-item>
          <el-form-item style="margin-right: 15px">
            <el-button :icon="Upload" circle />
            <el-button :icon="Promotion" type="primary" circle @click="send" />
          </el-form-item>
        </div>
      </el-form>
    </div>
  </div>
</template>

<script lang="ts" setup>
import type { DialogueInputDto, Record } from '@/assets/common/common'
import { getAnswer } from '@/service/dialogue/dialogue'
import { Upload, Promotion } from '@element-plus/icons-vue'
import { ElMessage } from 'element-plus'
import { MdPreview } from 'md-editor-v3'
import { reactive } from 'vue'

const content = reactive<DialogueInputDto>({
  question: '',
  model: 0,
})

const record = reactive<Record>({
  question: '122222222222222221',
  answer: '',
})

const send = async () => {
  const dialogueInputDto: DialogueInputDto = {
    question: content.question,
    model: 0,
  }
  record.question = content.question
  const answer = await getAnswer(dialogueInputDto)
  let allAnswer: string
  if (answer.data.code === '200') {
    allAnswer = answer.data.data.answer
  } else {
    ElMessage.error(answer.data.message)
  }
  console.log(record.question)
  console.log(record.answer)

  let count = 0
  const timer = setInterval(() => {
    if (count < allAnswer.length) {
      record.answer = record.answer += allAnswer.substring(count, (count += 30))
    } else {
      clearInterval(timer)
    }
  }, 500)
}

// 动态拆分文本为行元素
// const text = document.querySelector('.md-editor')?.textContent
// console.log();

// const lines = text.split('\n');  // 按换行符分割（或按容器宽度计算行数）
// const container = document.querySelector('.dynamic-text');
// container.innerHTML = lines.map(line =>
//   `<div class="line">${line}</div>`
// ).join('');

// // 动态添加动画延迟
// document.querySelectorAll('.line').forEach((line, index) => {
//   line.style.animationDelay = `${index * 0.5}s`;
// });
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

  .qa {
    flex-grow: 1;
    overflow-y: auto;
    width: 80%;
    margin: 0 auto;
    padding-bottom: 15px;
    .question {
      width: 100%;
      display: flex;
      justify-content: space-between;
      .fill {
        flex: 1;
      }
      .text {
        color: $font-icon-color;
        font-size: 18px;
        background-color: #f3f3f3;
        border-radius: 8px;
        height: 40px;
        padding: 0 5px;
        display: flex;
        justify-content: center;
        align-items: center;
        line-height: 1.2em;
      }
    }
    .answer {
      padding-top: 20px;
      .md-editor {
        opacity: 0;
        animation: fadeInUp 1.5s ease forwards;
      }
      @keyframes fadeInUp {
        from {
          opacity: 0;
          transform: translateY(20px);
        }
        to {
          opacity: 1;
          transform: translateY(0);
        }
      }
      .text:nth-child(1) {
        animation-delay: 0.5s;
      }
      .text:nth-child(2) {
        animation-delay: 1s;
      }
      .text:nth-child(3) {
        animation-delay: 1.5s;
      }
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
