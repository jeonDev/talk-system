<template>
  <div>
    <div v-for="(item, idx) in friendList"
         :key="idx"
         class="d-flex justify-content-between mb-1 p-2 border-bottom border-secondary-subtle align-items-center"
    >
      <div>
        <span class="fw-bold">{{item.nickname}}</span>
        <span class="text-secondary"> ({{item.name}}) </span>
      </div>
      <div>
        <b-button
            variant="outline-dark"
            @click="createRoom(item.userSeq)"
        >
          대화
        </b-button>
        <b-button
            variant="outline-dark"
            @click="friendRemove(item.userSeq)"
        >
          제거
        </b-button>
      </div>
    </div>
  </div>
</template>

<script>
import {createPrivateRoom} from "@/request/room";
import {removeFriend} from "@/request/friend";

export default {
  name: 'FriendListView',
  props: [ 'friendList' ],
  methods: {
    async createRoom(userSeq) {
      const result = await createPrivateRoom({userSeq: userSeq});

      if(result.status === 'SUCCESS') {
        this.attendChattingRoom(result.data.roomSeq);
      }
    },
    async attendChattingRoom(roomSeq) {
      this.$router.push({name: "ChattingRoom", params: {roomSeq: roomSeq}});
    },
    async friendRemove(userSeq) {
      const result = await removeFriend({userSeq: userSeq});

      if(result.status === 'SUCCESS') {
        this.$emit('deleteFriendEvent', userSeq)
      }
    }
  }
}
</script>