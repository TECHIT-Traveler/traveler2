// import Vue from 'vue'
// import Vuex from 'vuex'
// import axios from 'axios'

// Vue.use(Vuex)

// export default new Vuex.Store({
//   state: {
//     isAuthenticated: false,
//     userInfo: null
//   },
//   mutations: {
//     setIsAuthenticated(state, isAuthenticated) {
//       state.isAuthenticated = isAuthenticated
//     },
//     setUserInfo(state, userInfo) {
//       state.userInfo = userInfo
//     }
//   },
//   actions: {
//     async checkLoginStatus({ commit }) {
//       try {
//         const token = this.$cookies.get('Authorization')
//         if (token) {
//           const response = await axios.get('http://localhost:8090/member/login-info', {
//             withCredentials: true
//           })
//           commit('setIsAuthenticated', true)
//           commit('setUserInfo', response.data)
//         }
//       } catch (error) {
//         console.error('로그인 사용자 정보 요청 실패', error)
//       }
//     }
//   }
// })
