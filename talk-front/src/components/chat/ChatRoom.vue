<template>
  <div>
    {{roomSeq}}
  </div>
</template>

<script>
import {selectRoomChattingList} from "@/request/chatting";

export default {
  name: 'ChatRoomView',
  props: [ 'roomSeq' ],
  data() {
    return {
      chattingList: []
    }
  },
  methods: {
    async selectRoomChattingList() {
      const result = await selectRoomChattingList(this.roomSeq);
      if(result.status == 'SUCCESS') {
        this.chattingList = result.data;
      }
    }
  },
  created() {
    this.selectRoomChattingList();
    // this.$store.state.socketStore.socket.emit('connect');
  }
}
</script>

<style scoped>

</style>