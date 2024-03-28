<template>
	<div class="gyeonggi2">
    <!-- 검색어 입력 상자 -->
    <input type="text" v-model="searchQuery" @keyup.enter="performSearch" placeholder="시설 이름, 도시 이름을 입력하세요" class="form-control mx-auto mb-4">

		<div class="card-deck justify-content-center">
			<div
        v-for="(o, k) in filteredGyeonggi2Data"
        :key="k"
        class="card mb-4"
        @click="goToDetailPage(o.id)"
				style="cursor: pointer">
				<div class="card-body">
          <div class="image-container">
            <img :src="getImageUrl(o.id)" class="card-img-top image" alt="Image" />
          </div>
          <hr>
          <h5 class="card-title">{{ o.name }}</h5>
					<p class="card-text">
						<strong>주소:</strong> {{ o.address }} <br>
						<strong>지역명:</strong> {{ o.location }} <br>
						<strong>전화번호:</strong> {{ o.contact }} <br>
						<strong>이용시간:</strong> {{ o.time }} <br>
						<strong>홈페이지:</strong> {{ o.homepage }} <br>
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
      gyeonggi2Data: [], // Gyeonggi2 데이터를 저장할 배열
      searchQuery: '', // 검색어를 저장하는 데이터 추가
      searchResult: [] // 검색 결과를 저장하는 데이터 추가
	  }
	},
    computed: {
      filteredGyeonggi2Data() {
        if (!this.searchQuery) {
          return this.gyeonggi2Data;
        } else {
          return this.gyeonggi2Data.filter(item => {
            return Object.values(item).some(field => {
              if (typeof field === 'string') {
                return field.toLowerCase().includes(this.searchQuery.toLowerCase());
              }
              return false;
            });
          });
        }
      }
    },
	methods: {
	  getGyeonggi2Data() {
		fetch('http://localhost:8090/gyeonggi2')
		  .then(resp => resp.json())
		  .then(data => {
			this.gyeonggi2Data = data
		  })
      .catch(err => console.log(err))
	  },
	  goToDetailPage(id) {
      	this.$router.push({ name: 'Gyeonggi2Detail', params: { id: id } })
    },
    getImageUrl(id) {
      return require(`@/assets/gyeonggi2/${id}.jpg`);
    },
    performSearch() {
      fetch(`http://localhost:8090/gyeonggi2/search`)
        .then(resp => resp.json())
        .then(data => {
          this.searchResult  = data;
        })
        .catch(err => console.log(err));
    }
	}
  };

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
    max-width: calc(500px + 30px); /* Limit the maximum width of the card */
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
  .form-control {
    width: 1280px;
  }
</style>
