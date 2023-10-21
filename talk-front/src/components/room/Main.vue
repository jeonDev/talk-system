<template>
  <div>
    <!--  내 프로필  -->
    <div class="d-flex justify-content-between p-2 align-items-center bg-warning-subtle pt-4 pb-4">
      <div>
        <div class="fw-bold">
          <span>{{loginInfo('nickname')}}</span>
          <router-link
              class="m-1"
              to="/user/change"
          >
            <img
                src="../../assets/images/setting.png"
                style="width: 25px; height: 25px"
            >
          </router-link>
        </div>
        <div class="text-secondary" style="font-size: 12px"> ({{loginInfo('name')}}) </div>
      </div>
      <!--  친구 찾기  -->
      <div>
        <b-button
            variant="outline-dark"
        >
          <router-link to="/friend/search">친구찾기</router-link>
        </b-button>
      </div>
    </div>

    <hr/>
    <!--  친구 목록  -->
    <div>
      <FriendListView
          :friend-list="friendList"
          @deleteFriendEvent="(userSeq) => this.friendList = this.friendList.filter(item => item.userSeq !== userSeq)"
      />
    </div>

  </div>
</template>

<script>
import {nvlStr} from "@/utils/utils";
import {selectFriendList} from "@/request/friend";
import FriendListView from "@/components/room/FriendList.vue";

export default {
  name: 'MainView',
  components: {FriendListView},
  data() {
    return {
      friendList: []
    }
  },
  computed: {
    loginInfo() {
      return (item) => {
        return window.sessionStorage.getItem(nvlStr(item));
      }
    }
  },
  methods: {
    async selectFriendList() {
      const result = await selectFriendList();
      if(result.status === 'SUCCESS') {
        this.friendList = result.data;
      }
    }
  },
  created() {
    this.selectFriendList();
  }
}
</script>

<style scoped>
hr {
  margin:0
}
a {
  text-decoration: none;
}
</style>