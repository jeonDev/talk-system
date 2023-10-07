<template>
  <div>
    <div class="bg-white overflow-scroll" style="height: 300px">
      <div v-for="(item, idx) in chattingList" :key="idx">
        <span v-if="item.messageType == 'MESSAGE'">
          {{item.data}}
        </span>
      </div>
    </div>

    <div class="fixed-bottom m-auto p-2" style="width: 500px; height: 100px">
      <hr/>
      <div class="d-flex p-2">
        <b-form-input
            v-model="message"
            @keyup.enter="sendMsg"
            class="me-3"
        />
        <b-button
            variant="outline-dark"
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
      message : ''
    }
  },
  computed: {
    chattingList: function () {
      return this.$store.state.socketStore.chattingList;
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
      this.$store.commit('SOCKET_SEND_MESSAGE', JSON.stringify({'type': 'MESSAGE', 'message': this.message}));
      this.message = ''
    }
  },
  created() {
    this.selectRoomChattingList();
  }
}
</script>

<style scoped>

</style>