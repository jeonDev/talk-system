<template>
  <div>
    <input v-model="loginRequest.id">
    <input v-model="loginRequest.password">
    <button @click="loginClick">login</button>
  </div>
</template>

<script>
import {login} from "@/request/login";

export default {
  name: 'LoginView',
  data() {
    return {
      loginRequest : {
        id: '',
        password: ''
      }
    }
  },
  methods: {
    loginClick() {
      login(this.loginRequest);
    }
  }
}
</script>

<style scoped>

</style>