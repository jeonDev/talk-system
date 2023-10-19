<template>
  <div :class="loadingStore.isLoading ? 'card-img-overlay bg-opacity-50 bg-dark' : ''">
    <b-overlay
        spinner-variant="primary"
        spinner-type="grow"
        spineer-samll
        rounded="sm"
        class="w-100 top-50"
        :show="loadingStore.isLoading"
    />
  </div>
</template>

<script>
export default {
  name:'LoadingView',
  computed: {
    loadingStore() {
      return this.$store.state.loadingStore;
    }
  }
}
</script>

<style scoped>

</style>