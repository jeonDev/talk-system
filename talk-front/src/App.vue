<template>
  <div id="app">
    <div class="talk-bg">
      <!-- Login Before  -->
      <div class="talk-app">
        <div class="talk-app-main">
          <LoginBefore/>
        </div>
      </div>
    </div>

  </div>
</template>

<script>

import LoginBefore from "@/components/layout/LoginBefore.vue";

export default {
  name: 'App',
  components: {
    LoginBefore
  }
}
</script>

<style>
.talk-bg {
  width: 100vw;
  height: 100vh;
  background-color: #B4B4B3;
}
.talk-app {
  height: 100%;
  margin: auto;
  padding-top: 60px;
  padding-bottom: 60px;
  max-width: 500px;
  background-color: #EBE4D1;
}
.talk-app-main {
  width: 90%;
  margin: auto;
}
</style>
