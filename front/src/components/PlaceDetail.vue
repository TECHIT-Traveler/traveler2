<template>
  <div class="detail-container">
    <div class="detail-header">
      <div class="main-image" :style="{ backgroundImage: `url(${mainImageUrl})` }"></div>
    </div>
    <h1>{{ o.name }}</h1>
    <div class="detail-body">
      <div class="detail-info">
        <div class="detail-item" v-if="o.division">업종:{{ o.division }}</div>
        <div class="detail-item" v-if="o.address">주소:{{ o.address }}</div>
        <div class="detail-item" v-if="o.ar">규모시설면적: {{ o.ar }} </div>
        <div class="detail-item" v-if="o.cmgpermsnTm">출입허용시간: {{ o.cmgpermsnTm }} </div>
        <div class="detail-item" v-if="o.cmgpermsnDay">출입허용일: {{ o.cmgpermsnDay }} </div>
        <div class="detail-item" v-if="o.opertinstNm">운영기관명: {{ o.opertinstNm }} </div>
        <div class="detail-item" v-if="o.contact">전화번호: {{ o.contact }} </div>
        <div class="detail-item" v-if="o.utlzChrg">비용: {{ o.expn }} {{ o.utlzChrg }}</div>
        <div class="detail-item" v-if="o.partclrMatr">특이사항: {{ o.partclrMatr }}</div>
        <div class="detail-item" v-if="o.time">이용시간: {{ o.time }}</div>
        <div class="detail-item" v-if="o.cls">휴무일: {{ o.cls }}</div>
        <div class="detail-item" v-if="o.remarks">비고: {{ o.remarks }}</div>
        <div class="detail-item" v-if="o.zipCode">우편번호: {{ o.zipCode }}</div>

        <div v-if="o.homepage !== null">
          <div class="detail-item" v-if="o.homepage">홈페이지: <a :href="o.homepage">{{ o.homepage }}</a></div>
        </div>
      </div>
      <div id="map" style="width: 100%; height: 400px;"></div>
    </div>
    <div class="detail-buttons">
      <button class="btn btn-outline-danger" v-if="isLiked === true" @click="cancelLike"><i class="fas fa-heart" style="color: red"></i>{{ likeCount }}</button>
      <button class="btn btn-outline-danger" v-else @click="like"><i class="fas fa-heart"></i>{{ likeCount }}</button>
    </div>
    <!-- 리뷰 표시 부분 -->
    <div class="review-container">
      <div class="card" v-for="(review, index) in reviews" :key="index">
        <div class="card-body">
          <div class="d-flex justify-content-between">
            <h5 class="card-title">{{ review.member.nickname }}</h5>
            <div>
              <template v-for="i in review.rating">
                <i class="fas fa-star"></i>
              </template>
            </div>
          </div>
          <p class="card-text">{{ review.content }}</p>
          <div v-if="checkReviewOwner(review.member.id)">
            <button class="btn btn-primary" @click="editReview(review)">수정</button>
            <button class="btn btn-danger" @click="deleteReview(review)">삭제</button>
          </div>
        </div>
      </div>
    </div>
    <button class="btn btn-primary" @click="goToReviewPage(o.id)">리뷰 작성하기</button>
<!--    <div class="review-form">-->
<!--      <textarea v-model="reviewText" placeholder="리뷰를 작성해주세요"></textarea>-->
<!--      <input type="file" accept="image/*" @change="handleImageUpload">-->
<!--      <button class="btn btn-outline-primary" @click="submitReview">작성</button>-->
<!--    </div>-->
  </div>
</template>

