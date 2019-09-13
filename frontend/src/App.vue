<template>
  <v-app>
    <core-app-bar />

    <core-drawer />

    <core-view />

  </v-app>
</template>

<script>
  export default {
    components: {
      CoreDrawer: () => import('@/components/core/Drawer'),
      CoreAppBar: () => import('@/components/core/AppBar'),
      CoreView: () => import('@/components/core/View')
    }
  }
</script>

<style>
  .gradient-background{
    background: linear-gradient(-49deg, #736DFF, #A97FFF);
  }
  .gradient-background-lightblue{
    background: linear-gradient(146.29deg, #44DEC5 9.19%, #4EBCFA 100%);
  }
  .gradient-background-orange{
    background: linear-gradient(323.35deg, #F7C598 6.2%, #FF8886 97.74%);
  }
  .gradient-background-lightpurple{
    background: linear-gradient(147.2deg, #F9B4BD 0%, #9573DB 100%);
  }
</style>
