<template>
  <div class="content">
    <div class="left">
      <div class="top">
        <el-button round color="#3a59d1" style="width: 155px" disabled>
          <el-icon color="#fff"><Plus /></el-icon>
          <text style="padding-left: 3px; line-height: 1.2em">新建对话</text>
        </el-button>
        <el-button :icon="Search" circle />
      </div>
    </div>
    <div class="right">
      <div class="tip">
        <div class="descipition">
          <div class="logo">
            <img
              src="@/assets/img/facion.png"
              style="width: 55px; height: 55px; border-radius: 10px"
            />
          </div>
          <div class="logo-text">
            <div class="logo-text-big">我是百全，你的智能百科全书</div>
            <div class="logo-text-small">我可以帮你做这些事情</div>
          </div>
        </div>
        <div class="spot">
          <div class="office">
            <div class="office-name">效率办公</div>
            <div class="office-type">
              <div class="office-type-item">
                <div class="office-type-item-img">
                  <img src="@/assets/img/search.png" style="width: 30px; height: 30px" />
                </div>
                <div class="office-type-item-text">智慧搜索</div>
              </div>
              <div class="office-type-item">
                <div class="office-type-item-img">
                  <img src="@/assets/img/ppt.png" style="width: 30px; height: 30px" />
                </div>
                <div class="office-type-item-text">PPT创作</div>
              </div>
              <div class="office-type-item">
                <div class="office-type-item-img">
                  <img src="@/assets/img/read.png" style="width: 30px; height: 30px" />
                </div>
                <div class="office-type-item-text">阅读助手</div>
              </div>
              <div class="office-type-item">
                <div class="office-type-item-img">
                  <img src="@/assets/img/translate.png" style="width: 30px; height: 30px" />
                </div>
                <div class="office-type-item-text">语言翻译</div>
              </div>
            </div>
          </div>
        </div>
      </div>
      <div class="answer">
        <div class="operation">
          <div class="deep" @click="deep">
            <el-button round :style="deepOperation">
              <template #icon>
                <img src="@/assets/img/deep.png" style="width: 16px; height: 16px" />
              </template>
              深度思考
            </el-button>
          </div>
          <div class="online" @click="online">
            <el-button round :style="onlineOperation">
              <template #icon>
                <img src="@/assets/img/online.png" style="width: 18px; height: 18px" />
              </template>
              联网搜索</el-button
            >
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script lang="ts" setup>
import type { StyleChange } from '@/assets/common/common'
import { Search } from '@element-plus/icons-vue'
import { reactive } from 'vue'

const deepOperation = reactive<StyleChange>({
  'background-color': '',
  'border-color': '',
  color: '',
  outline: '',
})

const deep = () => {
  if (deepOperation.color === '') {
    Object.assign(deepOperation, {
      'background-color': 'var(--el-button-hover-bg-color)',
      'border-color': 'var(--el-button-hover-border-color)',
      color: 'var(--el-button-hover-text-color)',
      outline: 'none',
    })
  } else {
    Object.assign(deepOperation, {
      'background-color': '',
      'border-color': '',
      color: '',
      outline: '',
    })
  }
}

const onlineOperation = reactive<StyleChange>({
  'background-color': '',
  'border-color': '',
  color: '',
  outline: '',
})
const online = () => {
  if (onlineOperation.color === '') {
    Object.assign(onlineOperation, {
      'background-color': 'var(--el-button-hover-bg-color)',
      'border-color': 'var(--el-button-hover-border-color)',
      color: 'var(--el-button-hover-text-color)',
      outline: 'none',
    })
  } else {
    Object.assign(onlineOperation, {
      'background-color': '',
      'border-color': '',
      color: '',
      outline: '',
    })
  }
}
</script>

<style lang="scss" scoped>
.content {
  height: calc(100vh - 20px);
  background-color: #fff;
  border-radius: 10px;
  display: flex;
  .left {
    width: 260px;
    height: calc(100vh - 20px);
    border-radius: 10px 0 0 10px;
    border-right: 1px solid #f1f1f1;
    .top {
      padding: 30px;
      text-align: center;
    }
  }
  .right {
    flex: 1;
    border-radius: 0 10px 10px 0;
    position: relative;
    .tip {
      width: 70%;
      margin: 0 auto;
      height: calc(100% - 210px);
      border-radius: 0 10px 0 0;
      display: flex;
      flex-direction: column;
      justify-content: center;
      align-items: center;
      .descipition {
        width: 100%;
        height: 100px;
        display: flex;
        .logo {
          width: 80px;
          height: 100%;
          display: flex;
          align-items: center;
          justify-content: center;
        }
        .logo-text {
          display: flex;
          flex-direction: column;
          align-items: left;
          justify-content: center;
          .logo-text-big {
            color: $font-icon-color;
            font-size: 25px;
            font-weight: bold;
            line-height: 1.2em;
          }
          .logo-text-small {
            padding-top: 10px;
            color: #666;
          }
        }
      }
      .spot {
        padding-top: 10px;
        width: 100%;
        height: 180px;
        .office {
          width: 30%;
          height: 100%;
          border-radius: 10px;
          background: $theme-color-to-right;
          .office-name {
            font-size: 18px;
            font-weight: 600;
            padding: 10px 0 0 20px;
            color: $font-icon-color;
          }
          .office-type {
            padding-top: 10px;
            display: flex;
            justify-content: center;
            align-items: center;
            .office-type-item {
              width: 70px;
              height: 90px;
              background-color: #fff;
              margin: 10px 5px;
              border-radius: 10px;
              .office-type-item-img {
                width: 100%;
                height: 60px;
                display: flex;
                justify-content: center;
                align-items: center;
              }
              .office-type-item-text {
                font-size: 14px;
                font-weight: 500;
                display: flex;
                justify-content: center;
                color: $font-icon-color;
              }
            }
          }
        }
      }
    }
    .answer {
      position: absolute;
      bottom: 0;
      width: 100%;
      height: 200px;
      border-radius: 0 0 10px 0;
      .operation {
        display: flex;
        color: $font-icon-color;
        line-height: 1.2em;
        .deep {
          padding-right: 10px;
        }
      }
    }
  }
}
</style>
