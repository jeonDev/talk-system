<template>
  <div>
    <b-form-input
        v-model="signupRequest.id"
        type="text"
        placeholder="ID"
    />
    <b-form-input
        v-model="signupRequest.password"
        type="password"
        placeholder="PASSWORD"
    />
    <b-form-input
        v-model="signupRequest.name"
        type="text"
        placeholder="NAME"
    />
    <b-form-input
        v-model="signupRequest.nickname"
        type="text"
        placeholder="NICKNAME"
    />
    <b-form-input
        v-model="signupRequest.phone"
        type="tel"
        placeholder="PHONE"
    />
    <b-form-input
        v-model="signupRequest.email"
        type="email"
        placeholder="EMAIL"
    />
    <b-button
        variant="primary"
        @click="signUpClick"
    >
      회원가입
    </b-button>
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