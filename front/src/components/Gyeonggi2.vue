<template>
	<div class="gyeonggi2">
    <!-- 검색어 입력 상자 -->
    <input type="text" v-model="searchQuery" @keyup.enter="performSearch" placeholder="시설 이름, 도시 이름을 입력하세요" class="form-control mb-4">

		<div class="card-deck justify-content-center">
			<div
        v-for="(o, k) in filteredGyeonggi2Data"
        :key="k"
        class="card mb-4"
        @click="goToDetailPage(o.id)"
				style="cursor: pointer">
				<div class="card-body">
					<h5 class="card-title">{{ o.name }}</h5>
					<hr>
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
