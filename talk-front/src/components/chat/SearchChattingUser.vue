<template>
  <div class="border border-secondary p-2 h-100" style="border-radius: 10px">

    <!-- 초대할 멤버 -->
    <div class="d-flex justify-content-start mw-100 overflow-x-auto">
      <div v-for="(item, idx) in roomUserList"
           :key="idx"
           class="border border-black p-1 m-1"
           style="border-radius: 10px; width: 73px"
      >
        <span
            class="text-line"
            style="font-size: 12px; width: 60px"
        >
          {{item.name}}
        </span>
        <span
            class="p-1"
            style="font-size: 8px; cursor: pointer"
            @click="deleteChatUser(item)"
        >
           X
        </span>
      </div>
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
    <div class="overflow-y-scroll" style="height: 120px;">
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
    <div class="d-flex justify-content-between">
      <b-button
          variant="outline-success"
          class="w-50"
          @click="cancelSearchUserView"
      >
        취소
      </b-button>
      <b-button
          variant="success"
          class="w-50"
          @click="createUsersRoom"
      >
        생성
      </b-button>
    </div>
  </div>
</template>

<script>
import {selectFriendList} from "@/request/friend";
import {createRoom} from "@/request/room";

export default {
  name: 'SearchChattingUserView',
  props: {
    cancelSearchView: Function
  },
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
      const obj = {userSeq: item.userSeq, name: item.name, nickname: item.nickname};
      const isCheck = this.roomUserList.filter(e => e.userSeq == obj.userSeq).length > 0;
      if(!isCheck) {
        this.roomUserList.push(obj);
      }
    },
    deleteChatUser(item) {
      this.roomUserList = this.roomUserList.filter(e => e.userSeq != item.userSeq);
    },
    cancelSearchUserView() {
      this.$emit("cancelSearchUserView");
    },
    async createUsersRoom() {
      const result = await createRoom(this.roomUserList);
      if(result.status == 'SUCCESS') {
        this.$router.push({name: "ChattingRoom", params: {roomSeq: result.data.roomSeq}});
      }
    }
  },
  created() {
    this.selectFriendList();
  }
}
</script>


<style scoped>
hr{
  margin:0;
}
.text-line {
  overflow:hidden;
  text-overflow:ellipsis;
  white-space:nowrap;
}
</style>