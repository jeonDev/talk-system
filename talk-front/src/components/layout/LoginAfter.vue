<template>
  <div>
    <b-nav class="d-flex justify-content-between">
      <div>
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
    <section>
      <router-view></router-view>
    </section>
  </div>
</template>

<script setup>

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
</style>