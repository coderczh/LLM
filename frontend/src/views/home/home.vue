<template>
  <div class="common-layout">
    <el-container>
      <el-aside width="250px">
        <left-aside />
      </el-aside>
      <el-main>Main</el-main>
      <el-aside width="200px">Aside</el-aside>
    </el-container>
  </div>
</template>

<script lang="ts" setup>
import leftAside from '@/components/left-aside/left-aside.vue'
</script>

<style lang="scss" scoped></style>
