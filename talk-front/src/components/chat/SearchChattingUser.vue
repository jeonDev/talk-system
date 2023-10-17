<template>
  <div class="border border-secondary p-2 h-100" style="border-radius: 10px">

    <!-- 초대할 멤버 -->
    <div v-for="(item, idx) in roomUserList" :key="idx">
      {{item}}
    </div>
    <!-- Search -->
    <div style="height: 50px">
      <div class="d-flex justify-content-between p-2 align-self-center">
        <div class="w-75 m-auto">
          <b-form-input

              placeholder="name or nickname"
          />
        </div>
        <div>
          <b-button
              class="w-100"
              variant="outline-success"
              @click="selectFriendList"
          >
            찾기
          </b-button>
        </div>
      </div>
    </div>
    <hr/>
    <!-- Select -->
    <div style="height: 100px;">
      <div v-for="(item, idx) in friendList"
           :key="idx"
           style="cursor: pointer"
           @click="addChatUser(item)"
      >
        <div>
          {{item.name}}
        </div>

      </div>
    </div>
  </div>
</template>

<script>
import {selectFriendList} from "@/request/friend";

export default {
  name: 'SearchChattingUserView',
  data() {
    return {
      roomUserList: [],
      friendList: []
    }
  },
  methods: {
    async selectFriendList() {
      const result = await selectFriendList();
      if(result.status === 'SUCCESS') {
        this.friendList = result.data;
      }
    },
    addChatUser(item) {
      // this.roomUserList.push(item);
      console.log(item)
    }
  }
}
</script>


<style scoped>
hr{
  margin:0;
}
</style>