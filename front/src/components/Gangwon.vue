<template>
  <div class="gangwon">
    <div class="card-deck justify-content-center">
      <div
        v-for="(o, k) in gangwon2Data"
        :key="k"
        :to="'/gangwon2/' + o.id"
        class="card mb-4"
        @click="goToDetailPage(o.id)"
        style="cursor: pointer">
        <div class="card-body">
          <h5 class="card-title">{{ o.업체명 }}</h5>
          <hr>
          <p class="card-text">
            <strong>업체 구분:</strong> {{ o.업체구분 }} <br>
            <strong>지번 주소:</strong> {{ o.지번_주소 }} <br>
            <strong>도로명 주소:</strong> {{ o.도로명_주소 }} <br>
            <strong>위도:</strong> {{ o.위도 }} <br>
            <strong>경도:</strong> {{ o.경도 }} <br>
            <strong>연락처:</strong> {{ o.연락처 }} <br>
          </p>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  mounted () {
    this.getGangwon2Data()
  },
  name: 'Gangwon',
  data () {
    return {
      gangwon2Data: []
    }
  },
  methods: {
    getGangwon2Data () {
      fetch('http://localhost:8090/gangwon2')
        .then(resp => resp.json())
        .then(data => {
          this.gangwon2Data = data
        })
        .catch(err => console.log(err))
    },
    goToDetailPage (id) {
      this.$router.push({ name: 'GangwonDetail', params: { id: id } })
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
