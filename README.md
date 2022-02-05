# 🛍️프로젝트 개요
![logo](https://user-images.githubusercontent.com/69866091/152367621-5d238de1-99f2-47b3-90c7-0dd8547c05d8.png)

> 카테고리별로 지하도상가 지도 정보를 제공하고 점포 내 상품 온라인으로 구매할 수 있도록 하는 어플리케이션입니다. </br>

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
|[@rlagksql219](https://github.com/rlagksql219)|[lye2i](https://github.com/lye2i)|[@y78462](https://github.com/y78462)|
|-----|-----|-----|
|• 메인 페이지 <br> • 상담 신청 게시판 |• 사용자 로그인 </br> • 변호사 검색 </br> • 사이버법률상담 |• 변호사 로그인 </br> • 상담 예약 신청 페이지 </br> • 사이버법률상담
<br/>


# 🛍️프론트엔드 기능 구현 사항
페이지 간 데이터 전송 시, `HTML5` form 태그 GET 방식을 이용하여 프론트엔드를 개발하였습니다.

>  ✨[demo video](https://youtu.be/QqWv3s2lpOw)<br/>

## 주요 기능
- **회원가입**

- **사용자 로그인 & 변호사 로그인**
    - 사용자 로그인 메인 페이지와 변호사 로그인 메인 페이지 별도 구성
         - 변호사 로그인 메인 페이지에 '상담 신청 게시판' 메뉴 추가

- **변호사 검색**
    - `Javascript`를 이용하여 select box에 동적으로 옵션 추가
    - `Javascript`를 이용하여 테이블 동적 생성

- **상담 예약 신청**
    - `HTML5` 상담 신청 시, 실명 사용을 위해 input 태그 readonly 속성 이용
    -  `Web Storage`의 localStorage를 이용하여 데이터 저장

- **상담 신청 게시판**
   - `Javascript`를 이용하여 테이블 동적 생성
   - 동적으로 생성한 table에 row 단위로 클릭 리스너가 동작하도록 `jquery` 이용
   - `Web Storage`의 localStorage를 이용하여 데이터 저장

- **사이버법률상담**
   - `Web Storage`의 localStorage를 이용하여 데이터 저장

- **이용 안내**
   - 시민법률사무소 지도

- **마을변호사 소식**
   - `HTML5` iframe 태그로 마을변호사 블로그 페이지 불러옴

- **자주 묻는 질문 Q&A**
   - 애니메이션으로 구현


# 🛍️결과 화면

### ✨로그인
![1 로그인](https://user-images.githubusercontent.com/69866091/152654409-4aedfbb5-07e2-4bf4-9143-7586896d0963.gif)

### ✨마이페이지
![2 마이페이지](https://user-images.githubusercontent.com/69866091/152654414-0cce6bfe-19a1-4eab-a84c-7f9b6a1673c0.gif)

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
