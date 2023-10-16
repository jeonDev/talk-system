<template>
  <div id="app">
    <div class="talk-bg">
      <LoadingView/>
      <div class="d-flex talk-app">
        <div class="talk-app-main">
          <!-- Login Before  -->
          <div v-if="isAuthentication">
            <LoginBefore/>
          </div>
          <div v-else>
            <LoginAfter/>
          </div>
        </div>
      </div>
      <ModalView/>
    </div>
  </div>
</template>

<script>

import LoginBefore from "@/components/layout/LoginBefore.vue";
import LoginAfter from "@/components/layout/LoginAfter.vue";
import {isStringEmpty} from "@/utils/utils";
import ModalView from "@/components/layout/Modal.vue";
import LoadingView from "@/components/layout/Loading.vue";

export default {
  name: 'App',
  methods: {isStringEmpty},
  components: {
    LoadingView,
    ModalView,
    LoginAfter,
    LoginBefore
  },
  computed: {
    isAuthentication: () => {
      return isStringEmpty(sessionStorage.getItem('Authorization'));
    }
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
  max-width: 500px;
  background-color: #EBE4D1;
}
.talk-app-main {
  width: 90%;
  margin: auto;
}
</style>
