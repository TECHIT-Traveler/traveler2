<template>
  <div class="detail-container">
    <div class="detail-header">
      <h1>{{ o.업체명 }}</h1>
      <div class="main-image" :style="{ backgroundImage: `url(${mainImageUrl})` }"></div>
    </div>
    <div class="detail-body">
      <div class="detail-info">
        <p><strong>업체 구분:</strong> {{ o.업체구분 }}</p>
        <!-- 상세 이미지들 추가 -->
        <div class="detail-images">
          <div class="detail-image" v-for="(image, index) in detailImages" :key="index" :style="{ backgroundImage: `url(${image})` }"></div>
        </div>
        <p><strong>지번 주소:</strong> {{ o.지번_주소 }}</p>
        <p><strong>도로명 주소:</strong> {{ o.도로명_주소 }}</p>
        <p><strong>위도:</strong> {{ o.위도 }}</p>
        <p><strong>경도:</strong> {{ o.경도 }}</p>
        <p><strong>연락처:</strong> {{ o.연락처 }}</p>
      </div>
      <div id="map" style="width: 100%; height: 400px;"></div>
    </div>
    <div class="detail-buttons">
      <button class="like-button" v-if="isLiked === true" @click="cancelLike"><i class="fas fa-heart" style="color: red"></i>{{ likeCount }}</button>
      <button class="like-button" v-else @click="like"><i class="fas fa-heart" style="color: grey"></i>{{ likeCount }}</button>
      <button class="save-button"><i class="fas fa-star"></i> 저장</button>
    </div>
    <div class="comment-form">
      <textarea v-model="commentText" placeholder="댓글을 작성해주세요" id="summernote"></textarea>
      <input type="file" accept="image/*" @change="handleImageUpload">
      <button @click="submitComment">작성</button>
    </div>
  </div>
</template>

<script>
export default {
  name: 'GangwonDetail',
  data () {
    return {
      o: {},
      mainImageUrl: '',
      detailImages: [
        'https://via.placeholder.com/150x150',
        'https://via.placeholder.com/150x150',
        'https://via.placeholder.com/150x150'
      ],
      isLiked: null,
      likeCount: 0
    }
  },
  created () {
    this.getGangwonData(this.$route.params.id)
    this.checkLikeStatus(this.$route.params.id)
  },
  methods: {
    checkLikeStatus (postId) {
      this.$axios.get(`http://localhost:8090/gangwon2/checkLike/${postId}`)
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
      this.$axios.post(`http://localhost:8090/gangwon2/like/${id}`)
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
      this.$axios.post(`http://localhost:8090/gangwon2/cancelLike/${id}`)
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
      this.$axios.get(`http://localhost:8090/gangwon2/getLikeCount/${id}`)
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
        center: new window.kakao.maps.LatLng(this.o.위도, this.o.경도),
        level: 3
      }
      this.map = new window.kakao.maps.Map(mapContainer, mapOptions)
      const markerPosition = new window.kakao.maps.LatLng(this.o.위도, this.o.경도)
      const marker = new window.kakao.maps.Marker({ position: markerPosition })
      marker.setMap(this.map)
      window.kakao.maps.event.addListener(marker, 'click', () => {
        const infoWindow = new window.kakao.maps.InfoWindow({
          content: `<div>${this.o.업체명}</div>`
        })
        infoWindow.open(this.map, marker)
      })
    },
    getGangwonData (id) {
      fetch(`http://localhost:8090/gangwon2/${id}`)
        .then(resp => resp.json())
        .then(data => {
          this.o = data
          this.mainImageUrl = require(`@/assets/gangwon/${id}.png`);
          this.initMap()
          this.likeCount = this.o.likes.length
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
    submitComment () {
      // 여기에 댓글을 서버에 저장하는 코드를 추가하세요.
      // 예시: fetch를 사용하여 서버로 댓글 데이터를 보낼 수 있습니다.
      console.log('댓글 내용:', this.commentText)
      console.log('첨부된 사진:', this.uploadedImages)
      // 저장 후 폼 초기화
      this.commentText = ''
      this.uploadedImages = []
    }
  },
  mounted () {
    global.$('#summernote').summernote({
      tabsize: 2,
      height: 500
    })
  },
  beforeDestroy () {
    // Summernote 인스턴스 제거
    if (global.$('#summernote').summernote) {
      global.$('#summernote').summernote('destroy')
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
