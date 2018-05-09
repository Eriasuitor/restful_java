import Vue from 'vue'
import Router from 'vue-router'
import Index from '@/page/index'
import Content from '@/page/content'
import Projects from '@/page/projects'
import Project from '@/page/project'
import Subtask from '@/page/subtask'
import Subtasks from '@/page/subtasks'
import Bugs from '@/page/bugs'

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
    },
    {
      path: '/projects/:id',
      name: 'Projects',
      component: Project
    },
    {
      path: '/projects/:id/subtasks/:subId',
      name: 'Projects',
      component: Subtask
    },
    {
      path: '/statistics/subtasks',
      name: 'Subtasks',
      component: Subtasks
    },
    {
      path: '/statistics/bugs',
      name: 'Bugs',
      component: Bugs
    }
  ]
})
