<template>
  <div>
    <b-nav class="d-flex justify-content-between p-1">
      <div class="d-flex">
        <b-nav-item>
          <router-link
              class="nav-router"
              to="/main">
            메인
          </router-link>
        </b-nav-item>
        <b-nav-item>
          <router-link
              class="nav-router"
              to="/chatting/list">
            채팅방
          </router-link>
        </b-nav-item>
      </div>
      <div>
        <b-nav-item>
          <router-link
              class="nav-router"
              to="/logout">
            로그아웃
          </router-link>
        </b-nav-item>
      </div>
    </b-nav>
    <hr/>
    <section class="container-fluid min-vh-100">
      <router-view></router-view>
    </section>
  </div>
</template>

<script>
export default {
  name: 'LoginAfterView',
  created() {
    this.$store.commit('SOCKET_CONNECT');
  }
}
</script>

<style scoped>
.nav-router{
  color: #26577C;
  text-decoration: none;
  font-size: 15px;
  padding:5px;
}
.nav-router:hover {
  color: #E55604;
  font-size: 16px;
}
hr{
  margin:0
}
</style>