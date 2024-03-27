<template>
  <div class="gyeonggi">
     <!-- 검색어 입력 상자 -->
     <input type="text" v-model="searchQuery" @keyup.enter="performSearch" placeholder="시설 이름, 도시 이름을 입력하세요" class="form-control mx-auto mb-4">

    <div class="card-deck justify-content-center">
      <div
        v-for="(o, k) in filteredGyeonggiData"
        :key="k"
        :to="'/gyeonggi/' + o.id"
        class="card mb-4"
        @click="goToDetailPage(o.id)"
        style="cursor: pointer">
        <div class="card-body">
          <div class="image-container">
            <img :src="getImageUrl(o.id)" class="card-img-top image" alt="Image" />
          </div>
          <hr>
          <h5 class="card-title">{{ o.parkNm}}</h5>
          <hr>
          <p class="card-text">
            <strong>주소:</strong>{{ o.signguNm}} {{ o.emdNm }}<br>
            <strong>출입허용일:</strong> {{ o.cmgpermsnDay }} <br>
            <strong>전화번호:</strong> {{ o.reprsntTelNo }} <br>
          </p>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  mounted() {
  this.getGyeonggiData();
  },
  name: 'Gyeonggi',
  data() {
    return {
      gyeonggiData: [], // gyeonggi 데이터를 저장할 배열
      searchQuery: '', // 검색어를 저장하는 데이터 추가
      searchResult: [] // 검색 결과를 저장하는 데이터 추가
    };
  },
  computed: {
    filteredGyeonggiData() {
      if (!this.searchQuery) {
        return this.gyeonggiData;
      } else {
        return this.gyeonggiData.filter(item => {
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
    getGyeonggiData() {
      // API에서 데이터를 가져오는 메서드
      fetch('http://localhost:8090/gyeonggi')
        .then(resp => {
          if (!resp.ok) {
            throw new Error('API 호출 중 오류 발생');
          }
          return resp.json(); // 응답 데이터를 JSON 형식으로 변환
        })
        .then(data => {
          // 가져온 데이터를 gyeonggiData 배열에 할당
          this.gyeonggiData = data;
        })
        .catch(err => {
          console.error('데이터를 불러오는 중 에러 발생:', err);
          // API 호출 중 오류가 발생한 경우 처리
        });
    },
    goToDetailPage(id) {
      // 상세 페이지로 이동하는 메서드
      this.$router.push({ name: 'GyeonggiDetail', params: { id: id } });
    },
    getImageUrl(id) {
      return require(`@/assets/gyeonggi/${id}.jpg`);
    },
    performSearch() {
      fetch(`http://localhost:8090/gyeonggi/search`)
        .then(resp => resp.json())
        .then(data => {
          this.searchResult = data;
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
    max-width: calc(600px + 30px); /* Limit the maximum width of the card */
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
