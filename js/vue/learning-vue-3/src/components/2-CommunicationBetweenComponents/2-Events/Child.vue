<template>
  <div id="child">
    <h1>Child</h1>
    <p>Name: {{ name }}</p>
    <p>Age: {{ age }}</p>
    <button disabled>Change data</button>
    <br>
    <div id="info">
      <ul>
        <li>props form a <strong>one-way-down binding</strong>, the props cannot be changed/re-initialized</li>
        <li>Note that objects and arrays in JavaScript are passed by reference, so if the prop is an array or object, mutating the object or array itself inside the child component <strong>will affect parent state</strong>.</li>
      </ul>
    </div>
    <grandchild :name="name" :age="age"/>
  </div>
</template>

<script lang="ts">
import { defineComponent } from '@vue/runtime-core'
import Grandchild from '@/components/2-CommunicationBetweenComponents/2-Events/Grandchild.vue'

export default defineComponent({
  name: 'Child',
  props: {
    name: {
      type: String,
      required: true,
      // Custom validator function
      validator (value: string) {
        console.log('name validator', value)
        return ['Mario', 'Luigi'].includes(value)
      }
    },
    age: {
      type: [String, Number],
      required: false,
      default: 55
    },
    // Object with a default value
    propE: {
      type: Object,
      // Object or array defaults must be returned from
      // a factory function
      default () {
        return { message: 'hello' }
      }
    },
    propF: {
      type: String,
      validator (value: string) {
        console.log('propF validator', value)
        return ['success', 'warning', 'danger'].includes(value)
      }
    },
    // Function with a default value
    propG: {
      type: Function,
      // Unlike object or array default, this is not a factory function - this is a function to serve as a default value
      default () {
        return 'Default function'
      }
    }
  },
  components: {
    Grandchild
  },
  methods: {
    changeData () {
      // Cannot assign to 'name' because it is a read-only property.
      // this.name = 'Wario'
      // this.age = 45
    }
  },
  mounted () {
    console.log('Child: mounted(), propF', this.propF)
  }
})

</script>

<style lang="scss">
#child {
  width: 80%;
  margin: auto;
  background-color: lightblue;
}

#info {
  width: 70%;
  margin: auto;
}
</style>
