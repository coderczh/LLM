<template>
  <div class="feature">
    <el-button color="rgba(0, 0, 0, 0.05)" size="large" circle>
      <el-icon size="22"><Comment /></el-icon>
    </el-button>
    <div class="text">对话</div>
    <el-button color="rgba(0, 0, 0, 0.05)" size="large" circle style="margin-top: 30px">
      <el-icon size="22"><HelpFilled /></el-icon>
    </el-button>
    <div class="text">收藏</div>
  </div>
</template>

<script lang="ts" setup></script>

<style lang="scss" scoped>
.feature {
  padding-top: 120px;
  text-align: center;
  .text {
    padding-top: 8px;
    text-align: center;
    color: #fff;
    font-size: 13px;
    line-height: 1.2em;
    text-align: center;
  }
}
</style>
