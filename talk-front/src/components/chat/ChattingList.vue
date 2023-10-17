<template>
  <div>
    <div v-for="(item, idx) in chattingRoomList"
         :key="idx"
         class="d-flex justify-content-between mb-1 p-2 border-bottom border-secondary-subtle align-items-center"
         role="button"
         @click="attendChattingRoom(item.roomSeq)"
    >
      <div>
        <!-- 채팅 참가자 -->
        <div>
          {{item.roomTitle}}
        </div>
        <!-- 채팅 마지막 대화 -->
        <div>

        </div>
      </div>
      <!-- 채팅 마지막 일시 -->
      <div>

      </div>

    </div>
    <!-- 채팅방 생성 -->
    <div
        class="fixed-bottom m-auto"
        style="width: 500px"
    >
      <div
          class="d-flex justify-content-end m-2"
          style="height: 200px"
          v-if="isSearchUserView"
      >
        <SearchChattingUserView/>
      </div>
      <div class="d-flex justify-content-end">
        <div
            class="m-3 p-3 border border-secondary bg-light"
            style="cursor: pointer; font-size: 22px; border-radius: 22px"
            @click="selectFriendList"
        >
          +
        </div>
      </div>


    </div>
  </div>
</template>

<script>
import {selectRoomList} from "@/request/room";
import SearchChattingUserView from "@/components/chat/SearchChattingUser.vue";

export default {
  name: 'ChattingListView',
  components: {SearchChattingUserView},
  data() {
    return {
      chattingRoomList: [],
      isSearchUserView: false
    }
  },
  methods: {
    async selectRoomList() {
      const result = await selectRoomList();
      if(result.status === 'SUCCESS') {
        this.chattingRoomList = result.data;
      }
    },
    async attendChattingRoom(roomSeq) {
      this.$router.push({name: "ChattingRoom", params: {roomSeq: roomSeq}});
    },
    async selectFriendList() {
      this.isSearchUserView = !this.isSearchUserView;
    }
  },
  created() {
    this.selectRoomList();
  }
}
</script>

<style scoped>

</style>