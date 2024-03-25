<template>
  <div class="dog-facilities">
    <h1>{{ title }}</h1>
    <h2>애견 동반 가능한 시설 목록</h2>
    <ul>
      <li v-for="facility in dogFacilities" :key="facility.id">
        <h3>{{ facility.name }}</h3>
        <p>{{ facility.description }}</p>
        <p>주소: {{ facility.address }}</p>
        <p>전화번호: {{ facility.phone }}</p>
        <p>운영 시간: {{ facility.hours }}</p>
      </li>
    </ul>
  </div>
</template>

<script>
export default {
  name: 'DogFacilities',
  data () {
    return {
      title: '애견 동반 가능한 시설 안내',
      dogFacilities: [
        {
          id: 1,
          name: '애견 카페 "멍멍카페"',
          description: '애견과 함께 커피를 즐길 수 있는 카페입니다.',
          address: '서울시 강남구 강남대로 123',
          phone: '02-1234-5678',
          hours: '매일 10:00 - 22:00'
        },
        {
          id: 2,
          name: '애견 놀이터 "행복공원"',
          description: '애견과 함께 놀 수 있는 공원입니다.',
          address: '서울시 서초구 강남대로 456',
          phone: '02-9876-5432',
          hours: '매일 09:00 - 18:00'
        }
        // 추가적인 애견 동반 가능한 시설 정보를 여기에 추가할 수 있습니다.
      ]
    }
  }
}
</script>

<style scoped>
h1, h2 {
  font-weight: normal;
  text-align: center;
}
ul {
  list-style-type: none;
  padding: 0;
}
li {
  margin-bottom: 20px;
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
  position: relative;
}
</style>
