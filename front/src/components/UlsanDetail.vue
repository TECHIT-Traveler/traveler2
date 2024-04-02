<template>
  <div class="detail-container">
    <div class="detail-header">
      <h1>{{ o.facility }}</h1>
      <div class="main-image" :style="{ backgroundImage: `url(${mainImageUrl})` }"></div>
    </div>
    <div class="detail-body">
      <div class="detail-info">
        <p><strong>지번 주소:</strong> {{ o.address }}</p>
        <p><strong>도로명 주소:</strong> {{ o.streetNameAddress }}</p>
        <p><strong>연락처:</strong> {{ o.tel }}</p>
      </div>
      <div id="map" style="width: 100%; height: 400px;"></div>
    </div>
    <!-- detail-buttons -->
    <div class="detail-buttons">
      <button class="btn btn-outline-danger" v-if="isLiked === true" @click="cancelLike"><i class="fas fa-heart"
          style="color: red"></i>{{ likeCount }}</button>
      <button class="btn btn-outline-danger" v-else @click="like"><i class="fas fa-heart"></i>{{ likeCount
        }}</button>
    </div>
   <!--  <div class="reviews">
      <h2>리뷰 목록</h2>
      <div v-if="reviews.length === 0">리뷰가 없습니다.</div>
      <div v-else>
        <! 리뷰 목록을 폼 형식으로 표시
        <div v-for="(review, index) in reviews" :key="index" class="review-form">
          <table class="table table-hover">
            <tr>
              <th class="author" scope="col">작성자:{{ review.nickname }}</th>
              <th scope="col">{{ review.body }}</th>
            </tr>
            <! 리뷰 내용 표시

          </table>
        </div>
      </div>
    </div>
    <! 등록폼
    <div class="review-form mt-3">
      <form @submit.prevent="submitReview">
        <div class="form-group">
          <textarea class="form-control" v-model="reviewText" placeholder="리뷰를 작성해주세요" id="summernote"></textarea>
        </div>
        <div class="form-group">
          <input type="file" class="form-control-file" accept="image/*" @change="handleImageUpload">
        </div>
        <div class="d-grid gap-2">
          <button type="submit" class="btn btn-lg btn-primary">작성</button>
        </div>
      </form>
  </div> -->
    </div>
</template>

