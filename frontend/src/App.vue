<template>
  <div class="app">
    <router-view></router-view>
  </div>
</template>

<script lang="ts" setup>
import 'md-editor-v3/lib/preview.css'
</script>

<style lang="scss">
html,
body {
  height: 100vh;
  width: 100vw;
  margin: 0px;
}

* {
  box-sizing: border-box;
}

:root {
  --el-main-padding: 0px;
}
</style>
