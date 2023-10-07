<template>
  <div>
    <div class="bg-white overflow-scroll" style="height: 300px">
      <div v-for="(item, idx) in chattingList" :key="idx">
        {{item}}
      </div>
    </div>

    <div class="fixed-bottom m-auto p-2" style="width: 500px; height: 100px">
      <hr/>
      <div class="d-flex p-2">
        <b-form-input
            v-model="message"
            class="me-3"
        />
        <b-button
            variant="outline-dark"
            @keyup.enter="sendMsg"
            @click="sendMsg"
        >
          Send
        </b-button>
      </div>
    </div>
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
      message : ''
    }
  },
  computed: {
    socketMsg: function () {
      return this.$store.state.socketStore.message;
    }
  },
  watch: {
    socketMsg(value) {
      this.chattingList.push(value);
      this.message = ''
    }
  },
  methods: {
    async selectRoomChattingList() {
      const result = await selectRoomChattingList(this.roomSeq);
      if(result.status == 'SUCCESS') {
        // this.chattingList = result.data;
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