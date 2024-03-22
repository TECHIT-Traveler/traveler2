<template>
  <header>
	<nav class="navbar navbar-expand-sm navbar-light bg-light">
		<div class="container-fluid">
			<button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarContent" aria-controls="navbarContent" aria-expanded="false" aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarContent">
				<ul class="navbar-nav me-auto">
					<li class="nav-item">
						<router-link to="/" class="nav-link">Traveler</router-link>
					</li>
					<li class="nav-item">
						<router-link :to="{name: 'Gyeonggi'}" class="nav-link">경기</router-link>
					</li>
					<li class="nav-item">
						<router-link :to="{name: 'Gangwon'}" class="nav-link">강원</router-link>
					</li>
					<li class="nav-item">
						<router-link :to="{name: 'Ulsan'}" class="nav-link">울산</router-link>
					</li>
				</ul>
				<div class="navbar-info">
					<div v-if="isLogin">
						<p>안녕하세요.</p>
						<span @click="Logout()">로그아웃</span>
					</div>
				</div>
			</div>
            <!-- Right aligned nav items -->
      <b-navbar-nav class="ml-auto">
        <b-nav-item-dropdown right>
          <!-- Using 'button-content' slot -->
          <template #button-content>
            <em>User</em>
          </template>
          <b-dropdown-item href="#">Profile</b-dropdown-item>
          <b-dropdown-item href="#">Sign Out</b-dropdown-item>
        </b-nav-item-dropdown>
      </b-navbar-nav>
		</div>
	</nav>
</header>

</template>

<script>
export default {
  name: 'Header'
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
