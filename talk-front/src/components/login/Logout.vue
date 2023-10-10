<template>
  <div>
  </div>
</template>

<script>
import {logout} from "@/request/login";

export default {
  name: 'LogoutView',
  methods: {
    async logout() {
      await logout()
      sessionStorage.removeItem('userSeq');
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