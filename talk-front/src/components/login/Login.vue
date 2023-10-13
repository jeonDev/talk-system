<template>
  <div>
    <b-form-input
        v-model="loginRequest.id"
        type="text"
        class="mb-1"
        placeholder="ID"
        maxlength="16"
        @keyup.enter="loginClick"
    />
    <b-form-input
        v-model="loginRequest.password"
        type="password"
        class="mb-1"
        placeholder="PASSWORD"
        maxlength="20"
        @keyup.enter="loginClick"
    />
    <b-button
        class="w-100"
        variant="outline-success"
        @click="loginClick"
    >
      Login
    </b-button>
  </div>
</template>

<script>
import {login} from "@/request/login";
import {checkId, checkPassword, MessageEnum} from "@/utils/utils";

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
      // 정규식 체크
      if(!this.parameterCheck()) return;
      // login 요청
      const res = await login(this.loginRequest);
      if(res.status === 'SUCCESS') {
        const accessToken = res.data.tokenInfo.token;
        const userSeq = res.data.userSeq;
        const name = res.data.name;
        const nickname = res.data.nickname;
        sessionStorage.setItem('userSeq', userSeq);
        sessionStorage.setItem('name', name);
        sessionStorage.setItem('nickname', nickname);
        sessionStorage.setItem('Authorization', accessToken);
        window.location.href = "/main";
      } else {
        this.modalSetting(res.status, res.message, () => {})
      }
    },
    parameterCheck() {
      let isCheck = true;
      if(!checkId(this.loginRequest.id)) {
        this.modalSetting('ID_CHECK', MessageEnum.ID_CHECK, () => {});
        isCheck = false;
      } else if (!checkPassword(this.loginRequest.password)) {
        this.modalSetting('PASSWORD_CHECK', MessageEnum.PASSWORD_CHECK, () => {});
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