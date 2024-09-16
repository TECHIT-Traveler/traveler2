<template>
  <div class="container">
    <h1>리뷰 작성</h1>
    <form @submit.prevent="submitReview">
      <div class="form-group">
        <label for="rating">별점:</label>
        <select class="form-control" v-model="rating" id="rating">
          <option v-for="n in 5" :key="n" :value="n">{{ n }}</option>
        </select>
      </div>
      <div class="form-group">
        <label for="content">리뷰 내용:</label>
        <textarea class="form-control content" v-model="content" id="content"></textarea>
      </div>
      <button type="submit" class="btn btn-primary">리뷰 등록</button>
    </form>
  </div>
</template>

<script>
export default {
  data() {
    return {
      rating: 1,
      content: ''
    };
  },
  methods: {
    submitReview() {
      // 리뷰 데이터 전송 후 성공적으로 작성되면 리뷰 정보를 받아옴
      const reviewData = {
        rating: this.rating,
        content: this.content // 리뷰 내용
      };

      // 리뷰 데이터를 서버로 전송
      this.$axios.post(`http://localhost:8090/api/v1/places/${this.$route.params.id}/reviews`, reviewData)
        .then(response => {
          console.log('리뷰가 성공적으로 등록되었습니다.');
          this.$router.back();
        })
        .catch(error => {
          console.error('리뷰 등록 중 오류 발생', error);
          alert('리뷰를 등록하는 중 문제가 발생했습니다.');
        });
    }
  }
};
</script>

<style scoped>
.container {
  margin-top: 20px;
}
.form-group {
  margin-bottom: 20px;
}
.content {
  height: 500px;
}
</style>
