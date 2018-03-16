<template>
  <div id="app">
    <div class="ui menu">
      <div class='item'>J I R A</div>
      <a :class="['item', {'active': pageName === 'Dashboard'}]" href="/">总览</a>
      <a :class="['item', {'active': pageName === 'Projects'}]" href="/projects">项目</a>
      <a :class="['item', {'active': pageName === 'Assign'}]">任务 </a>
      <a :class="['item', {'active': pageName === 'Assign'}]">历史 </a>
      <div class="right menu">
        <a class="ui item image label"><img src="http://imgsrc.baidu.com/imgad/pic/item/5882b2b7d0a20cf46234df507c094b36adaf999c.jpg"> Joe </a>
        <a class="ui item">登出</a>
       </div>
    </div>
    <router-view/>
  </div>
</template>

<script>
import HelloWord from './components/HelloWorld'
export default {
  components: { HelloWord },
  name: 'App',
  computed: {
    pageName: function () {
      return this.$route.name
    }
  }
}
</script>

<style>
@import "./style/style.scss";
#app {
  font-family: "Avenir", Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  max-width: 1440px;
  margin: auto;
  padding: 20px;
}
</style>
