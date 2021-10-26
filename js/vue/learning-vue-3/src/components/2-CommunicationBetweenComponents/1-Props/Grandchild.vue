<template>
  <div id="grandchild">
    <h1>Grandchild</h1>
    <p>Name: {{ name }}</p>
    <p>Age: {{ age }}</p>
    <button disabled>Change data</button>
  </div>
</template>

<script lang="ts">
import { defineComponent } from '@vue/runtime-core'

export default defineComponent({
  name: 'Grandchild',
  props: {
    name: {
      type: String,
      required: true
    },
    age: {
      type: [String, Number],
      required: false,
      default: 55
    }
  }
})

</script>

<style lang="scss">
#grandchild {
  width: 70%;
  margin: auto;
  background-color: lightgreen;
}
</style>
