import Vue from "vue";
import Router from "vue-router";
import cookies from "vue-cookies";

import Login from "@/components/Login";
import Join from "@/components/Join";

import HelloWorld from "@/components/HelloWorld.vue";
import Place from "@/components/Place.vue";
import PlaceDetail from "@/components/PlaceDetail.vue";

import MyPage from "@/components/MyPage.vue";
import Like from "@/components/Like.vue";
import Review from "@/components/Review.vue";
import ReviewModify from "@/components/ReviewModify.vue";


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
    {
      path: '/like/:id', // 동적 파라미터를 사용한 라우터 경로 설정
      name: 'Like',
      component: Like
    },
    {
      path: '/review/:id',
      name: 'Review',
      component: Review
    },
    {
      path: '/review/modify/:reviewId',
      name: 'ReviewModify',
      component: ReviewModify
    }
  ],
});
