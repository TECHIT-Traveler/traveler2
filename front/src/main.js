// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from "vue";
import App from "./App";
import router from "./router";
import axios from "axios";
import BootstrapVue from "bootstrap-vue";
import "bootswatch/dist/Zephyr/bootstrap.min.css";

Vue.config.productionTip = false;
Vue.prototype.$axios = axios;
Vue.use(BootstrapVue);
Vue.config.productionTip = false;

const instance = axios.create({
  withCredentials: true,
});

Vue.prototype.$axios = instance;

/* eslint-disable no-new */
new Vue({
  el: "#app",
  router,
  components: { App },
  template: "<App/>",
});
