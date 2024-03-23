<template>
    <div>
      <h1>상세 페이지</h1>
      <div card-body>
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
  </template>
  
  <script>
  export default {
    name: 'GangwonDetail',
    data() {
      return {
        o: {}
      }
    },
    created() {
      this.getGangwonData(this.$route.params.id)
    },
    methods: {
      getGangwonData(id) {
        fetch(`http://localhost:8090/gangwon2/${id}`)
          .then(resp => resp.json())
          .then(data => {
            this.o = data
          })
          .catch(err => console.error(err))
      }
    }
  }
  </script>
  