# learning-vue-3

### Vue 3 with `vue-property-decorator` and `vue-class-component`
Atm (24 October 2021), it seems like the class component is not greatly supported and therefore the propert decorator is also not fully working since it depends on the API which is provided by `vue-class-component`. And it is also not sure if `vue-class-component` will ever come to a point where it supports all features provided by Vue 3.

> The best thing to do is to use the new vue 3.2 version [script setup](https://v3.vuejs.org/api/sfc-script-setup.html#basic-syntax) that is the best way to use vue with typescript.

See issue: https://github.com/kaorun343/vue-property-decorator/issues/294

### Vetur - template Interpolation (prop validation)
Checking if prop type is correct and if it is present (when `required: true`). Only works when using:
  1. JS file with export default {...}
  2. TS file with defineComponent in Vue 3 or Vue.extend in Vue 2
(See https://vuejs.github.io/vetur/guide/interpolation.html#prop-type-validation)

This means the props WILL NOT HAVE prop validation with Vue 3 and `vue-class-component`. Which is a bummer.
The maintainer of Vetur added the issue to the `v0.36.0` milestone
See the issue: https://github.com/vuejs/vetur/issues/2344


### Customize configuration
See [Configuration Reference](https://cli.vuejs.org/config/).


### TODO
* Template refs (and what happens with Composition API)
* Scoped and global css
* Sending events back to parents
* Passing props to childs
* Other ways to pass data between components? Bus? (See bookmarks)
* Slots?
* Mounted vs onMounted?
* Inject
* Style Guide?
* The proper way to force Vue to re-render components, and nextTick?
* Common problems
* More details about Vue lifecycle etc.
* Same implementation for vue store like we did in the other project. Better TS support?

### New in Vue 3
* Composition API
* Multiple root elements
* Teleport component
* Suspense component: used to handle async components easily. Can provide fallback content untill data is loaded
* Better TS support
* Multiple `v-model`s for custom components
* Improved Reactivity -> Now support for data structures like Map and Set ???

#### Teleport Component
TODO - Render content from one component in a different place in the DOM.
I guess this does solve the Problem where I had to render a different html file depending on the layout of the object (AgendaSearchResult -> AgendaSearchResultTile vs AgendaSearchResultList)
This probably can also be solved with multiple root elements?

### Basics
Vue can be used basically in 2 ways: 
1. Creating a <b>single page application</b> where everything is done withing a Vue project
2. Creating <b>standalone widgets/components</b> in an already existing page. This is usefull when we want to control certain parts of the page and make it dynamic. For instance a search component.

Here is a simple example how to create a component with Vue and mount it to an element with the id `counter`. Vue then will only track the state and changes for this element.

```html

<html>
  <head>
    <script src="https://unpkg.com/vue@3.0.2"></script>
  </head>
  ...
  <h1>Vue Counter with JS</h1>
  <div id="counter"></div>

  <script>
    const counterComponent = {
      // Vue will inject (not replace) the counter component template to #counter
      template: `
        <div>Counter value: {{ counter }}</div>
        <button v-on:click="increase">add 1</button>
      `,
      data () {
        return { counter: 1 }
      },
      methods: {
        increase () { this.counter += 1 }
      }
    }
    Vue.createApp(counterComponent).mount('#counter')
  </script>
</html>
```

## Application & Component Instances
#
### Creating an Application Instance

Every Vue application starts by creating a new application instance with the createApp function:

```javascript
const app = Vue.createApp({
  /* options */
})
```

The application instance is used to register 'globals' that can then be used by components within that application. We'll discuss that in detail later in the guide but as a quick example:

```javascript
const app = Vue.createApp({})
app.component('SearchInput', SearchInputComponent)
app.directive('focus', FocusDirective)
app.use(LocalePlugin)
```

### The Root Component

The options passed to createApp are used to configure the root component. That component is used as the starting point for rendering when we mount the application.
An application needs to be mounted into a DOM element. For example, if we want to mount a Vue application into `<div id="app"></div>`, we should pass `#app`:

```javascript
const RootComponent = {
  /* options */
}
const app = Vue.createApp(RootComponent)
const vm = app.mount('#app')
```

Unlike most of the application methods, mount does not return the application. Instead it returns the root component instance. The root component isn't really any different from any other component. The configuration options are the same, as is the behavior of the corresponding component instance.


### Component Instance Properties

Earlier in the guide we met data properties. Properties defined in data are exposed via the component instance:
```javascript
const app = Vue.createApp({
  data() {
    return { count: 4 }
  }
})
const vm = app.mount('#app')
console.log(vm.count) // => 4
```
There are various other component options that add user-defined properties to the component instance, such as methods, props, computed, inject and setup. We'll discuss each of these in depth later in the guide. All of the properties of the component instance, no matter how they are defined, will be accessible in the component's template.

Vue also exposes some built-in properties via the component instance, such as `$attrs` and `$emit`. These properties all have a $ prefix to avoid conflicting with user-defined property names.
#

### Lifecycle Hooks and Diagram

* Don't use arrow functions (opens new window) on an options property or callback, such as `created: () => console.log(this.a)` or `vm.$watch('a', newValue => this.myMethod())`. Since an arrow function doesn't have a `this`, `this` will be treated as any other variable and lexically looked up through parent scopes until found, often resulting in errors such as `Uncaught TypeError: Cannot read property of undefined` or` Uncaught TypeError: this.myMethod is not a function`.

### Template Syntax

Dynamically rendering arbitrary HTML on your website (with `v-html`) can be very dangerous because it can easily lead to XSS vulnerabilities (opens new window). Only use HTML interpolation on trusted content and <b style="color:red">never</b> on user-provided content.

#### v-bind
* https://v3.vuejs.org/api/directives.html#v-bind
* If the bound value is `null` or `undefined` then the attribute will not be included on the rendered element.
* The attribute will be kept if the value is a `truthy value`.


* JavaScript expressions can be used inside <b>all data bindings</b>. These expressions will be evaluated as JavaScript in the data scope of the current active instance. One restriction is that each binding can only contain one single expression, so the following will NOT work.
* Directives are special attributes with the `v-` prefix. Directive attribute values are expected to be a single JavaScript expression (with the exception of `v-for` and `v-on`, which will be discussed later). A directive's job is to <b>reactively apply side effects</b> to the DOM when the value of its expression changes. 

### Component Communication
Vue.js allows component communication in the following ways:
TODO
  1. Parent to child communication (Using Props).
  2. Child to parent communication (Using Events).
  3. Communication between any component (Using Event Bus).
  TODO: https://medium.com/weekly-webtips/communication-between-components-in-vuejs-b41d9e8be9c4
  4. $root.emit vs CustomEventBut???
  5. accessing child props from parent?

### Props
* All props form a `one-way-down binding` between the child property and the parent one: when the parent property updates, it will flow down to the child, but not the other way around. This prevents child components from accidentally mutating the parent's state. You should <b>not</b> attempt to mutate a prop inside a child component. Note that objects and arrays in JavaScript are passed by reference, so if the prop is an array or object, mutating the object or array itself inside the child component <b>WILL</b> affect parent state.
* You need `v-bind` or `:` to tell Vue that the value we are passing is not a string:
```html
<!-- Even though `false` is static, we need v-bind to tell Vue that -->
<!-- this is a JavaScript expression rather than a string.          -->
<blog-post :is-published="false"></blog-post>
<blog-post :likes="42"></blog-post>
<blog-post :comment-ids="[234, 266, 273]"></blog-post>
```
* If you want to pass all the properties of an object as props
```html
<blog-post v-bind="post"></blog-post> is the same as
<blog-post v-bind:id="post.id" v-bind:title="post.title"></blog-post>
```
* Props can be of any type (primitive types, object, array, function) and for all of them a default value can be provided (default object, default function etc.). You can also provide a `validator` function which returns a boolean indicating if the given prop is valid or not. If it returns `false`, a `warning` will be shown in the console.
* Instance properties (e.g. `data`, `computed`, etc) will NOT be available inside `default` or `validator` functions, since they are invoked first.
* Should props be kebab-case in html and if yes, why? Both works -> https://v3.vuejs.org/guide/component-basics.html#case-insensitivity

### Non-Prop Attributes
A component non-prop attribute is an attribute or event listener that is passed to a component, but does not have a corresponding property defined in props or emits. Common examples of this include `class`, `style`, and `id` attributes. You can access those attributes via $attrs property.

* Attribute Inheritance: When a component returns a single root node, non-prop attributes will automatically be added to the root node's attributes. Same rule applies to the event listeners. This default behaviour can be disabled
```html
<!-- Date-picker component with a non-prop attribute -->
<date-picker data-status="activated"></date-picker>

<!-- Rendered date-picker component -->
<div class="date-picker" data-status="activated">
  <input type="datetime-local" />
</div>

```
* Components with multiple roo nodes (multiple HTML elements) do not have an automatic attribute fallthrough behavior. If `$attrs` are not bound explicitly, a runtime warning will be issued.
```html
// No warnings, $attrs are passed to <main> element
app.component('custom-layout', {
  template: `
    <header>...</header>
    <main v-bind="$attrs">...</main>
    <footer>...</footer>
  `})
```

### Custom Events
* Event names: Like components and props, event names provide an automatic case transformation. If you emit an event from the child component in camel case, you will be able to add a kebab-cased listener in the parent: `this.$emit('myEvent')` -> `@my-event="doSomething"`
* TODO: https://v3.vuejs.org/guide/component-custom-events.html#defining-custom-events