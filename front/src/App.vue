<template>
  <div id="app">
    <img src="./assets/logo.png">
    <router-link v-if="!isAuthenticated" to="/login">Login</router-link>
    <router-link v-if="!isAuthenticated" to="/join">Join</router-link>
    <button @click="logout" v-if="isAuthenticated">Logout</button>
    <p v-if="isAuthenticated">
      <strong>{{userInfo}}</strong>
      <span>님 환영합니다.</span>
    </p>
    <router-view/>
  </div>
</template>

<script>
export default {
  name: 'App',
  data () {
    return {
      isAuthenticated: false,
      userInfo: null
    }
  },
  created () {
    this.checkLoginStatus()
  },
  methods: {
    checkLoginStatus () {
      const token = this.$cookies.get('Authorization')
      if (token !== null) {
        this.$axios.get('http://localhost:8090/member/login-info', {
          withCredentials: true
        }).then(response => {
          this.isAuthenticated = true
          this.userInfo = response.data
        }).catch(error => {
          console.log('로그인 사용자 정보 요청 실패', error)
        })
      }
    },
    logout () {
      this.$axios.post('http://localhost:8090/member/logout')
        .then(() => {
          this.isAuthenticated = false
          this.userInfo = null
          this.$cookies.remove('Authorization')
          this.$cookies.remove('JSESSIONID')
        })
        .catch(error => {
          console.error('로그아웃 실패', error)
        })
    }
  }
}
</script>

<style>
#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
</style>
