<template>
  <div>
    {{chattingList}}
    <b-form-input
        v-model="message"
    />
    <b-button
        variant="outline-dark"
        @keyup.enter="sendMsg"
        @click="sendMsg"
      >
      Send
    </b-button>
  </div>
</template>

<script>
import {selectRoomChattingList} from "@/request/chatting";

export default {
  name: 'ChatRoomView',
  props: [ 'roomSeq' ],
  data() {
    return {
      chattingList: [],
      message: ''
    }
  },
  computed: {
    socketMsg: function () {
      return this.$store.state.socketStore.message;
    }
  },
  watch: {
    socketMsg(value) {
      console.log(value);
      this.chattingList = value;
    }
  },
  methods: {
    async selectRoomChattingList() {
      const result = await selectRoomChattingList(this.roomSeq);
      if(result.status == 'SUCCESS') {
        this.chattingList = result.data;
      }
    },
    sendMsg() {
      this.$store.commit('SOCKET_SEND_MESSAGE', this.message);
    }
  },
  created() {
    this.selectRoomChattingList();
  }
}
</script>

<style scoped>

</style>