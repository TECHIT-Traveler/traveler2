<template>
  <div class="detail-container">
    <div class="detail-header">
      <h1>{{ o.facility }}</h1>
      <div class="main-image" :style="{ backgroundImage: `url(${mainImageUrl})` }"></div>
    </div>
    <div class="detail-body">
      <div class="detail-info">
        <!-- <div class="detail-images">
          <div class="detail-image" v-for="(image, index) in detailImages" :key="index" :style="{ backgroundImage: `url(${image})` }"></div>
        </div> -->
        <p><strong>지번 주소:</strong> {{ o.address }}</p>
        <p><strong>도로명 주소:</strong> {{ o.streetNameAddress }}</p>
        <p><strong>연락처:</strong> {{ o.tel }}</p>
      </div>
      <div id="map" style="width: 100%; height: 400px;"></div>
    </div>
    <div class="detail-buttons">
      <button class="like-button"><i class="fas fa-heart"></i> 좋아요</button>
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
  name: 'UlsanDetail',
  data () {
    return {
      o: {},
      mainImageUrl: '',
      detailImages: [
        'https://via.placeholder.com/150x150',
        'https://via.placeholder.com/150x150',
        'https://via.placeholder.com/150x150'
      ] // 상세 이미지 URL들
    }
  },
  created () {
    this.getUlsanData(this.$route.params.id)
  },
  methods: {
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
    submitComment () {
      // 여기에 댓글을 서버에 저장하는 코드를 추가하세요.
      // 예시: fetch를 사용하여 서버로 댓글 데이터를 보낼 수 있습니다.
      console.log('댓글 내용:', this.commentText)
      console.log('첨부된 사진:', this.uploadedImages)
      // 저장 후 폼 초기화
      this.commentText = ''
      this.uploadedImages = []
    },
    mounted () {
      $('#summernote').summernote({
        tabsize: 2,
        height: 500
      })
    },
    beforeDestroy () {
      // Summernote 인스턴스 제거
      if ($('#summernote').summernote) {
        $('#summernote').summernote('destroy')
      }
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

.like-button,
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

.like-button:hover,
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
