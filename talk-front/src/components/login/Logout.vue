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
      sessionStorage.removeItem("Authorization");
      this.$router.push({name: "Login"})
    }
  },
  created() {
    this.logout();
  }
}
</script>