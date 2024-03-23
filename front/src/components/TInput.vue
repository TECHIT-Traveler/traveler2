<template>
  <div>
    <input type="text" v-model="newItem" placeholder="Type what you have to do"
        v-on:keyup.enter="add">
    <span class="addContainer" v-on:click="add">
      <i class="addBtn fas fa-plus" aria-hidden="true"></i>
    </span>
    <button v-on:click="add">추가</button>
  </div>
</template>

<script>
export default {
  data () {
    return {
      newItem: ''
    }
  },
  methods: {
    add () {
      if (this.newItem !== '') {
        var value = this.newItem && this.newItem.trim()
        localStorage.setItem(value, value)
        this.clearInput()
      }
    },
    clearInput () {
      this.newItem = ''
    }
  }
}
</script>

<style>

</style>
