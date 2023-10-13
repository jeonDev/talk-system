<template>
  <div>
    <div class="d-flex justify-content-between p-2 align-self-center">
      <div class="w-75 m-auto">
        <b-form-input
            v-model="searchInfo.nameOrNickname"
            placeholder="name or nickname"
        />
      </div>
      <div>
        <b-button
            class="w-100"
            variant="outline-success"
            @click="searchFriendList"
          >
          찾기
        </b-button>
      </div>
    </div>
    <hr/>
    <div>
      <div v-for="(item, idx) in friendList" :key="idx">
        {{item.id}}
        {{item.name}}
        {{item.nickname}}
        <b-button
            variant="outline-dark"
            @click="requestFriend(item.userSeq)"
        >
          친구요청
        </b-button>
      </div>
    </div>
  </div>
</template>

<script>
import {requestFriend, selectRecommendFriendList} from "@/request/friend";
import {modalSetting} from "@/utils/utils";

export default {
  name: 'SearchFriendView',
  data() {
    return {
      searchInfo: {
        nameOrNickname: ''
      },
      friendList: []
    }
  },
  methods: {
    async searchFriendList() {
      const result = await selectRecommendFriendList(this.searchInfo.nameOrNickname);
      if(result.status === 'SUCCESS') {
        this.friendList = result.data;
      }
    },
    async requestFriend(userSeq) {
      const result = await requestFriend({ userSeq : userSeq });
      modalSetting(result.status, result.message, () => {});
    },

  }
}
</script>

<style scoped>
hr{margin:0}
</style>