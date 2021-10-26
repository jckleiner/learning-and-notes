<template>
  <div id="parent">
    <h1>Parent</h1>
    <p>Name: {{ name }}</p>
    <p>Age: {{ age }}</p>
    <button v-on:click="changeData">Change data</button>
    <!-- Styleguide: if a component does not have content, it should be self closed -->
    <child :name="name" :age="age" propF="asd" non-prop="blabla"/>
  </div>
</template>

<script lang="ts">
import { defineComponent } from '@vue/runtime-core'
import Child from '@/components/2-CommunicationBetweenComponents/2-Events/Child.vue'

export default defineComponent({
  name: 'Parent',
  components: {
    Child
  },
  data () {
    return {
      name: 'Wario',
      age: 30
    }
  },
  methods: {
    changeData () {
      if (this.name === 'Mario') {
        this.name = 'Luigi'
        this.age = 40
      } else {
        this.name = 'Mario'
        this.age = 30
      }
    }
  }
})

</script>

<style lang="scss">

#parent {
  background-color: lightpink;
  width: 80%;
  margin: auto;
}

</style>
