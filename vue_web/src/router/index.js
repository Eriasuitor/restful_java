import Vue from 'vue'
import Router from 'vue-router'
import HelloWorld from '@/components/HelloWorld'
import Index from '@/page/index'
import Content from '@/page/content'
import Projects from '@/page/projects'

Vue.use(Router)

export default new Router({
  mode: 'history',
  routes: [
    {
      path: '/',
      name: 'Dashboard',
      component: Index
    },
    {
      path: '/content/:id',
      name: 'Content',
      component: Content
    },
    {
      path: '/projects',
      name: 'Projects',
      component: Projects
    }
  ]
})
