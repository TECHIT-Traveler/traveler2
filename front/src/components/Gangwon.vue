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
          <div class="image-container">
          <img :src="getImageUrl(o.id)" class="card-img-top image" alt="Image" />
          </div>
          <hr>
          <h5 class="card-title">{{ o.업체명 }}</h5>
          <hr>
          <p class="card-text">
            <strong>업체 구분:</strong> {{ o.업체구분 }} <br>
            <strong>지번 주소:</strong> {{ o.지번_주소 }} <br>
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
    },
    getImageUrl(id) {
      return require(`@/assets/gangwon/${id}.png`);
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
  flex: 0 0 100%; /* Allow the card to expand to 100% of its container */
  max-width: calc(600px + 30px); /* Limit the maximum width of the card */
  margin-right: 15px;
  margin-left: 15px;
  cursor: pointer;
}

.image-container {
  width: calc(100% - 0px);
  height: 500px;
  overflow: hidden;
  position: relative;
}

.image {
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  object-fit: cover;
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

