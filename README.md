# "장소 검색 서비스"를 위한 서버 프로그램

## 1. 요구 사항

- 장소 검색

  - 장소 API 를 통해 장소를 검색합니다.
  - 장소 검색 서비스 - 카카오 검색 API, 네이버 검색 API - 를 통해 각각 최대 5개씩, 총 10개의 키워드 관련 장소를 검색합니다.

- 검색 키워드 목록

  - 사용자들이 많이 검색한 순서대로, 최대 10개의 검색 키워드 목록을 제공합니다.

  - 키워드 별로 검색된 횟수도 함께 표기해 주세요.

  - 비즈니스 로직은 모두 서버에서 구현합니다.



## 2. 개발 환경

- Java 17
- Spring Boot 2.6.8
- Gradle multi module project (gradle-7.4)
- H2 Database Engine
- proj4j 1.3.0
  - 좌표 변환을 위한 라이브러리
  - 동일 업체 판단을 위해 사용
  - 네이버 Api의 KATECH 좌표계를 longitude 좌표계로 변경하여 카카오 Api의 좌표계와 통일 하기 위함

## 3. 테스트

- location-api-server 모듈의 HTTP Request file 이용
  - 경로 : src/test/http



## 4. Api 문서

- Terminal에서 ./gradlew clean build 명령어 실행
- 어플리케이션 실행 후 http://localhost:8080/docs/index.html 접속
- 어플리케이션 실행이 어려운 상황이면 Location-api-server 모듈의 index.html 확인
  - 경로 : src/main/resourse/static/docs/index.html



## 5. 부연 설명

- 프로그램의 지속적 유지 보수 및 확장에 용이한 multi module project 선택
  - Application과 공통 module 그리고 외부 연동 (Api, DB Repository)로 module로 나눔
  - 외부 연동 module의 의존 관계에 Application module의 의존성을 제거하여 단방향 의존 관계로 구성, module을 교체 하게 된다 하더라도 Application에 영향도가 낮음
  - Application module과 외부 연동 module과의 의존성을 더욱 낮추기 위해 DIP를 적용
- 키워드 별로 검색된 횟수의 동시성 이슈를 방지
  - JPA의 @Version 을 이용해 Optimistic Lock 설정
  - OptimisticLockException 발생을 대비해 Aop를 이용하여 일정 횟수 만큼의 재처리 구현
- 검색 API 제공자의 “다양한” 장애 및 연동 오류 발생을 대비
  - 일시 적인 연동 오류를 대비하여 Aop와 Circut Breaker 적용
  - 일부 검색 제공자의 실패를 대비해서 Exception을 체크해서 서비스 제공
- 대용량 트래픽 처리를 위한 반응성(Low Latency), 확장성(Scalability), 가용성(Availability)을 높이기 위한 고려
  - 검색어 횟수의 안정적인 처리를 위해 외부 Api 연동과 검색어 저장 기능의 트렌젝션 분리
  - 확장성을 위해 검색을 했을때 검색어 증가 처리가 아닌 검색 이벤트를 발생키는 ApplicationEventPublisher로 처리하여 검색과 저장의 의존성 제거
- 테스트 코드를 통한 프로그램 검증 및 테스트 용이성(Testability)을 높이기 위한 코드 설계
  - 테스트를 위해 의존성을 들어내는 설계
  - 모든 코드에 대한 테스트가 아닌 작성한 코드와 동작에 대한 테스트 코드 작성
  - Mokito, DIP 등을 이용하여 테스트 하기 힘든 외부 시스템을 의존성 없이 테스트를 수행
- 구글 장소 검색 등 새로운 검색 API 제공자의 추가 시 변경 영역 최소화에 대한 고려
  - 검색 API에 대한 모듈화
  - API 호출과 데이터 가공 서비스 분리
  - 하나의 인터페이스와 동일된 응답값으로 공통값을 정하고 다양한 API 호출의 가공을 구현하여 다형성 제공
- 그 외
  - 테스트 코드 기반의 Spring REST docs를 적용하여 문서 자동화
  - enum을 이용한 상수값과 그에 연관된 값 관리
  - ExceptionHandler를 통한 exception 관리