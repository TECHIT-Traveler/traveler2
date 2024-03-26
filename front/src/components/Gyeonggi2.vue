<template>
  <div class="gyeonggi2">
    <div class="card-deck justify-content-center">
      <div
        v-for="(o, k) in gyeonggi2Data"
        :key="k"
        class="card mb-4"
        @click="goToDetailPage(o.id)"
        style="cursor: pointer">
        <div class="card-body">
          <h5 class="card-title">{{ o.지역명 }}</h5>
          <hr>
          <p class="card-text">
            <strong>주소:</strong> {{ o.주소 }} <br>
            <strong>전화번호:</strong> {{ o.전화번호 }} <br>
            <strong>이용시간:</strong> {{ o.이용시간 }} <br>
            <strong>홈페이지:</strong> {{ o.홈페이지 }} <br>
          </p>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  mounted () {
    this.getGyeonggi2Data()()
  },
  name: 'Gyeonggi2',
  data () {
    return {
      gyeonggi2Data: []
    }
  },
  methods: {
    getGyeonggi2Data() {
      fetch('http://localhost:8090/gyeonggi2')
        .then(resp => resp.json())
        .then(data => {
          this.gyeonggi2Data = data
        }).catch(err => console.log(err))
    },
    goToDetailPage(id) {
      this.$router.push({ name: 'Gyeonggi2Detail', params: { id: id } })
    }
  }
}
</script>

<style scoped>
.card-deck {
  display: flex;
  flex-wrap: wrap;
  margin-right: -15px;
  margin-left: -15px;
}

.card {
  flex: 0 0 33.333333%;
  max-width: 33.333333%;
  padding-right: 15px;
  padding-left: 15px;
  cursor: pointer; /* 커서를 포인터로 변경하여 클릭 가능한 것을 나타냅니다. */
}

.card-body {
  flex: 1 1 auto;
}

.card-title {
  margin-bottom: 0.75rem;
}

.card-text {
  margin-bottom: 1rem;
}
</style>
