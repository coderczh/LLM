<template>
  <div class="spot-type-item">
    <div class="spot-type-item-img">
      <img :src="img" style="width: 35px; height: 40px" />
    </div>
    <div class="spot-type-item-text">{{ text }}</div>
  </div>
</template>

<script lang="ts" setup>
defineProps({
  img: {
    type: String,
    required: true,
    default: '',
  },
  text: {
    type: String,
    required: true,
    default: '',
  },
})
</script>

<style lang="scss" scoped>
.spot-type-item {
  width: 90px;
  height: 100px;
  background-color: #fff;
  margin: 10px 10px;
  border-radius: 10px;
  .spot-type-item-img {
    width: 100%;
    height: 70px;
    display: flex;
    justify-content: center;
    align-items: center;
  }
  .spot-type-item-text {
    font-size: 14px;
    font-weight: 500;
    display: flex;
    justify-content: center;
    color: $font-icon-color;
  }
}
</style>
