<template>
  <div>
    <b-form-input
        v-model="signupRequest.id"
        type="text"
        class="mb-1"
        placeholder="ID"
        maxlength="16"
        @keyup.enter="signUpClick"
    />
    <b-form-input
        v-model="signupRequest.password"
        type="password"
        class="mb-1"
        placeholder="PASSWORD"
        maxlength="20"
        @keyup.enter="signUpClick"
    />
    <b-form-input
        v-model="signupRequest.name"
        type="text"
        class="mb-1"
        placeholder="NAME"
        maxlength="30"
        @keyup.enter="signUpClick"
    />
    <b-form-input
        v-model="signupRequest.nickname"
        type="text"
        class="mb-1"
        placeholder="NICKNAME"
        maxlength="10"
        @keyup.enter="signUpClick"
    />
    <b-form-input
        v-model="signupRequest.phone"
        type="tel"
        class="mb-1"
        placeholder="PHONE"
        maxlength="11"
        @keyup.enter="signUpClick"
    />
    <b-form-input
        v-model="signupRequest.email"
        type="email"
        class="mb-1"
        placeholder="EMAIL"
        maxlength="100"
        @keyup.enter="signUpClick"
    />
    <b-button
        variant="outline-success"
        class="w-100"
        @click="signUpClick"
    >
      회원가입
    </b-button>
  </div>
</template>

<script>
import {signup} from "@/request/login";
import {checkEmail, checkId, checkPassword, checkPhoneNumber, MessageEnum} from "@/utils/utils";

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
    async signUpClick() {

      // 정규식 체크
      if(!this.parameterCheck()) return;

      const res = await signup(this.signupRequest);

      if(res.status === 'SUCCESS') {
        this.modalSetting(res.status, res.message, () => this.$router.push({name: "Login"}));
      }
      else this.modalSetting(res.status, res.message, () => {});

    },
    parameterCheck() {
      let isCheck = true;
      // 정규식 체크
      if(!checkId(this.signupRequest.id)) {
        this.modalSetting('ID_CHECK', MessageEnum.ID_CHECK, () => {});
        isCheck = false;
      } else if (!checkPassword(this.signupRequest.password)) {
        this.modalSetting('PASSWORD_CHECK', MessageEnum.PASSWORD_CHECK, () => {});
        isCheck = false;
      } else if (this.signupRequest.name == null || this.signupRequest.name === '') {
        this.modalSetting('NAME_CHECK', MessageEnum.NAME_CHECK, () => {});
        isCheck = false;
      } else if (this.signupRequest.nickname == null || this.signupRequest.nickname === '') {
        this.modalSetting('NICKNAME_CHECK', MessageEnum.NICKNAME_CHECK, () => {});
        isCheck = false;
      } else if (!checkPhoneNumber(this.signupRequest.phone)) {
        this.modalSetting('PHONE_CHECK', MessageEnum.PHONE_CHECK, () => {});
        isCheck = false;
      } else if (!checkEmail(this.signupRequest.email)) {
        this.modalSetting('EMAIL_CHECK', MessageEnum.EMAIL_CHECK, () => {});
        isCheck = false;
      }
      return isCheck;
    },
    modalSetting(code, message, callback) {
      this.$store.commit('showModal', {code: code, message: message, callback: callback});
    }
  }
}

</script>

<style scoped>

</style>