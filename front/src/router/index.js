

import Vue from 'vue'
import Router from 'vue-router'
import Login from '@/components/Login'
import cookies from 'vue-cookies'
import Join from '@/components/Join'
import HelloWorld from '@/components/HelloWorld.vue'
import Gyeonggi from '@/components/Gyeonggi.vue'
import Gangwon from '@/components/Gangwon.vue'
import Ulsan from '@/components/Ulsan.vue'
import GangwonDetail from "@/components/GangwonDetail.vue";
import MyPage from '@/components/MyPage.vue'

Vue.use(Router)
Vue.use(cookies)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'HelloWorld',
      component: HelloWorld
    },
    {
      path: '/Gyeonggi',
      name: 'Gyeonggi',
      component: Gyeonggi
    },
    {
      path: '/Gangwon',
      name: 'Gangwon',
      component: Gangwon
    },
    {
      path: '/Ulsan',
      name: 'Ulsan',
      component: Ulsan
    },
    {
      path: "/gangwon2/:id",
      name: "GangwonDetail",
      component: GangwonDetail,
    },
  ],
  {
      path: '/Login',
      name: 'Login',
      component: Login
    },
    {
      path: '/Join',
      name: 'Join',
      component: Join
    },
    {
      path: '/MyPage',
      name: 'MyPage',
      component: MyPage
    }
  ]
});
