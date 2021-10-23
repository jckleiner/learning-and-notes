# learning-vue-3

## Project setup
```
npm install
```

### Compiles and hot-reloads for development
```
npm run serve
```

### Compiles and minifies for production
```
npm run build
```

### Lints and fixes files
```
npm run lint
```

### Customize configuration
See [Configuration Reference](https://cli.vuejs.org/config/).


### TODO
* Template refs (and what happens with Composition API)
* Scoped and global css
* Sending events back to parents
* Passing props to childs
* Other ways to pass data between components? Bus? (See bookmarks)
* Slots?
* Style Guide?
* The proper way to force Vue to re-render components, and nextTick?
* Common problems
* More details about Vue lifecycle etc.

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