<template>
  <div>
    <h2>강원 데이터</h2>
    <div class="row">
      <div v-for="(item, index) in rawData" :key="index" class="col-md-4 mb-4">
        <div class="card">
          <div class="card-body">
            <h5 class="card-title">{{ item.name }}</h5>
            <h6 class="card-subtitle mb-2 text-muted">{{ item.category }}</h6>
            <p class="card-text">주소: {{ item.address }}</p>
            <p class="card-text" v-if="item.contact">연락처: {{ item.contact }}</p>
            <p class="card-text" v-if="!item.contact">연락처 없음</p>
            <router-link :to="{name: 'Details', params: {id: item.id}}" class="btn btn-primary detailBtn">자세히 보기</router-link>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'Gangwon',
  data () {
    return {
      rawData: [
        { id: 1, address: '강원 양양군 현남면 인구중앙길 103', category: '식음료', name: '103번길' },
        { id: 2, address: '강원 강릉시 임영로141번길 4-6', category: '식음료', name: '1938슬로우', contact: '033-646-4649' },
        { id: 3, address: '강원 강릉시 경강로 2070', category: '동물병원', name: '24시보듬동물병원', contact: '033-655-7975' },
        { id: 4, address: '강원 정선군 북평면 장열길 83-2', category: '숙박지', name: '365행복펜션', contact: '0507-1423-1365' },
        { id: 5, address: '강원 속초시 중앙부두길 51', category: '식음료', name: '5구도선장', contact: '033-636-5950' },
        { id: 6, address: '강원 속초시 장사항해안길 25', category: '식음료', name: 'Cafe COEL', contact: '0507-1344-4321' },
        { id: 7, address: '강원 고성군 현내면 통일전망대로 369', category: '관광지', name: 'DMZ박물관', contact: '033-681-0625' },
        { id: 8, address: '강원 속초시 영랑해안길 159', category: '숙박지', name: 'EST펜션', contact: '033-633-2345' },
        { id: 9, address: '강원 평창군 대관령면 올림픽로 715', category: '숙박지', name: 'hj매그놀리아용평호텔앤리조트', contact: '033-330-7511' },
        { id: 10, address: '강원 동해시 항골길 94', category: '식음료', name: 'J카페', contact: '033-534-3934' },
        { id: 11, address: '강원 양양군 현북면 하조대2길 48-101', category: '식음료', name: 'mtd버거하우스', contact: '0507-1352-0895' },
        { id: 12, address: null, category: '식음료', name: 'Rest', contact: '033-643-9375' },
        { id: 13, address: '강원 강릉시 주문진읍 주문로 59', category: '숙박지', name: 'SL호텔 강릉', contact: '033-920-1800' },
        { id: 14, address: '강원 철원군 갈말읍 상사로 377', category: '숙박지', name: '가산농원캠핑장', contact: '033-452-9995' },
        { id: 15, address: '강원 평창군 봉평면 흥정계곡길 72', category: '숙박지', name: '가을동화펜션', contact: null },
        { id: 16, address: '강원 원주시 지정면 소금산길 12', category: '관광지', name: '간현관광지', contact: '033-749-4860' },
        { id: 17, address: '강원 강릉시 연곡면 영진길 63', category: '식음료', name: '갈매기다방', contact: '0507-1317-1874' },
        { id: 18, address: '강원 춘천시 신북읍 신샘밭로 674', category: '식음료', name: '감자밭', contact: '1566-3756' },
        { id: 19, address: null, category: '식음료', name: '감자아일랜드 온의점', contact: '0507-1356-4681' },
        { id: 20, address: '강원 강릉시 해안로 621번길 12', category: '숙박지', name: '강릉 로뎀나무 펜션', contact: '0505-356-9646' },
        { id: 21, address: '강원 강릉시 옥가로 18', category: '동물병원', name: '강릉동물병원', contact: '0507-1315-7584' },
        { id: 22, address: '강원 강릉시  왕산면 안반데기1길 203', category: '관광지', name: '강릉안반데기관광농원', contact: '033-644-8552' },
        { id: 23, address: '강원 강릉시 창해로14번길 20-1', category: '관광지', name: '강릉커피거리', contact: '033-640-4531', address_detail: '강원 강릉시 견소동 286' },
        { id: 24, address: '강원 강릉시 창해로14번길 55-11', category: '관광지', name: '강릉항', contact: '033-640-5420', address_detail: '강원 강릉시 견소동 286-5' },
        { id: 25, address: null, category: '관광지', name: '강문솟대다리', contact: '033-660-3864', address_detail: '강원 강릉시 강문동 248-4' },
        { id: 26, address: '강원 강릉시 창해로350번길 7', category: '관광지', name: '강문해변', contact: '033-660-3865', address_detail: '강원 강릉시 강문동 182-1' },
        { id: 27, address: '강원 춘천시 신북읍 신샘밭로 621', category: '식음료', name: '강산숯불닭갈비막국수', contact: '033-242-9972', address_detail: '강원 춘천시 신북읍 천전리 672-1' },
        { id: 28, address: '강원 춘천시 남산면 충효로 437', category: '관광지', name: '강아지숲 테마파크', contact: '033-913-1400', address_detail: '강원 춘천시 남산면 광판리 1193' },
        { id: 29, address: '강원 영월군 영월읍 중앙로 75', category: '동물병원', name: '강원동물병원', contact: '033-373-0788', address_detail: '강원 영월군 영월읍 영흥리 845-10' },
        { id: 30, address: null, category: '동물병원', name: '강원동물병원', contact: '033-641-7584', address_detail: '강원 원주시 평원로 67 (중앙동)' }
      ]
    }
  }
}
</script>

<style>
.gangwondo {
  width: 800px;
  margin: 20px auto;
}
.card {
  height: 220px;
}
</style>
