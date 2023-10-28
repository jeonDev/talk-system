<template>
  <div>
    <b-modal
        hide-footer
        v-model="modalStore.isShow"
        @hide="modalStore.callback"
    >
      [{{modalStore.code}}] {{modalStore.message}}
    </b-modal>
  </div>
</template>

<script>
export default {
  name:'ModalView',
  computed: {
    modalStore() {
      return this.$store.state.modalStore;
    }
  }
}
</script>

<style scoped>

</style>