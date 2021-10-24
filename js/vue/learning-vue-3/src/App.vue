<!--
  Template Interpolation - prop validation (checking if prop type is correct and if it is present, if required) only works when using
    1. JS file with export default {...}
    2. TS file with defineComponent in Vue 3 or Vue.extend in Vue 2
  (See https://vuejs.github.io/vetur/guide/interpolation.html#prop-type-validation)

  This means the props WILL NOT HAVE prop validation with Vue 3 and vue-class-component.
  See the issue: https://github.com/vuejs/vetur/issues/2344
 -->
<template>
  <!-- With Vue 3, you can have multiple root elements in a template -->
  <img alt="Vue logo" src="./assets/logo.png">
  <!--
    Component names can also be written like this:
      <HelloWorld :msg="This is a message"/>
    But the official documentation writes it kebab-case
  -->
  <hello-world />
</template>

<script lang="ts">
import { Options, Vue } from 'vue-class-component'
import HelloWorld from './components/HelloWorld.vue'

@Options({
  components: {
    HelloWorld
  }
})
export default class App extends Vue {
  bla = 1234

  beforeCreate () {
    console.log('beforeCreate', this.bla)
  }

  created () {
    console.log('created', this.bla)
  }
}
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
