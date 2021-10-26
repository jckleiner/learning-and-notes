<template>
  <!-- With Vue 3, you can have multiple root elements in a template -->
  <!-- <img alt="Vue logo" src="./assets/logo.png"> -->
  <!--
    Component names can also be written like this:
      <HelloWorld :msg="This is a message"/>
    But the official documentation writes it kebab-case
  -->
  <parent />
</template>

<script lang="ts">
import { defineComponent } from 'vue'
import Parent from '@/components/2-CommunicationBetweenComponents/1-Props/Parent.vue'

export default defineComponent({
  name: 'App', // optional
  components: {
    Parent
  },
  data () {
    return {
      bla: '12312312312312312'
    }
  }
})
</script>

<style lang="scss">
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
</style>
