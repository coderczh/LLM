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
          <div class="office" style="margin-left: 15px">
            <div class="office-name">编码助手</div>
            <div class="office-type">
              <div class="office-type-item">
                <div class="office-type-item-img">
                  <img src="@/assets/img/bug.png" style="width: 30px; height: 30px" />
                </div>
                <div class="office-type-item-text">bug修复</div>
              </div>
              <div class="office-type-item">
                <div class="office-type-item-img">
                  <img src="@/assets/img/flaw.png" style="width: 30px; height: 30px" />
                </div>
                <div class="office-type-item-text">漏洞分析</div>
              </div>
              <div class="office-type-item">
                <div class="office-type-item-img">
                  <img src="@/assets/img/code.png" style="width: 30px; height: 30px" />
                </div>
                <div class="office-type-item-text">代码续写</div>
              </div>
              <div class="office-type-item">
                <div class="office-type-item-img">
                  <img src="@/assets/img/explain.png" style="width: 30px; height: 30px" />
                </div>
                <div class="office-type-item-text">代码解释</div>
              </div>
            </div>
          </div>
        </div>
      </div>
      <div class="answer">11</div>
    </div>
  </div>
</template>

<script lang="ts" setup>
import { Search } from '@element-plus/icons-vue'
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
      width: 60%;
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
    }
  }
}
</style>
