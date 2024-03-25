// import Vue from 'vue'
// import Vuex from 'vuex'

// Vue.use(Vuex)

// export default new Vuex.Store({
//   state: {
//     isAuthenticated: false,
//     userInfo: null
//   },
//   mutations: {
//     setAuthenticated(state, isAuthenticated) {
//       state.isAuthenticated = isAuthenticated
//     },
//     setUserInfo(state, userInfo) {
//       state.userInfo = userInfo
//     }
//   },
//   actions: {
//     async checkLoginStatus({ commit }) {
//       try {
//         const token = Vue.$cookies.get('Authorization')
//         if (token) {
//           commit('setAuthenticated', true)
//         }
//       } catch (error) {
//         console.error('로그인 사용자 정보 요청 실패', error)
//       }
//     },
//     async logout({ commit }) {
//       try {
//         await Vue.$axios.post('http://localhost:8090/member/logout')
//         commit('setAuthenticated', false)
//         commit('setUserInfo', null)
//         Vue.$cookies.remove('Authorization')
//         Vue.$cookies.remove('JSESSIONID')
//       } catch (error) {
//         console.error('로그아웃 실패', error)
//       }
//     }
//   },
//   modules: {}
// })
