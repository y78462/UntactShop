# 🛍️프로젝트 개요
![logo](https://user-images.githubusercontent.com/69866091/152367621-5d238de1-99f2-47b3-90c7-0dd8547c05d8.png)

> 카테고리별로 지하도상가 지도 정보를 제공하고 점포 내 상품을 온라인으로 구매할 수 있도록 하는 어플리케이션입니다. </br>

지하도상가 지도 정보를 사용하기 위해 [**서울시설공단**](https://www.sisul.or.kr/open_content/undershop/) 의 open API를 사용하였습니다.
</br>
</br>

## 개발 배경
코로나 19가 장기화되고 언택트 시대가 도래하며 공공시설 이용이 눈에 띄게 줄었고 이는 소상공인의 매출 급감과 폐업 점포의 급증으로 이어집니다. 따라서 지하상가 내 상품 접근성을 향상시키고, 지하상가 이용의 편의성을 제공하기 위해 지하도상가 상품 온라인 구매 앱 서비스(이하 `Untact Shop`)를 제안하게 되었습니다.

## 주요 기능
- 지하도상가 점포별 상품 확인
- 온라인 구매 서비스
- 실시간 채팅을 통한 Q&A
- 지하도상가 점포 위치 확인
- 상품 카테고리별 점포 분류

## API
[**서울시설공단**](https://www.sisul.or.kr/open_content/undershop/)
- 지하도상가 배치도

## 팀원 및 역할분담
|[@rlagksql219](https://github.com/rlagksql219)|[@lye2i](https://github.com/lye2i)|[@y78462](https://github.com/y78462)|[@HwangJaeHwan](https://github.com/HwangJaeHwan)|
|-----|-----|-----|-----|
|• 매장 검색 <br> • 채팅 기능 <br> • 데이터베이스 구축 |• 상품목록, 상점목록, 채팅목록 </br> • 데이터베이스 구축 |• 로그인, 마이페이지, 연관상품추천기능,  </br> • 웹서버 구축 |• 카테고리별 매장 분류 지도, 가게별 위치 맵핑기능  </br> • 웹서버 구축 및 데이터베이스 구축 
<br/>


# 🛍️프론트엔드 기능 구현 사항
>  ✨[demo video](https://youtu.be/QqWv3s2lpOw)<br/>

## 주요 기능
- **백엔드 연결**
    - 지하도상가 점포 데이터베이스 구축
    - `Firebase`를 이용해 웹서버 구축

- **메인 페이지**
    - 지도보기
    - 쇼핑하기
    - 로그인, 회원가입

- **지하도상가 지도**
    - 매장 검색
         - 위치정보
         - 매장정보
    - 카테고리별 매장 분류
         - 패션의류, 음식점, 쇼핑미용, 기타매장, 디지털가전, 공방, 편의시설 등으로 분류
    - 채팅을 통한 매장 Q&A
         - 지도 내에서 점포 클릭 시, 페이지 전환

- **온라인 쇼핑**
    - 상품목록
    - 상점목록
         - 상점 검색
    - 채팅목록
    - 마이페이지
         - 개인정보 수정
    - 채팅을 통한 상품 구매
         - 연관상품 추천


# 🛍️Screen 구성
![layout](https://user-images.githubusercontent.com/69866091/152942764-c2f29cde-7a72-4605-9eaa-5a6848459a3c.png)
![final](https://user-images.githubusercontent.com/69866091/152943277-617b2556-ec44-4d89-a432-08886c4bc3db.jpg)


# 🛍️결과 화면

### ✨로그인
![1 로그인](https://user-images.githubusercontent.com/69866091/152654409-4aedfbb5-07e2-4bf4-9143-7586896d0963.gif)

### ✨마이페이지
![2 마이페이지](https://user-images.githubusercontent.com/69866091/152655343-91dea02a-0a44-481d-af00-72fa8128de96.png)

### ✨매장 검색
![3 매장 검색](https://user-images.githubusercontent.com/69866091/152654425-91a9c402-1c5a-4c8a-bbf2-948e991a6084.gif)

### ✨매장 정보
![4 매장 정보](https://user-images.githubusercontent.com/69866091/152654430-b8d07f84-ad0d-4584-9051-afa3725e9270.gif)

### ✨매장지도
![5 매장지도](https://user-images.githubusercontent.com/69866091/152654433-36fb29a2-1a1e-46f3-8388-57433669ce40.gif)

### ✨카테고리별 매장 분류
![6 카테고리별 매장 분류](https://user-images.githubusercontent.com/69866091/152654435-97a29f32-a388-444a-80c6-1cda50003286.gif)

### ✨채팅을 통한 매장 Q&A
![7 채팅을 통한 매장 Q A](https://user-images.githubusercontent.com/69866091/152654442-7e937729-0030-45e7-9324-b2edf196306e.gif)

### ✨상품목록
![8 상품목록](https://user-images.githubusercontent.com/69866091/152654445-e244b2ba-ee5b-4f40-bf33-a4f10265e442.gif)

### ✨상점목록
![9 상점목록](https://user-images.githubusercontent.com/69866091/152654448-5a5ba54b-e87f-4f54-9835-804b2d5322c9.gif)

### ✨상점 검색
![10 상점 검색](https://user-images.githubusercontent.com/69866091/152654458-19482c65-379a-49de-a685-4262ae1f73c6.gif)

### ✨연관상품 추천
![11 연관상품 추천](https://user-images.githubusercontent.com/69866091/152654463-73ee27a5-af2d-45e8-8247-66e3f9a5f4a9.gif)

### ✨채팅을 통한 상품 구매
![12 채팅을 통한 상품 구매](https://user-images.githubusercontent.com/69866091/152654467-7000a4a5-9534-4dc9-83b6-7632701ec3cb.gif)

### ✨채팅목록
![13 채팅목록](https://user-images.githubusercontent.com/69866091/152654481-3ada9097-7951-4577-ad53-abca2b07d26f.gif)
