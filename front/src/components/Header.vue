<template>
  <header>
    <nav class="navbar navbar-expand-lg bg-light">
      <div class="container-fluid">
        <router-link to="/" class="navbar-brand">Traveler</router-link>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarColor03"
          aria-controls="navbarColor03" aria-expanded="false" aria-label="Toggle navigation">
          <span class="navbar-toggler-icon"></span>
        </button>
        <div class="navbar-collapse collapse show" id="navbarColor03">
          <!-- 네비게이션 메뉴 -->
          <ul class="navbar-nav me-auto">
            <b-nav-item-dropdown  class="nav-item active mr-auto">
              <template #button-content>
                <b>경기</b>
              </template>
              <b-dropdown-item @click="$router.push('/Gyeonggi')">경기</b-dropdown-item>
              <b-dropdown-item @click="$router.push('/Gyeonggi2')">경기2</b-dropdown-item>
            </b-nav-item-dropdown>
            <li class="nav-item">
              <router-link to="/Gangwon" class="nav-link active">강원</router-link>
            </li>
            <li class="nav-item">
              <router-link to="/Ulsan" class="nav-link active">울산</router-link>
            </li>
          </ul>
            <!-- dropdown -->
            <li class="nav-item dropdown d-flex" v-if="isAuthenticated">
              <b-nav-item-dropdown right>
                <!-- Using 'button-content' slot -->
                <template #button-content>
                  <em>{{userInfo.nickname}}</em>
                </template>
                <b-dropdown-item @click="$router.push('/MyPage')">Profile</b-dropdown-item>
                <b-dropdown-item @click="logout">Sign Out</b-dropdown-item>
              </b-nav-item-dropdown>
            </li>
            <li class="nav-item dropdown d-flex" v-if="!isAuthenticated">
              <b-nav-item-dropdown right>
                <!-- Using 'button-content' slot -->
                <template #button-content>
                  <strong>로그인 해주세요.</strong>
                </template>
                <b-dropdown-item v-if="!isAuthenticated" @click="$router.push('/Login')">Login</b-dropdown-item>
                <b-dropdown-item v-if="!isAuthenticated" @click="$router.push('/Join')">Join</b-dropdown-item>
              </b-nav-item-dropdown>
            </li>
        </div>
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
          // window.location.href = '/'
        })
        .catch(error => {
          console.error('로그아웃 실패', error)
        })
    }
  }
}
</script>

<style scoped>
.nav {
  font-weight: 500;
  margin : 2.5rem 0 1.5rem;
  z-index: 1;
}
.nav-item {
  font-size: 16px;
}
.nav-item.dropdown{
  list-style-type: none; 
  padding-left: 0; 
}
.navbar-brand {
  margin-left: 8px;
}
.nav-item b {
  font-weight: 500;
  margin-left: 8px;
}
.nav-link {
  margin-left: 8px;
  text-align: left;
}
@media (max-width: 576px) {
  .navbar-info { background-color:#eee; padding:10px 10px; }
}
</style>
