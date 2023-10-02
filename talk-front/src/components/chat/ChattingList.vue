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
  </div>
</template>

<script>
import {selectRoomList} from "@/request/room";

export default {
  name: 'ChattingListview',
  data() {
    return {
      chattingRoomList: []
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
    }
  },
  created() {
    this.selectRoomList();
  }
}
</script>

<style scoped>

</style>