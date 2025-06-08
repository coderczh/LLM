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
          <div class="spot-type">
            <div class="spot-type-name">效率办公</div>
            <div class="spot-type-arr">
              <spot
                v-for="(item, index) in officeSpot"
                :key="index"
                :img="item.img"
                :text="item.text"
              />
            </div>
          </div>
          <div class="spot-type" style="margin-left: 20px">
            <div class="spot-type-name">编码助手</div>
            <div class="spot-type-arr">
              <spot
                v-for="(item, index) in codeSpot"
                :key="index"
                :img="item.img"
                :text="item.text"
              />
            </div>
          </div>
        </div>
      </div>
      <div class="answer">
        <div class="operation">
          <div class="deep" @click="operation('deep')">
            <el-button round :style="deepOperation">
              <template #icon>
                <img src="@/assets/img/deep.png" style="width: 16px; height: 16px" />
              </template>
              深度思考
            </el-button>
          </div>
          <div class="online" @click="operation('online')">
            <el-button round :style="onlineOperation">
              <template #icon>
                <img src="@/assets/img/online.png" style="width: 18px; height: 18px" />
              </template>
              联网搜索</el-button
            >
          </div>
        </div>
        <div class="input">
          <el-input
            style="width: 85%; height: 50px; font-size: 15px"
            placeholder="有问题，问百全"
            v-model="inputVal"
          >
            <template #prefix
              ><el-icon color="#343434" size="20" style="margin-right: 15px"><Upload /></el-icon>
            </template>
            <template #suffix>
              <el-button :icon="Promotion" circle color="#3a59d1" />
            </template>
          </el-input>
        </div>
      </div>
    </div>
  </div>
</template>

<script lang="ts" setup>
import type { StyleChange } from '@/assets/common/common'
import { Search, Promotion } from '@element-plus/icons-vue'
import { reactive, ref } from 'vue'
import { NORMAL_STYLE, SELECT_STYLE } from '@/assets/common/constant'
import spot from './spot/spot.vue'

const deepOperation = reactive<StyleChange>(JSON.parse(JSON.stringify(NORMAL_STYLE)))
const onlineOperation = reactive<StyleChange>(JSON.parse(JSON.stringify(NORMAL_STYLE)))
const operation = (type: string) => {
  if (deepOperation.color === '#777' && type === 'deep') {
    Object.assign(deepOperation, SELECT_STYLE)
  } else if (onlineOperation.color === '#777' && type === 'online') {
    Object.assign(onlineOperation, SELECT_STYLE)
  } else if (type === 'deep') {
    Object.assign(deepOperation, NORMAL_STYLE)
  } else {
    Object.assign(onlineOperation, NORMAL_STYLE)
  }
}

const inputVal = ref<string>('')

const officeSpot = reactive<any>([
  {
    img: 'src/assets/img/search.png',
    text: '智慧搜索',
  },
  {
    img: 'src/assets/img/ppt.png',
    text: 'PPT创作',
  },
  {
    img: 'src/assets/img/read.png',
    text: '阅读助手',
  },
  {
    img: 'src/assets/img/translate.png',
    text: '语言翻译',
  },
])

const codeSpot = reactive<any>([
  {
    img: 'src/assets/img/bug.png',
    text: 'bug修复',
  },
  {
    img: 'src/assets/img/flaw.png',
    text: '漏洞分析',
  },
  {
    img: 'src/assets/img/code.png',
    text: '代码续写',
  },
  {
    img: 'src/assets/img/explain.png',
    text: '代码解释',
  },
])
</script>

<style lang="scss" scoped>
.content {
  height: calc(100vh - 20px);
  background-color: #fff;
  border-radius: 10px;
  display: flex;
  .left {
    width: 255px;
    height: calc(100vh - 20px);
    border-radius: 10px 0 0 10px;
    border-right: 1px solid #f1f1f1;
    .top {
      padding: 30px 20px;
      text-align: center;
    }
  }
  .right {
    flex: 1;
    border-radius: 0 10px 10px 0;
    position: relative;
    .tip {
      width: 75%;
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
        display: flex;
        .spot-type {
          width: 40%;
          height: 100%;
          border-radius: 10px;
          background: $theme-color-to-right;
          .spot-type-name {
            font-size: 18px;
            font-weight: 600;
            padding: 10px 0 0 20px;
            color: $font-icon-color;
          }
          .spot-type-arr {
            padding-top: 10px;
            display: flex;
            justify-content: center;
            align-items: center;
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
        line-height: 1.2em;
        padding-bottom: 12px;
        width: 70%;
        padding-top: 70px;
        padding-left: 10px;
        margin: 0 auto;
        .deep {
          padding-right: 10px;
        }
      }
      .input {
        width: 70%;
        margin: 0 auto;
      }
    }
  }
}

:deep(.el-input__wrapper) {
  border-radius: 15px;
  border: 1px solid #3a59d1;
  box-shadow: none;
}

:deep(.el-input__wrapper.is-focus) {
  box-shadow: 0 0 0 0.5px #3a59d1;
}

:deep(.el-input__inner) {
  color: #343434;
}
</style>
