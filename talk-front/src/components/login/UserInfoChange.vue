<template>
  <div>
    <div class="mt-2">
      <b-form-input
          v-model="userInfo.oldPassword"
          type="password"
          class="mb-1"
          placeholder="OLD_PASSWORD"
          maxlength="20"
      />
      <b-form-input
          v-model="userInfo.password"
          type="password"
          class="mb-1"
          placeholder="NEW_PASSWORD"
          maxlength="20"
      />
      <b-form-input
          v-model="userInfo.name"
          type="text"
          class="mb-1"
          placeholder="NAME"
          maxlength="30"
      />
      <b-form-input
          v-model="userInfo.nickname"
          type="text"
          class="mb-1"
          placeholder="NICKNAME"
          maxlength="10"
      />
      <b-form-input
          v-model="userInfo.phone"
          type="tel"
          class="mb-1"
          placeholder="PHONE"
          maxlength="11"
      />
      <b-form-input
          v-model="userInfo.email"
          type="email"
          class="mb-1"
          placeholder="EMAIL"
          maxlength="100"
      />
      <b-button
          variant="outline-success"
          class="w-100"
          @click="updateUserInfo"
      >
        정보변경
      </b-button>
    </div>
  </div>
</template>

<script>
import {selectUserInfo, updateUserInfo} from "@/request/login";
import {modalSetting} from "@/utils/utils";

export default {
  name: 'UserInfoChangeView',
  data() {
    return {
      userInfo : {
        oldPassword: '',
        password: '',
        name: '',
        nickname: '',
        phone: '',
        email: ''
      }
    }
  },
  methods: {
    async selectMyUserInfo() {
      const result = await selectUserInfo();
      if(result.status === 'SUCCESS') {
        this.userInfo = result.data;
      }
    },
    async updateUserInfo() {
      const result = await updateUserInfo(this.userInfo);
      if(result.status === 'SUCCESS') {
        modalSetting(result.status, result.message, () => {});
        this.userInfo = result.data;
      } else modalSetting(result.status, result.message, () => {});
    }
  },
  created() {
    this.selectMyUserInfo();
  }
}

</script>

<style scoped>

</style>