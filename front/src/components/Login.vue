<template>
  <div class="container mt-5">
    <div class="row justify-content-center">
      <div class="col-md-6">
        <div class="card shadow">
          <div class="card-body">
            <h2 class="card-title text-center">로그인</h2>
            <form @submit.prevent="login">
              <div class="mb-3">
                <label for="username" class="form-label sr-only">아이디</label>
                <input type="text" class="form-control" id="username" name="username" v-model="username"
                  placeholder="아이디를 입력해주세요">
              </div>
              <div class="mb-4">
                <label for="password" class="form-label sr-only">비밀번호</label>
                <input type="password" class="form-control" id="password" name="password" v-model="password"
                  placeholder="비밀번호를 입력해주세요">
              </div>
              <button type="submit" class="btn btn-primary w-100 mb-2">로그인</button>
              <button type="button" @click="naverLogin" class="btn btn-success w-100 mb-2">네이버 로그인</button>
              <button type="button" @click="googleLogin" class="btn btn-secondary w-100">구글 로그인</button>
            </form>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  data () {
    return {
      username: '',
      password: ''
    }
  },
  methods: {
    login () {
      const formData = new FormData()
      if (this.username.trim().length === 0) {
        alert('아이디를 입력해주세요')
        return
      }
      if (this.password.trim().length === 0) {
        alert('비밀번호를 입력해주세요')
      }
      formData.append('username', this.username)
      formData.append('password', this.password)

      this.$axios.post('http://localhost:8090/login', formData)
        .then(response => {
          const token = response.headers['authorization']
          this.$cookies.set('Authorization', token)
          window.location.href = '/'
        }).catch(error => {
          console.log('로그인 실패: ', error)
          alert('로그인에 실패했습니다.')
        })
    },
    naverLogin () {
      window.location.href = 'http://localhost:8090/oauth2/authorization/naver'
    },
    googleLogin () {
      window.location.href = 'http://localhost:8090/oauth2/authorization/google'
    }
  }
}
</script>

<style scoped>
button {
  font-size: 16px;
}
</style>


