<template>
  <div>
    <b-nav>
      <b-nav-item>
        <router-link
            class="nav-router"
            to="/login">
          로그인
        </router-link>
      </b-nav-item>
      <b-nav-item>
        <router-link
            class="nav-router"
            to="/signup">
          회원가입
        </router-link>
      </b-nav-item>
    </b-nav>
    <hr/>
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