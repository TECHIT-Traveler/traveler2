<template>
  <div class="gangwon">
    <!-- 검색어 입력 상자 -->
    <input type="text" v-model="searchQuery" @keyup.enter="performSearch" placeholder="시설 이름, 도시 이름을 입력하세요" class="form-control mx-auto mb-4">

    <div class="card-deck justify-content-center">
      <div
        v-for="(o, k) in filteredGangwonData"
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
          <h5 class="card-title">{{ o.name }}</h5>
          <hr>
          <p class="card-text">
            <strong>업체 구분:</strong> {{ o.division }} <br>
            <strong>지번 주소:</strong> {{ o.streetAddress }} <br>
            <strong>연락처:</strong> {{ o.contact }} <br>
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
      gangwon2Data: [], //gangwon 데이터를 저장할 배열
      searchQuery: '', // 검색어를 저장하는 데이터 추가
      searchResult: [] // 검색 결과를 저장하는 데이터 추가
    }
  },
  computed: {
    filteredGangwonData() {
      if (!this.searchQuery) {
        return this.gangwon2Data;
      } else {
        return this.gangwon2Data.filter(item => {
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
    getGangwon2Data () {
      // API에서 데이터를 가져오는 메서드
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
    },
    performSearch() {
      fetch(`http://localhost:8090/gangwon2/search`)
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
