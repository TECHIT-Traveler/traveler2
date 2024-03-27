<template>
  <div class="ulsan">
    <div class="card-deck justify-content-center">
      <div
        v-for="(o, k) in ulsanData"
        :key="k"
        :to="'/ulsan/' + o.id"
        class="card mb-4"
        @click="goToDetailPage(o.id)"
        style="cursor: pointer">
        <div class="card-body">
          <div class="image-container">
            <img :src="getImageUrl(o.id)" class="card-img-top image" alt="Image" />
          </div>
          <hr>
          <h5 class="card-title">{{ o.facility}}</h5>
          <hr>
          <p class="card-text">
            <strong>지번 주소:</strong> {{ o.address }} <br>
            <strong>도로명 주소:</strong> {{ o.streetNameAddress }} <br>
            <strong>위도:</strong> {{ o.lat }} <br>
            <strong>경도:</strong> {{ o.lng }} <br>
            <strong>연락처:</strong> {{ o.tel }} <br>
          </p>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  mounted () {
    this.getUlsanData()
  },
  name: 'Ulsan',
  data () {
    return {
      ulsanData: []
    }
  },
  methods: {
    getUlsanData () {
      fetch('http://localhost:8090/ulsan')
        .then(resp => resp.json())
        .then(data => {
          this.ulsanData = data
        })
        .catch(err => console.log(err))
    },
    goToDetailPage (id) {
      this.$router.push({ name: 'UlsanDetail', params: { id: id } })
    },
    getImageUrl(id) {
      return require(`@/assets/ulsan/${id}.jpg`);
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
  width: 600px;
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