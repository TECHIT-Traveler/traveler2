import Vue from 'vue'
import Router from 'vue-router'
import Login from '@/components/Login'
import cookies from 'vue-cookies'
import Join from '@/components/Join'

Vue.use(Router)
Vue.use(cookies)

export default new Router({
  routes: [
    {
      path: '/login',
      name: 'Login',
      component: Login
    },
    {
      path: '/join',
      name: 'Join',
      component: Join
    }
  ]
})
