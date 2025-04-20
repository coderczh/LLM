<template>
  <div class="content">
    <div class="left">
      <div class="top">
        <el-button round color="#3a59d1" style="width: 155px">
          <el-icon color="#fff"><Plus /></el-icon>
          <text style="padding-left: 3px; line-height: 1.2em">新建对话</text>
        </el-button>
        <el-button :icon="Search" circle />
      </div>
    </div>
    <div class="right">4444</div>
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
  }
}
</style>
