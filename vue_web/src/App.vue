<template>
  <div id="app">
    <div class="ui menu" v-if="pageName != 'Login'">
      <div class='item'>J I R A</div>
      <!-- <a :class="['item', {'active': pageName === 'Dashboard'}]" href="/">总览</a> -->
      <a :class="['item', {'active': pageName === 'Projects'}]" @click="routeTo('/projects')">项目</a>
      <a :class="['item', {'active': pageName === 'Subtasks'}]" @click="routeTo('/statistics/subtasks')">任务</a>
      <a :class="['item', {'active': pageName === 'Bugs'}]" @click="routeTo('/statistics/bugs')">Bugs</a>
      <a :class="['item', {'active': pageName === 'History'}]" @click="routeTo('/history')">历史 </a>
      <div class="right menu">
        <a class="ui item image label"><img :src="userInfo.image">{{userInfo.name}} </a>
        <a class="ui item" @click="logout()">登出</a>
      </div>
    </div>
    <router-view v-on:refreshUserInfo="refreshUserInfo"/>
  </div>
</template>

<script>
  import HelloWord from './components/HelloWorld'
  export default {
    components: {
      HelloWord
    },
    name: 'App',
    computed: {
      pageName: function() {
        return this.$route.name
      }
    },
    data() {
      return {
        userInfo: {}
      }
    },
    methods: {
      routeTo: function(path) {
        this.$router.push({
          path,
          replace: true
        })
      },
      logout: function(){
        window.localStorage.clear('token')
        window.localStorage.clear('userInfo')
        window.localStorage.setItem('toLogin', this.$route.path)
        this.$router.push({
          path: "/login",
          replace: true
        })
      },
      refreshUserInfo: function(){
        this.userInfo = JSON.parse(window.localStorage.getItem('userInfo'))
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
    max-width: 1550px;
    margin: auto;
    padding: 20px;
  }
</style>
