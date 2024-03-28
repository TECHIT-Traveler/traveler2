<template>
  <div class="ulsan">
    <!-- 검색어 입력 상자 -->
    <input type="text" v-model="searchQuery" @keyup.enter="performSearch" placeholder="시설 이름, 도시 이름을 입력하세요" class="form-control mx-auto mb-4">

    <div class="card-deck justify-content-center">
      <div
        v-for="(o, k) in filteredUlsanData"
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
      ulsanData: [],
      searchQuery: '', // 검색어를 저장하는 데이터 추가
      searchResult: [] // 검색 결과를 저장하는 데이터 추가
    }
  },
  computed: {
    // 검색어를 기반으로 필터링된 결과를 계산하는 computed property
    filteredUlsanData() {
      if (!this.searchQuery) {
        return this.ulsanData; // 검색어가 없으면 전체 데이터를 반환
      } else {
        // 시설 이름 또는 도로명 주소 등 모든 필드에서 검색어를 찾아서 반환
        return this.ulsanData.filter(item => {
          // 검색어가 포함된 경우 true를 반환하여 필터링에 포함시킴
          return Object.values(item).some(field => {
            // 필드의 값이 문자열인 경우에만 검색 수행
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
    getUlsanData () {
      // API에서 데이터를 가져오는 데이터
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
    },
    performSearch() {
      fetch(`http://localhost:8090/ulsan/search`)
        .then(resp => resp.json())
        .then(data => {
          this.searchResult = data;
        })
        .catch(err => console.log(err));
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
  max-width: calc(500px + 30px); /* Limit the maximum width of the card */
  margin-right: 15px;
  margin-left: 15px;
  cursor: pointer; /* 커서를 포인터로 변경하여 클릭 가능한 것을 나타냅니다. */
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
