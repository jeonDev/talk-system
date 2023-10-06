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

    <b-modal
        hide-footer
        v-model="modal.isShow"
    >
      [{{modal.code}}] {{modal.message}}
    </b-modal>

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
      },
      modal : {
        code: '',
        message: '',
        isShow: false
      }
    }
  },
  methods: {
    async loginClick() {
      // 정규식 체크
      if(!checkId(this.loginRequest.id)) {
        this.setErrorMsg('ID_CHECK', MessageEnum.ID_CHECK);
        return;
      } else if (!checkPassword(this.loginRequest.password)) {
        this.setErrorMsg('PASSWORD_CHECK', MessageEnum.PASSWORD_CHECK);
        return;
      }
      // login 요청
      const res = await login(this.loginRequest);
      if(res.status === 'SUCCESS') {
        const accessToken = res.data.tokenInfo.token;
        const name = res.data.name;
        const nickname = res.data.nickname;
        sessionStorage.setItem('name', name);
        sessionStorage.setItem('nickname', nickname);
        sessionStorage.setItem('Authorization', accessToken);
        window.location.href = "/main";
        // this.$router.push({name: "Main"})
      } else {
        this.setErrorMsg(res.status, res.message)
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