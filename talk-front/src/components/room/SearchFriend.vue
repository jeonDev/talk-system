<template>
  <div>
    <div class="d-flex justify-content-between">
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
      </div>
    </div>
  </div>
</template>

<script>
import {selectFriendList} from "@/request/friend";

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
      const result = await selectFriendList(this.searchInfo.nameOrNickname);
      if(result.status === 'SUCCESS') {
        console.log(result);
        console.log(result.data);
        this.friendList = result.data;
      }
    }
  }
}
</script>

<style scoped>

</style>