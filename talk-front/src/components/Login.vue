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
    async loginClick() {
      const res = await login(this.loginRequest);
      if(res.status === 'SUCCESS') {
        console.log(res);
      }
    }
  }
}
</script>

<style scoped>

</style>