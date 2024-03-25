<template>
  <div class="gyeonggi2">
    <div v-for="(o, k) in gyeonggi2Data" :key="k">
      <h1>{{ o.지역명 }}</h1> <!-- 지역명만 h1에 표시 -->
      <div>
        <p>주소: {{ o.주소 }}</p>
        <p>전화번호: {{ o.전화번호 }}</p>
        <p>이용시간: {{ o.이용시간 }}</p>
        <p>홈페이지: {{ o.홈페이지 }}</p>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'Gyeonggi2',
  data () {
    return {
      gyeonggi2Data: []
    }
  },
  mounted () {
    this.getGyeonggi2Data()
  },
  methods: {
    getGyeonggi2Data () {
      fetch('http://localhost:8090/gyeonggi2')
        .then(resp => resp.json())
        .then(data => {
          this.gyeonggi2Data = data
        })
        .catch(err => console.log(err))
    }
  }
}
</script>

<style scoped>
h1, h2 {
  font-weight: normal;
}
ul {
  list-style-type: none;
  padding: 0;
}
li {
  display: inline-block;
  margin: 0 10px;
}
a {
  color: #42b983;
}
</style>
