<template>
  <div>
    <input v-model="signupRequest.id">
    <input v-model="signupRequest.password">
    <input v-model="signupRequest.name">
    <input v-model="signupRequest.nickname">
    <input v-model="signupRequest.phone">
    <input v-model="signupRequest.email">
    <button @click="signUpClick">회원가입</button>
  </div>
</template>

<script>
import {signup} from "@/request/login";

export default {
  name: 'SignupView',
  data() {
    return {
      signupRequest: {
        id: '',
        password: '',
        name: '',
        nickname: '',
        phone: '',
        email: ''
      }
    }
  },
  methods: {
    signUpClick() {
      signup(this.signupRequest);
    }
  }
}

</script>

<style scoped>

</style>