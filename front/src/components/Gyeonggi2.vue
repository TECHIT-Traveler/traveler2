<template>
	<div class="gyeonggi2">
		<div class="card-deck justify-content-center">
			<div v-for="(o, k) in gyeonggi2Data" :key="k" class="card mb-4" @click="goToDetailPage(o.id)"
				style="cursor: pointer">
				<div class="card-body">
          <div class="image-container">
            <img :src="getImageUrl(o.id)" class="card-img-top image" alt="Image" />
          </div>
          <hr>
					<h5 class="card-title">{{ o.업체명 }}</h5>
					<hr>
					<p class="card-text">
						<strong>주소:</strong> {{ o.주소 }} <br>
						<strong>지역명:</strong> {{ o.지역명 }} <br>
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
    },
    getImageUrl(id) {
      return require(`@/assets/gyeonggi2/${id}.jpg`);
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
