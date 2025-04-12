<template>
  <div class="home">
    <div class="search">
      <el-button round :icon="Search" style="width: 150px">历史记录</el-button>
    </div>
    <div class="mark" @click="getMarkInfo">
      <div class="left">
        <div class="icon">
          <el-icon color="#606266" size="20"><Discount /></el-icon>
        </div>
        <div class="text">我的收藏</div>
      </div>
      <div class="right">
        <el-icon color="#606266"><ArrowRight /></el-icon>
      </div>
    </div>
    <div class="record">
      <div class="text" v-for="(item, index) in 10" :key="index">
        <div class="content">
          vue3的开发规范vue3的开发规范vue3的开发规范vue3的开发规范vue3的开发规范
        </div>
        <el-divider />
      </div>
    </div>
  </div>
</template>

<script lang="ts" setup>
import { Search } from '@element-plus/icons-vue'

const getMarkInfo = () => {
  console.log('收藏')
}
</script>

<style lang="scss" scoped>
.home {
  padding: 10px 10px;
  .search {
    display: flex;
    justify-content: center;
    align-items: center;
  }
  .mark {
    padding: 0 5px;
    display: flex;
    justify-content: space-between;
    align-items: center;
    margin-top: 15px;
    height: 40px;
    border: 1px solid #eee;
    border-radius: 10px;
    .left {
      display: flex;
      justify-content: center;
      align-items: center;
      .text {
        font-size: 18px;
        padding: 0 0 5px 5px;
        color: $font-icon-color;
      }
    }
  }
  .record {
    margin-top: 15px;
    border: 1px solid #eee;
    border-radius: 10px;
    padding: 5px 5px;
    .text {
      .content {
        @include multi-line-ellipsis(1);
        font-size: 18px;
        color: $font-icon-color;
        line-height: 1.5em;
      }
    }
  }
}
</style>
