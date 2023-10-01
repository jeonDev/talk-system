<template>
  <div>
    <div v-for="(item, idx) in friendList"
         :key="idx"
         class="d-flex justify-content-between mb-1 p-2 border-bottom border-secondary-subtle align-items-center"
    >
      <div>
        <span class="fw-bold">{{item.nickname}}</span>
        <span class="text-secondary"> ({{item.name}}) </span>
      </div>
      <div>
        <b-button
            variant="outline-dark"
        >
          대화하기
        </b-button>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'FriendListView',
  props: [ 'friendList' ]
}
</script>