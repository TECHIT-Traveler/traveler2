<template>
  <div class="container mt-5">
    <div class="row justify-content-center">
      <div class="col-md-6">
        <div class="card shadow">
          <div class="card-body">
            <h2 class="card-title text-center">회원가입</h2>
            <form @submit.prevent="register">
              <div class="form-group">
                <label for="username" class="sr-only">아이디</label>
                <input type="text" class="form-control" id="username" name="username" v-model="username"
                  placeholder="아이디를 입력해주세요">
                <button type="button" class="btn btn-outline-primary form-control mt-2"
                  @click="checkUsernameAvailavility">중복 확인</button>
                <p v-if="usernameAvailable === false &amp;&amp; username.length &gt; 0" class="text-danger">이미 사용중인
                  아이디입니다.</p>
                <p v-else-if="usernameAvailable === true &amp;&amp; username.length &gt; 0" class="text-success">사용 가능한
                  아이디입니다.</p>
              </div>
              <div class="form-group">
                <label for="password" class="sr-only">비밀번호</label>
                <input type="password" class="form-control" id="password" name="password" v-model="password"
                  @input="passwordConfirm" placeholder="비밀번호를 입력해주세요">
                <p v-if="password && !isValidPassword" class="text-danger">비밀번호는 영문 소문자와 숫자, 특수기호가 적어도 1개 이상씩 포함된 8자 이상의
                  비밀번호여야 합니다.</p>
              </div>
              <div class="form-group">
                <label for="confirmPassword" class="sr-only">비밀번호 확인</label>
                <input type="password" class="form-control" id="confirmPassword" name="confirmPassword"
                  v-model="confirmPassword" placeholder="비밀번호를 다시 입력해주세요">
                <p v-if="confirmPassword && confirmPasswordMismatch" class="text-danger">비밀번호가 일치하지 않습니다.</p>
              </div>
              <div class="form-group">
                <label for="email" class="sr-only">이메일</label>
                <input type="email" class="form-control" id="email" name="email" v-model="email"
                  :disabled="isEmailVerified" placeholder="이메일을 입력해주세요">
                <p v-if="email && !isValidEmail" class="text-danger">이메일 형식에 맞지 않습니다.</p>
                <button type="button" class="btn btn-outline-primary form-control mt-2" @click="sendVerificationCode"
                  :disabled="!isValidEmail || isEmailVerified">인증번호 전송</button>
              </div>
              <div class="form-group">
                <label for="emailCode" class="sr-only">이메일 인증번호</label>
                <input type="text" class="form-control" id="emailCode" name="emailCode" v-model="emailCode"
                  :disabled="!emailSending || isEmailVerified" placeholder="인증번호를 입력해주세요">
                <button type="button" class="btn btn-outline-primary form-control mt-2" @click="verifyCode"
                  :disabled="!emailSending || isEmailVerified">인증번호 확인</button>
              </div>
              <div class="form-group">
                <label for="nickname" class="sr-only">닉네임</label>
                <input type="text" class="form-control" id="nickname" name="nickname" v-model="nickname"
                  placeholder="닉네임을 입력해주세요">
              </div>
              <button type="submit" class="btn btn-primary form-control">회원가입</button>
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
      password: '',
      confirmPassword: '',
      email: '',
      emailCode: '',
      nickname: '',
      isEmailVerified: false,
      emailSending: false,
      usernameAvailable: null,
      isValidPassword: true,
      isValidEmail: true,
      confirmPasswordMismatch: false
    }
  },
  watch: {
    password (newValue) {
      this.validatePassword(newValue)
    },
    confirmPassword (newValue) {
      this.passwordConfirm(newValue)
    },
    email (newValue) {
      this.validateEmail(newValue)
    }
  },
  methods: {
    register () {
      if (this.username.length === 0) {
        alert('아이디를 입력해주세요')
        return
      }
      if (this.password.length === 0) {
        alert('비밀번호를 입력해주세요')
        return
      }
      if (this.email.length === 0) {
        alert('이메일을 입력해주세요')
        return
      }
      if (this.nickname.length === 0) {
        alert('닉네임을 입력해주세요')
        return
      }
      if (!this.isValidPassword) {
        alert('비밀번호가 형식에 맞지 않습니다.')
        return
      }
      if (this.confirmPasswordMismatch) {
        alert('비밀번호가 일치하는지 확인해주세요.')
        return
      }
      if (!this.isEmailVerified) {
        alert('이메일을 인증해주세요')
        return
      }
      if (!this.usernameAvailable) {
        alert('아이디 중복 여부를 확인해주세요')
        return
      }
      const data = {
        username: this.username,
        password: this.password,
        confirmPassword: this.confirmPassword,
        email: this.email,
        nickname: this.nickname
      }
      this.$axios.post('http://localhost:8090/member/join', data)
        .then(response => {
          console.log('회원가입 성공')
          this.$router.push('/login')
        }).catch(error => {
          console.log('회원가입 실패', error)
          console.log(this.password)
        })
    },
    sendVerificationCode () {
      const data = {
        email: this.email
      }
      if (this.email.length === 0) {
        alert('이메일 인증을 위해 이메일을 입력해주세요')
        return
      }
      this.$axios.get(`http://localhost:8090/member/checkEmail/${this.email}`)
        .then(response => {
          if (response.data) {
            alert('이미 가입한 적이 있는 이메일 입니다.')
          } else {
            this.emailSending = true
            this.$axios.post('http://localhost:8090/mailSend', data)
              .then(response => {
                console.log('이메일 인증 요청')
                alert('인증번호가 전송되었습니다.')
              }).catch(error => {
                console.log('이메일 인증 요청 실패', error)
              })
          }
        }).catch(error => {
          console.log('이메일 중복', error)
        })
    },
    verifyCode () {
      const data = {
        email: this.email,
        authNum: this.emailCode
      }
      this.$axios.post('http://localhost:8090/mailauthCheck', data)
        .then(response => {
          console.log('인증번호 확인 성공')
          alert('이메일 인증에 성공하였습니다.')
          this.isEmailVerified = true
        }).catch(error => {
          alert('인증번호가 일치하지 않습니다.')
          console.log('인증번호 확인 실패', error)
        })
    },
    checkUsernameAvailavility () {
      if (this.username.length === 0) {
        alert('아이디 중복 확인을 위해 아이디를 입력해주세요.')
        return
      }
      this.$axios.get(`http://localhost:8090/member/checkUsername/${this.username}`)
        .then(response => {
          this.usernameAvailable = response.data
        }).catch(error => {
          console.log('아이디 중복', error)
        })
    },
    validatePassword (password) {
      const passwordRegex = /^(?=.*[a-z])(?=.*[!@#$%^&*])(?=.*[0-9]).{8,}$/
      this.isValidPassword = passwordRegex.test(password)
    },
    validateEmail (email) {
      const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/
      this.isValidEmail = emailRegex.test(email)
    },
    passwordConfirm (confirmPassword) {
      this.confirmPasswordMismatch = this.password !== confirmPassword
    }
  }
}
</script>

<style scoped>
.register {
  max-width: 300px;
  margin: 20px auto;
  padding: 20px;
  border: 1px solid #ccc;
  border-radius: 5px;
}

.register form {
  display: flex;
  flex-direction: column;
}

.register input,
.register button {
  margin-bottom: 10px;
  padding: 8px;
}

.register button {
  background-color: #007bff;
  color: white;
  border: none;
  cursor: pointer;
}

.register button:disabled {
  background-color: #ccc;
  cursor: not-allowed;
}

.warn {
  color: red;
}

.ok {
  color: green;
}

</style>
