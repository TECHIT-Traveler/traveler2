import Vue from "vue";
import Router from "vue-router";
import cookies from "vue-cookies";

import Login from "@/components/Login";
import Join from "@/components/Join";

import HelloWorld from "@/components/HelloWorld.vue";
import Place from "@/components/Place.vue";
import PlaceDetail from "@/components/PlaceDetail.vue";

import MyPage from "@/components/MyPage.vue";

Vue.use(Router);
Vue.use(cookies);

export default new Router({
  routes: [
    {
      path: "/",
      name: "HelloWorld",
      component: HelloWorld,
    },
    {
      path: "/Place",
      name: "Place",
      component: Place,
    },
    {
      path: "/places/:id",
      name: "PlaceDetail",
      component: PlaceDetail,
    },
    {
      path: "/Login",
      name: "Login",
      component: Login,
    },
    {
      path: "/Join",
      name: "Join",
      component: Join,
    },
    {
      path: "/MyPage",
      name: "MyPage",
      component: MyPage,
    },
  ],
});
