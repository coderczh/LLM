<template>
  <div class="common-layout">
    <el-container>
      <el-aside width="250px">
        <left-aside />
      </el-aside>
      <el-main>
        <content />
      </el-main>
    </el-container>
  </div>
</template>

<script lang="ts" setup>
import leftAside from '@/components/left-aside/left-aside.vue'
import content from '@/components/content/content.vue'
</script>

<style lang="scss" scoped></style>
