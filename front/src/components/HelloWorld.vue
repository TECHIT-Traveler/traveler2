<template>
  <div class="main-top-promotion">
    <video class="main-top-promotion-video" autoplay="autoplay" loop="loop" muted="muted">
      <source src="../assets/project_dog (1).mp4" type="video/mp4">
    </video>
    <div class="container">
      <div class="row align-items-center">
        <div class="col-sm-12 col-md-6 text-md-start">
          <div class="py-3 py-md-5">
            <h1 class="text-white fw-bold">
              Welcome to Your <br>Vue.JS Application
            </h1>
            <p class="text-white mb-4 fs-5">
              당신의 이야기가 세상을 바꿉니다.
            </p>
            <router-link :to="{name:'BoardWrite'}" class="btn btn-success">나의 이야기 쓰기</router-link>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
}
</script>

<style>
.main-top-promotion-video {
  width: 100%;
  margin: 0px;
  padding: 0px;
  position: absolute;
  left: 0;
  object-fit: cover;
}
.main-top-promotion > .container {
  position: relative
}
</style>
