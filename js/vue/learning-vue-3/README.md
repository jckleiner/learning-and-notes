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

