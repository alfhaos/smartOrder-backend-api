# 플랫폼개발팀 백엔드 과제 테스트

## 과제설명(필독)
1. 총 제출 문제는 3개 입니다.
   - 필수 : 문제 1, 문제 2, 문제 3
   - 선택 : 문제 4
2. `work` 패키지안에 REST API 서비스에 적절한 패키지, 클래스를 생성해서 개발해주세요.<br>
3. 프로젝트를 실행 시키시면 과제에 필요한 테이블, 데이터가 자동으로 생성됩니다. `schema.sql`, `data.sql` 을 참조해 주세요.<br>
4. `sample` 피키지는 `users` 샘플 테이블의 데이터를 조회하고 있습니다. 참고하시면 됩니다.<br>
5. 미완성이라도 기한내에 제출해주세요.
6. maven 라이브러리 추가 가능합니다.

## 실행 방법

프로젝트 폴더로 이동 후 아래 명령어를 입력 합니다.

**mac** : `./mvnw spring-boot:run`

**windows** : `mvnw.cmd spring-boot:run`

실행 후 `localhost:8989/h2-console` 접속하여 아래 정보로 디비 접속해 데이터 확인 가능합니다.
- Driver Class: `org.h2.Driver`
- JDBC URL: `jdbc:h2:mem:testdb`
- User Name: `sa`

## 기대사항

1. Spring annotation, lombok 을 적극적으로 사용해 주세요.
2. 테스트 코드를 작성하면 가산점이 있습니다.
3. 적절한 주석을 추가해 주세요.
4. 가능하면 JPA로 개발해주시고 Mybatis로도 개발하셔도 됩니다.

## 문제 1

해당 프로젝트에 공통 기능을 개발합니다.

### 요구 사항
1. 예상치 못한 에러들에 대해 공통 기능으로 JSON 형태로 에러 처리를 해주세요.
   - 예) 500, 404 등의 에러 발생시 json 타입으로 에러 응답해주세요.
2. request 요청에 대한 공통으로 로그 아래와 같은 포맷으로 남겨주세요.
    - 로그 포맷 : `Request: Remote IP: {}, Headers: {}, Method: {}, URI: {}, Parameter: {}` 
	- API 가 추가되도 작업이 최소화 되게 작업해주세요.
    - Headers 와 Parameter 정보는 json 형태로 로그를 남겨주세요.

## 문제 2

`주문 정보 조회` API 명세서(func2.md)를 바탕으로 REST API를 구현해 주세요.<br>
orders, order_menus, order_payments 3개 테이블을 조회해서 API 명세서에 맞게 개발하세요.<br>
테이블 정보는 schema.sql 파일을 참고해주세요.

### 요구사항

1. 파라미터 유효성 검증하고 적절한 에러 처리를 해주세요.
2. 주문 정보 조회 결과가 없는 경우 적절한 결과코드, 메세지로 리턴해주세요.

## 문제 3

`주문 예약 기능 API` 명세서(func3.md)를 바탕으로 REST API를 구현해 주세요.

### 요구사항

1. 파라미터 유효성 검증하고 적절한 에러 처리를 해주세요.
2. 결제의 공통 기능으로 결제 예약 저장(insert) 기능과 저장 후 알림 발송 기능을 인터페이스로 생성합니다.
3. 결제 타입별로 객체지향적인 설계를 적용하여 결제 타입이 추가시 최소한의 작업이 되도록 해주세요.
4. order_reservations 테이블에 주문 예약 정보를 저장하고, reservation_id를 응답으로 반환합니다.
5. 결제 예약 정보를 테이블에 저장을 성공한 경우에만 예약완료 알림 발송 기능을 수행합니다.
6. 알림 발송 기능은 `{결제타입} : {reservation_id를} 예약완료` 문구를 프린트로 대체합니다.

## 문제 4

`포인트 조회` 명세서 양식(func4-1.md)으로 바탕으로 REST API를 구현해 주세요.<br>
`외부 포인트 데이터 조회 API` 명세서(func4-2.md)를 확인하여 호출 결과를 응답으로 돌려주세요.

### 요구사항

1. 클라이언트에서는 `CODE_A` 형태로 호출 받고 외부 API 호출 할때는 `A` 로 변경해서 호출해주세요.
2. 파라미터 유효성 검증하고 적절한 에러 처리를 해주세요.
3. 외부 포인트 조회 API 호출시 timeout 은 5초로 설정하고 timeout 에러 발생시 3번 재시도 해야됩니다.
4. 외부 API 호출 실패시 적절한 적절한 결과코드, 메세지로 리턴해주세요.