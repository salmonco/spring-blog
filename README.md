# Spring Framework 블로그
## Spring MVC Project
* Controller > Service > ServiceImpl > Mapper > Mapper.xml

## Spring Framework vs Spring Boot
* **Spring Legacy Project: Spring Framework를 이용하는 프로젝트**
* 장점: 기존 프로젝트를 이해하는 데 도움. 모든 버전의 스프링에서 사용
* 단점: 초기 설정 복잡. war 파일을 통한 비교적 복잡한 배포
* WAS에 배포하여 일반적인 사이트 구축 및 사이트관리 운영에 적합
***
* **Spring Starter Project: Spring Boot를 이용하는 프로젝트**
* 장점: 초기 설정 간단. 내장된 WAS. jar 파일을 통한 비교적 간편한 배포
* 단점: WAS에 배포 시 별도의 설정이 복잡
* RESTful API로서 Docker로 배포하여 AWS의 ECS를 이용해서 운영함이 적합

## 개발 환경
* Spring Framework 4.2.4.RELEASE
* STS 3.9.12.RELEASE
* Apache Tomcat 7.0.107
* MySQL 8.0.23
* MyBatis 3.4.1
* Java 1.8
* JSP 2.2
* Bootstrap 4

## 기능
* 로그인/회원가입
* 유저 프로필 사진 업로드
* 게시글 CRUD
* 게시글 작성 페이지 - 파일 첨부, CKEditor 4
* 게시글 페이징
* 게시글 좋아요
* 좋아요 수에 따른 랭킹
* 댓글 CRUD
* WebSocket을 통한 다중 채팅
* Apache POI을 통한 신고 내용 엑셀 파일에 쓰기
