// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import api from './api/index'
import utils from './utils/index'
import '../node_modules/semantic-ui-css/semantic.min.css'
require('semantic')

Vue.config.productionTip = false
Vue.prototype.$api = api
Vue.prototype.$utils = utils
Vue.prototype.$apiUrl = 'http://120.77.200.119:8080/MyBatisJIRAapi'
// Vue.prototype.$apiUrl = 'http://127.0.0.1:8080/MyBatisJIRAapi'

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  components: { App },
  template: '<App/>'
})
