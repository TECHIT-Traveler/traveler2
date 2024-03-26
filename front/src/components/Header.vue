<template>
  <header>
    <nav class="navbar navbar-expand-sm navbar-light bg-light">
      <div class="container-fluid">
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarContent" aria-controls="navbarContent" aria-expanded="false" aria-label="Toggle navigation">
          <span class="navbar-toggler-icon"></span>
        </button>
        <b-navbar-nav class="collapse navbar-collapse" id="navbarContent">
          <ul class="navbar-nav me-auto">
            <li class="nav-item">
              <router-link to="/" class="nav-link">Traveler</router-link>
            </li>
            <b-nav-item-dropdown  class="nav-item">
              <template #button-content>
                <strong>경기</strong>
              </template>
              <b-dropdown-item @click="$router.push('/Gyeonggi')">경기</b-dropdown-item>
              <b-dropdown-item @click="$router.push('/Gyeonggi2')">경기2</b-dropdown-item>
            </b-nav-item-dropdown>
            <li class="nav-item">
              <router-link :to="{name: 'Gangwon'}" class="nav-link">강원</router-link>
            </li>
            <li class="nav-item">
              <router-link :to="{name: 'Ulsan'}" class="nav-link">울산</router-link>
            </li>
          </ul>
        </b-navbar-nav>
        <!-- Right aligned nav items -->
        <b-navbar-nav class="ml-auto" v-if="isAuthenticated">
          <b-nav-item-dropdown right>
            <!-- Using 'button-content' slot -->
            <template #button-content>
              <em>{{userInfo.nickname}}</em>
            </template>
            <b-dropdown-item @click="$router.push('/MyPage')">Profile</b-dropdown-item>
            <b-dropdown-item @click="logout">Sign Out</b-dropdown-item>
          </b-nav-item-dropdown>
        </b-navbar-nav>
        <b-navbar-nav class="ml-auto" v-if="!isAuthenticated">
          <b-nav-item-dropdown right>
            <!-- Using 'button-content' slot -->
            <template #button-content>
              <em>로그인 해주세요.</em>
            </template>
            <b-dropdown-item v-if="!isAuthenticated" @click="$router.push('/Login')">Login</b-dropdown-item>
            <b-dropdown-item v-if="!isAuthenticated" @click="$router.push('/Join')">Join</b-dropdown-item>
          </b-nav-item-dropdown>
        </b-navbar-nav>
      </div>
    </nav>
  </header>
</template>

<script>
export default {
  name: 'Header',
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
          window.location.href = '/'
        })
        .catch(error => {
          console.error('로그아웃 실패', error)
        })
    }
  }
}
</script>

<style scoped>
.header {
  font-weight: 900;
  margin : 2.5rem 0 1.5rem;
  z-index: 1;
}
nav .navbar-nav a.nav-link {
  font-weight: bold;
  color: #2c3e50 !important;
  text-align:initial;
}
.navbar-info a { font-weight:bold; color:#2c3e50 !important; text-decoration:none; }
.navbar-info span { font-weight:bold; color:#2c3e50; cursor:pointer; }
@media (max-width: 576px) {
  .navbar-info { background-color:#eee; padding:10px 10px; }
}
</style>
