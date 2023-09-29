<template>
  <div>
    <b-form-input
        v-model="signupRequest.id"
        type="text"
        class="mb-1"
        placeholder="ID"
        maxlength="16"
    />
    <b-form-input
        v-model="signupRequest.password"
        type="password"
        class="mb-1"
        placeholder="PASSWORD"
        maxlength="20"
    />
    <b-form-input
        v-model="signupRequest.name"
        type="text"
        class="mb-1"
        placeholder="NAME"
        maxlength="30"
    />
    <b-form-input
        v-model="signupRequest.nickname"
        type="text"
        class="mb-1"
        placeholder="NICKNAME"
        maxlength="10"
    />
    <b-form-input
        v-model="signupRequest.phone"
        type="tel"
        class="mb-1"
        placeholder="PHONE"
        maxlength="11"
    />
    <b-form-input
        v-model="signupRequest.email"
        type="email"
        class="mb-1"
        placeholder="EMAIL"
        maxlength="100"
    />
    <b-button
        variant="outline-success"
        class="w-100"
        @click="signUpClick"
    >
      회원가입
    </b-button>

    <b-modal v-model="modal.isShow">
      [{{modal.code}}] {{modal.message}}
    </b-modal>
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
      },
      modal : {
        code: '',
        message: '',
        isShow: false
      }
    }
  },
  methods: {
    async signUpClick() {

      // 정규식 체크
      if(!checkId(this.signupRequest.id)) {
        this.setErrorMsg('ID_CHECK', MessageEnum.ID_CHECK);
        return;
      } else if (!checkPassword(this.signupRequest.password)) {
        this.setErrorMsg('PASSWORD_CHECK', MessageEnum.PASSWORD_CHECK);
        return;
      } else if (this.signupRequest.name == null || this.signupRequest.name === '') {
        this.setErrorMsg('NAME_CHECK', MessageEnum.NAME_CHECK);
        return;
      } else if (this.signupRequest.nickname == null || this.signupRequest.nickname === '') {
        this.setErrorMsg('NICKNAME_CHECK', MessageEnum.NICKNAME_CHECK);
        return;
      } else if (!checkPhoneNumber(this.signupRequest.phone)) {
        this.setErrorMsg('PHONE_CHECK', MessageEnum.PHONE_CHECK);
        return;
      } else if (!checkEmail(this.signupRequest.email)) {
        this.setErrorMsg('EMAIL_CHECK', MessageEnum.EMAIL_CHECK);
        return;
      }

      const res = await signup(this.signupRequest);
      if(res.status === 'SUCCESS') {
        this.$router.push({
          name: "Login"
        })
      } else {
        this.setErrorMsg(res.status, res.message);
      }
    },
    setErrorMsg(code, message) {
      this.modal.code = code;
      this.modal.message = message;
      this.showModal();
    },
    showModal() {
      this.modal.isShow = true;
    },
    hideModal() {
      this.modal.isShow = false;
    }
  }
}

</script>

<style scoped>

</style>