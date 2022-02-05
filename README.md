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

### ✨메인 페이지
![1 메인 페이지](https://user-images.githubusercontent.com/69866091/152214705-14322e6a-5cdb-44e1-8f3c-c8d13d4aa732.gif)

### ✨회원가입
![2 회원가입](https://user-images.githubusercontent.com/69866091/152214715-b78c6a03-9715-4c2e-8617-3e05bf857f95.gif)

### ✨사용자 로그인
![3 사용자 로그인](https://user-images.githubusercontent.com/69866091/152214728-cf68fda2-ddfa-45a8-8a7d-8dc5a050a09e.gif)

### ✨변호사 로그인
![4 변호사 로그인](https://user-images.githubusercontent.com/69866091/152216658-ea350084-bae4-4df1-b165-88c22a897fef.gif)

### ✨변호사 검색
![5 변호사 검색](https://user-images.githubusercontent.com/69866091/152214788-69dfe74a-e175-414b-99ae-75e1859b62ac.gif)

### ✨상담 예약 신청
![6 상담 예약 신청](https://user-images.githubusercontent.com/69866091/152217000-81007e75-9d3c-4ed2-a3ec-9b32ecc3c9cc.gif)

### ✨상담 신청 게시판
![7 상담신청 게시판](https://user-images.githubusercontent.com/69866091/152214836-12ef98ca-91e7-49e6-9bef-a04b5393b090.gif)

### ✨사이버법률상담 신청
![8 사이버법률상담 신청](https://user-images.githubusercontent.com/69866091/152216723-8e4eec60-5f80-4688-9b40-b74435e55edf.gif)

### ✨사이버법률상담 답변
![9 사이버법률상담 답변](https://user-images.githubusercontent.com/69866091/152214893-ae832fbe-9ed5-4297-b2b0-93dd47b38b35.gif)

### ✨사이버법률상담 목록
![10 사이버법률상담 목록](https://user-images.githubusercontent.com/69866091/152214914-41fc02b1-5fc7-45e6-8a2f-54589a1e6413.gif)

### ✨이용 안내
![11 이용 안내](https://user-images.githubusercontent.com/69866091/152214934-b2218745-1a2a-4785-8c29-37c9fac8f3da.gif)

### ✨시민법률사무소 지도
![12 시민법률사무소 지도](https://user-images.githubusercontent.com/69866091/152214968-4ce09097-0737-4add-881a-4b0b18610eec.gif)

### ✨마을변호사 소식
![13 마을변호사 소식](https://user-images.githubusercontent.com/69866091/152214978-2de3c474-7622-476e-bdcf-9fd85a28f3bf.gif)

### ✨자주 묻는 질문 Q&A
![14 자주 묻는 질문 Q A](https://user-images.githubusercontent.com/69866091/152215050-5affa814-2389-446f-9267-929c5d898c1c.gif)
