<template>
  <div id="app">
    <HelloWord></HelloWord>
    <img src="./assets/logo.png">
    <router-view/>
  </div>
</template>

<script>
import HelloWord from './components/HelloWorld'
export default {
  components: {HelloWord},
  name: 'App'
}
</script>

<style>
  @import "./style/style.scss";
#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
</style>
