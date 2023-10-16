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
      <div v-for="(item, idx) in friendList" :key="idx"
           class="d-flex justify-content-between m-2"
      >
        <div>
          <div>
            <span class="fw-bold">{{item.nickname}}</span>
            <span style="font-size: 12px"> ({{item.name}})</span>
          </div>
          <div v-if="!isStringEmpty(item.friendUserSeq)"
               class="text-secondary"
               style="font-size: 10px"
          >
            나에게 친구요청한 고객입니다
          </div>
        </div>
        <b-button
            :variant="[isStringEmpty(item.friendUserSeq) ? 'outline-dark' : 'outline-primary']"
            @click="requestFriend(item.userSeq)"
        >
          친구요청
        </b-button>
      </div>
    </div>

    <div v-if="page.totalPage > 0">
      <b-pagination
          v-model="page.currentPage"
          align="center"
          :total-rows="page.totalPage"
          :per-page="page.perPage"
          @click="searchFriendList"
      />
    </div>
  </div>
</template>

<script>
import {requestFriend, selectRecommendFriendList} from "@/request/friend";
import {isStringEmpty, modalSetting} from "@/utils/utils";

export default {
  name: 'SearchFriendView',
  data() {
    return {
      searchInfo: {
        nameOrNickname: ''
      },
      friendList: [],
      page: {
        currentPage: 1,
        totalPage: 0,
        perPage: 10
      }
    }
  },
  methods: {
    isStringEmpty,
    async searchFriendList() {
      const request = {
        nameOrNickname: this.searchInfo.nameOrNickname,
        currentPage : this.page.currentPage,
        perPage : this.page.perPage
      }
      const result = await selectRecommendFriendList(request);
      if(result.status === 'SUCCESS') {
        this.friendList = result.data.data;
        this.page.totalPage = result.data.totalPage;
      }
    },
    async requestFriend(userSeq) {
      const result = await requestFriend({ userSeq : userSeq });
      modalSetting(result.status, result.message, () => {this.searchFriendList()});
    }
  }
}
</script>

<style scoped>
hr{margin:0}
</style>