<script>
export default {
  name: 'UlsanDetail',
  data() {
    return {
      o: {},
      mainImageUrl: '',
      reviewText: '', // 리뷰 텍스트
      reviews: [], // 리뷰 목록
      uploadedImages: [], // 업로드된 이미지들
      userInfo: null, // 사용자 정보 객체 추가
      isLiked: null,
      likeCount: 0
    }
  },
  created() {
    this.getUlsanData(this.$route.params.id)
    this.getUserInfoAndReviews() // 사용자 정보 및 리뷰 목록 가져오기
    this.checkLikeStatus(this.$route.params.id)
  },
  methods: {
    checkLikeStatus (id) {
      this.$axios.get(`http://localhost:8090/ulsan/checkLike/${id}`)
        .then(response => {
          this.isLiked = response.data
          console.log('isLiked: ', this.isLiked)
        })
        .catch(error => {
          console.log(error)
        })
    },
    like () {
      const id = this.o.id
      this.$axios.post(`http://localhost:8090/ulsan/like/${id}`)
        .then(response => {
          console.log('좋아요 처리 성공')
          this.isLiked = true
          this.updateLikeCount(id)
        })
        .catch(error => {
          console.error('좋아요 처리 중 오류 발생', error)
          alert('로그인이 필요합니다.')
        })
    },
    cancelLike () {
      const id = this.o.id
      this.$axios.post(`http://localhost:8090/ulsan/cancelLike/${id}`)
        .then(response => {
          console.log('좋아요 취소 처리 성공')
          this.isLiked = false
          this.updateLikeCount(id)
        })
        .catch(error => {
          console.error('좋아요 처리 중 오류 발생', error)
          alert('로그인이 필요합니다.')
        })
    },
    updateLikeCount (id) {
      this.$axios.get(`http://localhost:8090/ulsan/getLikeCount/${id}`)
        .then(response => {
          this.likeCount = response.data
        })
        .catch(error => {
          console.log('좋아요 수 업데이트 중 오류 발생', error)
        })
    },
    initMap () {
      const mapContainer = document.getElementById('map')
      const mapOptions = {
        center: new window.kakao.maps.LatLng(this.o.lat, this.o.lng),
        level: 3
      }
      this.map = new window.kakao.maps.Map(mapContainer, mapOptions)
      const markerPosition = new window.kakao.maps.LatLng(this.o.lat, this.o.lng)
      const marker = new window.kakao.maps.Marker({ position: markerPosition })
      marker.setMap(this.map)
      window.kakao.maps.event.addListener(marker, 'click', () => {
        const infoWindow = new window.kakao.maps.InfoWindow({
          content: `<div>${this.o.pafacility}</div>`
        })
        infoWindow.open(this.map, marker)
      })
    },
    getUlsanData (id) {
      fetch(`http://localhost:8090/ulsan/${id}`)
        .then(resp => resp.json())
        .then(data => {
          this.o = data
          this.mainImageUrl = require(`@/assets/ulsan/${id}.jpg`);
          this.initMap()
        })
        .catch(err => console.error(err))
    },
    handleImageUpload (event) {
      const files = event.target.files
      if (files) {
        for (let i = 0; i < files.length; i++) {
          const reader = new FileReader()
          reader.readAsDataURL(files[i])
          reader.onload = (e) => {
            this.uploadedImages.push(e.target.result)
          }
        }
      }
    },
//     async getUserInfoAndReviews() {
//       try {
//         await this.getUserInfo();
//         await this.getReviews();
//         // 여기서 submitReview 메소드 호출
//       } catch (error) {
//         console.error(error);
//       }
//     },
//     // 사용자 정보 가져오기
//     async getUserInfo() {
//       try {
//         const response = await this.$axios.get('http://localhost:8090/member/login-info', {
//           withCredentials: true
//         });
//         this.userInfo.id = response.data.id;
//         this.userInfo.nickname = response.data.nickname;
//       } catch (error) {
//         console.error('사용자 정보 요청 실패:', error);
//       }
//     },
//     // 리뷰 목록 가져오기
//     async getReviews() {
//       try {
//         const response = await fetch('http://localhost:8090/api/reviews/all');
//         const data = await response.json();
//         this.reviews = data;
//       } catch (error) {
//         console.error(error);
//       }
//     },
//     // 리뷰 작성 및 업로드
//     submitReview() {
//       const reviewRequest = {
//         nickname: this.nickname,
//         body: this.reviewText,
//         placeId: this.placeId,
//         placeType: "ULSAN",
//       };
//       fetch(`http://localhost:8090/api/reviews/create`, {
//         method: 'POST',
//         headers: {
//           'Content-Type': 'application/json',
//         },
//         body: JSON.stringify({
//         body: this.reviewText,
//         placeId: this.id,
//         placeType: "ULSAN",
//       }),
//       })
//         .then(resp => resp.json())
//         .then(data => {
//           console.log('Review added successfully');
//           this.getReviews(); // 리뷰 목록을 새로고침
//           this.reviewText = '';
//         })
//         .catch(error => {
//           console.error('Error adding review:', error);
//         });
//     },
  }
}
</script>


<style scoped>
.detail-container {
  margin: 50px auto;
  max-width: 480px;
  padding: 20px;
  border: 1px solid #ccc;
  border-radius: 10px;
  background-color: #f9f9f9;
}

.detail-header {
  text-align: center;
  margin-bottom: 20px;
}

.detail-body {
  background-color: #fff;
  padding: 20px;
  border-radius: 10px;
  box-shadow: 0px 2px 4px rgba(0, 0, 0, 0.1);
}

.detail-info {
  font-size: 16px;
}

.detail-info p {
  margin: 8px 0;
}

.main-image {
  width: 100%;
  height: 500px;
  background-size: cover;
  background-position: center;
  background-repeat: no-repeat;
}

.detail-images {
  display: flex;
  justify-content: space-between;
  margin-top: 20px;
}

.detail-image {
  width: 150px;
  height: 150px;
  background-size: cover;
  background-position: center;
  background-repeat: no-repeat;
}

/* 나머지 스타일은 그대로 두고 버튼과 리뷰 폼의 스타일을 추가합니다 */
.detail-buttons {
  display: flex;
  justify-content: space-between;
  margin-top: 20px;
}

.review-form {
  margin-top: 20px;
}

.review-form textarea {
  width: 100%;
  height: 100px;
  margin-bottom: 10px;
  padding: 10px;
  border: 1px solid #ccc;
  border-radius: 5px;
  resize: none;
}

.review-form input[type="file"] {
  margin-bottom: 10px;
}
.author {
  text-align: left;
}
</style>
