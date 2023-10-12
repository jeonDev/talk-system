<template>
  <div>
    <div class="bg-white overflow-scroll scroll" style="height: 300px">
      <div
          v-for="(item, idx) in chattingList"
          :key="idx"
      >
        <div class="d-flex"
             :class="[item.userInfo.userSeq == getUserInfo ? 'send-msg-box' : 'receive-msg-box']"
        >
          <div class="profile-box align-self-center p-2 m-1">
            <span class="profile-msg-box">{{item.userInfo.nickname}}</span>
          </div>
          <div class="p-2 m-2 w-75 msg-box">
                <span v-if="item.messageType == 'MESSAGE'">
              {{item.data}}
            </span>

          </div>

        </div>
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
    },
    getUserInfo: function () {
      return sessionStorage.getItem("userSeq");
    }
  },
  methods: {
    async selectRoomChattingList() {
      const result = await selectRoomChattingList(this.roomSeq);
      if(result.status == 'SUCCESS') {
        if(result.data != null && result.data.length > 0) this.$store.commit('CHATTING_LIST_RESET', result.data);
        else this.$store.commit('CHATTING_LIST_RESET', []);
      }
    },
    sendMsg() {
      this.$store.commit('SOCKET_SEND_MESSAGE', JSON.stringify({'type': 'MESSAGE', 'roomSeq': this.roomSeq, 'message': this.message}));
      this.message = ''
    }
  },
  created() {
    this.$store.commit('SET_ROOM_ENTRANCE', this.roomSeq);
    this.selectRoomChattingList();
  }
}
</script>
<style scoped>
.send-msg-box {
  justify-content: end;
  flex-direction: row-reverse;
}
.receive-msg-box {
  justify-content: start;
}
.send-msg-box .msg-box{
  background: #DFCCFB;
  border-radius: 10px;
}
.receive-msg-box .msg-box{
  background: #FFF8C9;
  border-radius: 10px;
}

.profile-box {
  width: 50px;
  height: 40px;
  border: 1px solid #DFCCFB;
  border-radius: 15px;
  overflow:hidden;
  text-overflow:ellipsis;
  white-space:nowrap;
}
.profile-box .profile-msg-box {
  top: 25%;
}

/* ( 크롬, 사파리, 오페라, 엣지 ) 동작 */
.scroll::-webkit-scrollbar {
  display: none;
}

.scroll {
  -ms-overflow-style: none; /* 인터넷 익스플로러 */
  scrollbar-width: none; /* 파이어폭스 */
}
</style>