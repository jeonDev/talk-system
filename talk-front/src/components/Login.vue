<template>
  <div>
    <b-form-input
        v-model="loginRequest.id"
        type="text"
        placeholder="ID"
    />
    <b-form-input
        v-model="loginRequest.password"
        type="password"
        placeholder="PASSWORD"
    />
    <b-button
        variant="primary"
        @click="loginClick"
    >
      Login
    </b-button>
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