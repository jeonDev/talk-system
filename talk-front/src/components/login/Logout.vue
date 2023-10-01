<template>
  <div>
    나 로그아웃
  </div>
</template>

<script>
export default {
  name: 'LogoutView',
  methods: {
    logout() {
      sessionStorage.removeItem('name');
      sessionStorage.removeItem('nickname');
      sessionStorage.removeItem("Authorization");
      window.location.href = "/login"
      // this.$router.push({name: "Login"})
    }
  },
  created() {
    this.logout();
  }
}
</script>