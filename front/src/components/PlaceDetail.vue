<template>
  <div class="detail-container">
    <div class="detail-header">
<!--      <div class="main-image" :style="{ backgroundImage: `url(${mainImageUrl})` }"></div>-->
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
    <div class="comment-form">
      <textarea v-model="commentText" placeholder="댓글을 작성해주세요" id="summernote">

      </textarea>
<!--      <input type="file" accept="image/*" @change="handleImageUpload">-->
      <button @click="submitComment">작성</button>
    </div>
  </div>
</template>

<script>
export default {
  name: 'PlaceDetail',
  data() {
    return {
      o: {},
      // mainImageUrl: '',
      isLiked: null,
      likeCount: 0
    }
  },
  created() {
    this.getPlaceData(this.$route.params.id)
    this.checkLikeStatus(this.$route.params.id)
  },
  methods: {
    checkLikeStatus (id) {
      this.$axios.get(`http://localhost:8090/api/v1/places/${id}/likes/status`)
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
    cancelLike () {
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
    updateLikeCount (id) {4
      this.$axios.get(`http://localhost:8090/api/v1/places/${id}/likes/count`)
        .then(response => {
          this.likeCount = response.data
        })
        .catch(error => {
          console.log('좋아요 수 업데이트 중 오류 발생', error)
        })
    },
    initMap () {
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
      const marker = new window.kakao.maps.Marker({ position: markerPosition });
      marker.setMap(this.map);
      window.kakao.maps.event.addListener(marker, 'click', () => {
        const infoWindow = new window.kakao.maps.InfoWindow({
          content: `<div>${this.o.name}</div>`
        });
        infoWindow.open(this.map, marker);
      });
    },

    getPlaceData (id) {
      fetch(`http://localhost:8090/api/v1/places/${id}`)
        .then(resp => resp.json())
        .then(data => {
          this.o = data
<<<<<<< HEAD
  //        this.mainImageUrl = require(`@/assets/gyeonggi/${id}.jpg`);
=======
          // this.mainImageUrl = require(`@/assets/gyeonggi/${id}.jpg`);
>>>>>>> ef16cbbd12466d5e60a42c08d2345a982ed0676a
          this.initMap()
          this.likeCount = this.o.likes.length
        })
        .catch(err => console.error(err))
    },
    // handleImageUpload(event) {
    //   const files = event.target.files;
    //   if (files) {
    //     for (let i = 0; i < files.length; i++) {
    //       const reader = new FileReader();
    //       reader.readAsDataURL(files[i]);
    //       reader.onload = (e) => {
    //         this.uploadedImages.push(e.target.result);
    //       }
    //     }
    //   }
    // },
    submitComment() {
      // 여기에 댓글을 서버에 저장하는 코드를 추가하세요.
      // 예시: fetch를 사용하여 서버로 댓글 데이터를 보낼 수 있습니다.
      console.log('댓글 내용:', this.commentText);
      console.log('첨부된 사진:', this.uploadedImages);
      // 저장 후 폼 초기화
      this.commentText = '';
      this.uploadedImages = [];
    },
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

.like-button {
  padding: 10px 20px;
  font-size: 16px;
  color: black;
  border: none;
  border-radius: 5px;
  cursor: pointer;
  transition: background-color 0.3s ease;
}

.save-button {
  padding: 10px 20px;
  font-size: 16px;
  color: #fff;
  background-color: #007bff;
  border: none;
  border-radius: 5px;
  cursor: pointer;
  transition: background-color 0.3s ease;
}

.like-button:hover {
  border: 0.5px solid black;
}

.save-button:hover {
  background-color: #0056b3;
}

.comment-form {
  margin-top: 20px;
}

.comment-form textarea {
  width: 100%;
  height: 100px;
  margin-bottom: 10px;
  padding: 10px;
  border: 1px solid #ccc;
  border-radius: 5px;
  resize: none;
}

.comment-form input[type="file"] {
  margin-bottom: 10px;
}

.comment-form button {
  padding: 10px 20px;
  font-size: 16px;
  color: #fff;
  background-color: #007bff;
  border: none;
  border-radius: 5px;
  cursor: pointer;
  transition: background-color 0.3s ease;
}

.comment-form button:hover {
  background-color: #0056b3;
}
</style>

