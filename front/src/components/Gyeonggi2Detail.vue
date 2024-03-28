
    <template>
      <div class="detail-container">
        <div class="detail-header">
          <h1>{{ o.name }}</h1>
          <div class="main-image" :style="{ backgroundImage: `url(${mainImageUrl})` }"></div>

        </div>
        <div class="detail-body">
          <div class="detail-info">
            <!-- 상세 이미지들 추가 -->
            <div class="detail-images">
              <div class="detail-image" v-for="(image, index) in detailImages" :key="index" :style="{ backgroundImage: `url(${image})` }"></div>
            </div>
            <p><strong>지역명:</strong> {{ o.location}}</p>
            <strong>주소:</strong> {{ o.address }} <br>
            <strong>전화번호:</strong> {{ o.contact }} <br>
            <strong>이용시간:</strong> {{ o.time }} <br>
            <template v-if="o.homepage !== null">
              <strong>
                홈페이지: <a :href="o.homepage">{{ o.homepage }}</a>
              </strong> <br>
            </template>
            <hr>
          </div>
          <div id="map" style="width: 100%; height: 400px;"></div>
        </div>
        <div class="detail-buttons">
          <button class="btn btn-outline-danger" v-if="isLiked === true" @click="cancelLike"><i class="fas fa-heart" style="color: red"></i>{{ likeCount }}</button>
          <button class="btn btn-outline-danger" v-else @click="like"><i class="fas fa-heart"></i>{{ likeCount }}</button>
        </div>
      </div>
    </template>
<script>
export default {
  name: 'Gyeonggi2Detail',
  data() {
    return {
      o: {},
      mainImageUrl: '',
      detailImages: [
      ],
      isLiked: null,
      likeCount: 0
    }
  },
  created() {
    this.getGyeonggi2Data(this.$route.params.id)
    this.checkLikeStatus(this.$route.params.id)
  },
  methods: {
    checkLikeStatus (id) {
      this.$axios.get(`http://localhost:8090/gyeonggi2/checkLike/${id}`)
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
      this.$axios.post(`http://localhost:8090/gyeonggi2/like/${id}`)
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
      this.$axios.post(`http://localhost:8090/gyeonggi2/cancelLike/${id}`)
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
      this.$axios.get(`http://localhost:8090/gyeonggi2/getLikeCount/${id}`)
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
        center: new window.kakao.maps.LatLng(37.5, 127),
        level: 3
      }
      this.map = new window.kakao.maps.Map(mapContainer, mapOptions)
      var geocoder = new window.kakao.maps.services.Geocoder()

      geocoder.addressSearch(this.o.주소, (result, status) => {
        if (status === window.kakao.maps.sevices.Status.OK) {
          var coords = new window.kakao.maps.LatLng(result[0].y, result[0].x)

          var marker = new window.kakao.Marker({
            map: this.map,
            position: coords
          })

          marker.setMap(this.map)

          window.kakao.maps.event.addListener(marker, 'click', () => {
            const infoWindow = new window.kakao.maps.InfoWindow({
              content: `<div style="width:150px;text-align:center;padding:6px 0;">${this.o.업체명}</div>`
            })
            infoWindow.open(this.map, marker)
          })
        }
      })
    },
    getGyeonggi2Data(id) {
      fetch(`http://localhost:8090/gyeonggi2/${id}`)
        .then(resp => resp.json())
        .then(data => {
          this.o = data
          this.mainImageUrl = require((`@/assets/gyeonggi2/${id}.jpg`))
          this.initMap()
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
    submitComment() {
      // 여기에 댓글을 서버에 저장하는 코드를 추가하세요.
      // 예시: fetch를 사용하여 서버로 댓글 데이터를 보낼 수 있습니다.
      console.log('댓글 내용:', this.commentText);
      console.log('첨부된 사진:', this.uploadedImages);
      // 저장 후 폼 초기화
      this.commentText = '';
      this.uploadedImages = [];
    },
    mounted() {
      $('#summernote').summernote({
        tabsize: 2,
        height: 500
      });
    },
    beforeDestroy() {
      // Summernote 인스턴스 제거
      if ($('#summernote').summernote) {
        $('#summernote').summernote('destroy');
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

.like-button:hover {
  border: 0.5px solid black;
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
