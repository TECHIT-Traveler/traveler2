<template>
	<div class="gangwon">

		<div v-for="(o, k) in gangwon2Data">
		  <h1>{{ o.업체명 }}</h1>
		  <hr>
		  <span>
			{{o.업체구분}} <br>
			{{o.지번_주소}}<br>
			{{o.도로명_주소}}<br>
			{{o.위도}}<br>
			{{o.경도}}<br>
			{{o.연락처}}<br>
		  </span>
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
		  }).catch(err => console.log(err))
	  }
  }
}
</script>

  <!-- Add "scoped" attribute to limit CSS to this component only -->
  <style scoped>
  h1, h2 {
	font-weight: normal;
  }
  ul {
	list-style-type: none;
	padding: 0;
  }
  li {
	display: inline-block;
	margin: 0 10px;
  }
  a {
	color: #42b983;
  }
  </style>
