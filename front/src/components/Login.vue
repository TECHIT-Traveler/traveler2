<template>
  <div class="login">
    <h2>로그인</h2>
    <form @submit.prevent="login">
      <div>
        <label for="username">아이디</label>
        <input type="text" id="username" name="username" v-model="username" placeholder="아이디를 입력해주세요">
      </div>
      <div>
        <label for="password">비밀번호</label>
        <input type="password" id="password" name="password" v-model="password" placeholder="비밀번호를 입력해주세요">
      </div>
      <button type="submit" class="login-button">로그인</button>
      <button type="button" @click="naverLogin" class="social-login-button naver">네이버 로그인</button>
      <button type="button" @click="googleLogin" class="social-login-button google">구글 로그인</button>
    </form>
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
.login {
  max-width: 300px;
  margin: 20px auto;
  padding: 20px;
  border: 1px solid #ccc;
  border-radius: 5px;
}

.login form {
  display: flex;
  flex-direction: column;
}

.login input {
  margin-bottom: 10px;
  padding: 5px;
}

.login button {
  margin-bottom: 10px;
  padding: 8px;
  cursor: pointer;
}

.login button.login-button {
  background-color: #71C4EE;
  color: white;
  border: none;
}

.login button.login-button:hover {
  background-color: #1F7BEC;
}

.login button.social-login-button {
  color: white;
  border: none;
}

.login button.naver {
  background-color: #1ec800;
}

.login button.naver:hover {
  background-color: #049326;
}

.login button.google {
  background-color: #4285f4;
}

.login button.google:hover {
  background-color: #422CC1;
}
</style>
