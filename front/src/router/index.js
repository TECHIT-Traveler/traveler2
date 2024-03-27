
import Vue from "vue";
import Router from "vue-router";

import Login from "@/components/Login";
import cookies from "vue-cookies";
import Join from "@/components/Join";

import HelloWorld from "@/components/HelloWorld.vue";

import Gyeonggi from "@/components/Gyeonggi.vue";
import Gyeonggi2 from "@/components/Gyeonggi2.vue";
import Gangwon from "@/components/Gangwon.vue";
import Ulsan from "@/components/Ulsan.vue";

import GangwonDetail from "@/components/GangwonDetail.vue";
import GyeonggiDetail from "@/components/GyeonggiDetail.vue";
import Gyeonggi2Detail from "@/components/Gyeonggi2Detail.vue";

import MyPage from "@/components/MyPage.vue";

Vue.use(Router); // Vue Router를 Vue 애플리케이션에 등록하는 역할
Vue.use(cookies); // vue-cookies라는 플로그인을 Vue 애플리케이션에 등록하는 역할

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
      path: "/Gyeonggi2",
      name: "Gyeonggi2",
      component: Gyeonggi2,
    },
    {
      path: "/Gangwon",
      name: "Gangwon",
      component: Gangwon,

    },
    {
      path: '/Ulsan',
      name: 'Ulsan',
      component: Ulsan
    },
    {
      path: '/gyeonggi/:id',
      name: 'GyeonggiDetail',
      component: GyeonggiDetail
    },
    {

      path: "/gyeonggi2/:id",
      name: "Gyeonggi2Detail",
      component: Gyeonggi2Detail,
    },
    {
      path: "/gangwon2/:id",
      name: "GangwonDetail",
      component: GangwonDetail,
    },
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
})