<script>
export default {
  name: 'PlaceDetail',
  data() {
    return {
      o: {},
      mainImageUrl: '',
      isLiked: null,
      likeCount: 0,
      reviews: [],
      userInfo: null
    }
  },
  created() {
    this.getReviews()
    this.getPlaceData(this.$route.params.id)
    this.checkLikeStatus(this.$route.params.id)
    this.getUserInfo()
  },
  methods: {
    checkLikeStatus(id) {
      this.$axios.get(`http://localhost:8090/api/v1/places/${id}/likes/status`)
        .then(response => {
          this.isLiked = response.data
          console.log('isLiked: ', this.isLiked)
        })
        .catch(error => {
          console.log(error)
        })
    },
    like() {
      const id = this.o.id
      this.$axios.post(`http://localhost:8090/api/v1/places/${id}/likes`)
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
    cancelLike() {
      const id = this.o.id
      this.$axios.delete(`http://localhost:8090/api/v1/places/${id}/likes`)
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
    updateLikeCount(id) {
      4
      this.$axios.get(`http://localhost:8090/api/v1/places/${id}/likes/count`)
        .then(response => {
          this.likeCount = response.data
        })
        .catch(error => {
          console.log('좋아요 수 업데이트 중 오류 발생', error)
        })
    },
    initMap() {
      console.log("위도:", this.o.latitude)
      const mapContainer = document.getElementById('map')
      let mapOptions = {};

      if (!this.o.longitude) {
        var geocoder = new window.kakao.maps.services.Geocoder();
        geocoder.addressSearch(this.o.address, (result, status) => {
          if (status === window.kakao.maps.services.Status.OK) {
            this.o.latitude = result[0].y;
            this.o.longitude = result[0].x;

            // 위도와 경도를 가져온 후에 지도를 초기화합니다.
            this.initializeMap(mapContainer);
          }
        });
      } else {
        // 이미 위도와 경도가 존재하는 경우에는 그대로 사용합니다.
        this.initializeMap(mapContainer);
      }
    },

    initializeMap(mapContainer) {
      let mapOptions = {};
      mapOptions.center = new window.kakao.maps.LatLng(this.o.latitude, this.o.longitude);
      mapOptions.level = 3;
      this.map = new window.kakao.maps.Map(mapContainer, mapOptions);
      const markerPosition = new window.kakao.maps.LatLng(this.o.latitude, this.o.longitude);
      const marker = new window.kakao.maps.Marker({position: markerPosition});
      marker.setMap(this.map);
      window.kakao.maps.event.addListener(marker, 'click', () => {
        const infoWindow = new window.kakao.maps.InfoWindow({
          content: `<div>${this.o.name}</div>`
        });
        infoWindow.open(this.map, marker);
      });
    },

    getPlaceData(id) {
      fetch(`http://localhost:8090/api/v1/places/${id}`)
        .then(resp => resp.json())
        .then(data => {
          this.o = data
          // this.mainImageUrl = require(`@/assets/gyeonggi/${id}.jpg`);
          this.initMap()
          this.likeCount = this.o.likes.length
        })
        .catch(err => console.error(err))
    },
    handleImageUpload(event) {
      const files = event.target.files;
      if (files) {
        for (let i = 0; i < files.length; i++) {
          const reader = new FileReader();
          reader.readAsDataURL(files[i]);
          reader.onload = (e) => {
            this.uploadedImages.push(e.target.result);
          }
        }
      }
    },
    getReviews() {
      const placeId = this.$route.params.id;
      this.$axios.get(`http://localhost:8090/api/v1/places/${placeId}/reviews`)
        .then(response => {
          this.reviews = response.data;
          console.log('reviews: ', this.reviews)
        })
        .catch(error => {
          console.log('등록된 리뷰 불러오는 중 오류 발생', error);
        })
    },
    editReview(review) {
      // 수정 로직 추가
      const confirmEdit = confirm('리뷰를 수정하시겠습니까?');
      if (confirmEdit) {
        // 수정할 리뷰의 ID를 얻어옵니다.
        const reviewId = review.id;
        this.goToReviewModifyPage(reviewId, this.o.id);
      }
    },
    deleteReview(review) {
      // 삭제 로직 추가
      const confirmDelete = confirm('리뷰를 삭제하시겠습니까?');
      if (confirmDelete) {
        // 삭제할 리뷰의 ID를 얻어옵니다.
        const reviewId = review.id;
        // 리뷰 삭제 API에 요청을 보냅니다.
        this.$axios.delete(`http://localhost:8090/api/v1/places/${this.$route.params.id}/reviews/${reviewId}`)
          .then(response => {
            console.log('리뷰 삭제가 성공적으로 완료되었습니다.');
            this.getReviews();
            // 성공적으로 삭제되면 화면을 갱신하거나 다시 불러올 수 있는 작업을 수행합니다.
          })
          .catch(error => {
            console.error('리뷰 삭제 중 오류 발생', error);
            alert('리뷰 삭제 중 문제가 발생했습니다.');
          });
      }
    },
    goToReviewPage (placeId) {
      this.$router.push({ name: 'Review', params: { id: placeId }});
    },
    goToReviewModifyPage(reviewId, placeId) {
      this.$router.push({name: 'ReviewModify', params: {reviewId: reviewId, placeId: placeId}});
    },
    checkReviewOwner(reviewOwnerId) {
      // 로그인한 사용자인 경우에만 비교를 수행하도록 확인
      if (this.userInfo) {
        return this.userInfo.id === reviewOwnerId;
      }
      // 로그인하지 않은 경우에는 항상 false를 반환하여 리뷰 수정 및 삭제 버튼을 표시하지 않음
      return false;
    },
    getUserInfo() {
      this.$axios.get('http://localhost:8090/member/login-info', {
        withCredentials: true
      }).then(response => {
        this.userInfo = response.data
      }).catch(error => {
        console.log('사용자 정보 요청 실패', error)
      })
    }
  }
}
</script>
<style scoped>
.detail-container {
  margin: 50px auto;
  max-width: 600px;
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
  height: 300px;
  background-size: cover;
  background-position: center;
  background-repeat: no-repeat;
}

.detail-item {
  font-size: 18px;
  text-align: left;
  font-weight: 500;
  margin-bottom: 10px;
  padding: 10px;
}
/* 나머지 스타일은 그대로 두고 버튼과 댓글 폼의 스타일을 추가합니다 */
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
.text-truncate {
  max-width: 250px;
  white-space: nowrap;
  overflow: hidden;
  text-overflow: ellipsis;
}
.fa-star {
  color: gold;
}
</style>

