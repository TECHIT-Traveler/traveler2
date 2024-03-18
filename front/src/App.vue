<template>
  <div id="app">
    <div class="header">
		<div class="topline">
			<div class="headmenu">
				<div v-if="isLogin">
					{{this.$store.state.loginStore.memberId}}님, 안녕하세요.
					<span @click="Logout()">로그아웃</span>
				</div>
				<div v-else>
					<router-link :to="{ name: 'Login', query: { returnUrl: '/' }}" v-show="isLogin == false">로그인</router-link>
				</div>
			</div>
		</div>
		<div id="nav">
			<router-link to="/">Home</router-link> |
			<router-link to="/about">About</router-link> |
			<router-link :to="{name: 'BoardList'}">Board</router-link> |
			<router-link :to="{name: 'Score'}">Score</router-link>
		</div>
		<button type="button" class="btn btn-primary">부트스크랩 버튼</button>
	</div>
	<router-view/>
  </div>
</template>

<script>
export default {
  name: 'App'
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
