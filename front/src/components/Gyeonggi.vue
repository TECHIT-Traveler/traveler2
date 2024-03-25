<template>
	<div class="gyeonggi">
	  <div class="card-deck justify-content-center">
		<div
			v-for="(o, k) in gyeonggiData"
			:key="k"
      		:to="'/gyeonggi/' + o.id"
			class="card mb-4"
			@click="goToDetailPage(o.id)"
			style="cursor: pointer">
		  <div class="card-body">
			<h5 class="card-title">{{ o.PARK_NM}}</h5>
			<hr>
			<p class="card-text">
			  <strong>읍면동명</strong> {{ o.EMD_NM }} <br>
        <strong>출입허용일:</strong> {{ o.CMGPERMSN_DAY }} <br>
        <strong>운영기관명</strong> {{ o.OPERTINST_NM }} <br>
        <strong>특이사항</strong> {{ o.PARTCLR_MATR }} <br>
      </p>
		  </div>
		</div>
	  </div>
	</div>
  </template>

<script>
export default {
  data() {
    return {
      gyeonggiData: []
    };
  },
  mounted() {
    this.getGyeonggiData();
  },
  methods: {
    getGyeonggiData() {
      fetch('http://localhost:8090/gyeonggi')
        .then(resp => resp.json())
        .then(data => {
          this.gyeonggiData = data;
        })
        .catch(err => {
          console.error('데이터를 불러오는 중 에러 발생:', err);
          // 오류 처리
        });
    },
    goToDetailPage(id) {
      this.$router.push({ name: 'GyeonggiDetail', params: { id: id } });
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
