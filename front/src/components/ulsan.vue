<template>
  <div class="ulsan">
    <div v-for="(o, k) in ulsanData">
      <h1>{{ o.facility }}</h1>
      <hr>
      <span>
            {{o.streetNameAddress}}<br>
            {{o.address}}<br>
            {{o.tel}}<br>
            {{o.lat}}<br>
            {{o.lng}}<br>
          </span>
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