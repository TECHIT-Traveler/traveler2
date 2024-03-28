<template>
  <div class="container mt-5">
    <div class="row justify-content-center">
      <div class="col-md-8">
        <div class="card">
          <div class="card-body">
            <!-- <div class="col-md-4">
              <img class="img-thumbnail rounded-circle mb-5" :src="profileImgUrl" style="width: 150px; height: 150px;">
            </div> -->
            <div class="row">
              <div class="col-md-12">
                <table class="table">
                  <tbody>
                    <tr>
                      <th class="w-30">아이디</th>
                      <td v-if="userInfo.provider === null">{{ userInfo.username }}</td>
                      <td v-else>{{ userInfo.provider }} 사용자</td>
                    </tr>
                    <tr>
                      <th>닉네임</th>
                      <td>{{ userInfo.nickname }}</td>
                    </tr>
                    <tr>
                      <th>이메일</th>
                      <td>{{ userInfo.email }}</td>
                    </tr>
                    <tr>
                      <th>경기저장</th>
                      <td>{{ userInfo.email }}</td>
                    </tr>
                  </tbody>
                </table>
              </div>
            </div>
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
          console.log('사용자 정보 요청 실패', error)
        })
      }
    }
  }
}
</script>

<style scoped>
</style>
