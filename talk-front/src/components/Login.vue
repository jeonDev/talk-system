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
        const accessToken = res.data.tokenInfo.token;
        sessionStorage.setItem('Authorization', accessToken);
        console.log(sessionStorage.getItem('Authorization'))
      }
    }
  }
}
</script>

<style scoped>

</style>