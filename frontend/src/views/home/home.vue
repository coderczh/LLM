<template>
  <div class="home">
    <div class="left">
      <tab />
    </div>
    <div class="right">
      <content />
    </div>
  </div>
</template>

<script lang="ts" setup>
import tab from '@/components/tab/tab.vue'
import content from '@/components/content/content.vue'
import 'md-editor-v3/lib/preview.css'
</script>

<style lang="scss" scoped>
.home {
  background: $theme-color-to-bottom;
  display: flex;
  justify-content: center;
  align-items: center;
  width: 100vw;
  height: 100vh;
  .left {
    width: 4vw;
  }
  .right {
    padding: 10px 10px 10px 0;
    flex: 1;
  }
}
</style>
