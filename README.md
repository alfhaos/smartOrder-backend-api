# 플랫폼개발팀 백엔드 과제 테스트

## 실행 전
VM arguments에 -Djava.net.preferIPv4Stack=true 추가 해주셔야 됩니다.  

request 요청에 대한 공통 로그 포맷 출력시 IP가 정상적으로 출력됩니다.  


## 테스트 파일
1. request 값 유효성 검사  

주 패키지 경로 : kr.co.kcp.backendcoding.validation  

주 클래스 명 : BeanValidationTest  

설명 : 각 문제별 요청 값에 따른 유효성 검사 테스트 파일 입니다.  


2. 문제 2,3 orderService 테스트  

주 패키지 경로 : kr.co.kcp.backendcoding.work.service  

주 클래스 명 : OrderServiceImplTest  

설명 : 각 문제 요구사항 기능 구현 결과에 따른 값들을 테스트 할수 있는 테스트 파일 입니다.  


3. 문제 4 pointService 테스트  

주 패키지 경로 : kr.co.kcp.backendcoding.work.service  

주 클래스 명 : PointServiceImplTest  

설명 : 포인트 조회에 따른 반환 값들을 테스트 할수 있는 파일 입니다.  


## 문제 1

해당 프로젝트에 공통 기능을 개발합니다.

### 요구 사항
1. 예상치 못한 에러들에 대해 공통 기능으로 JSON 형태로 에러 처리를 해주세요.
   - 예) 500, 404 등의 에러 발생시 json 타입으로 에러 응답해주세요.

- > 주 패키지 경로 : kr.co.kcp.backendcoding.work.common.exception.handler.advice
- > 주 클래스 명 : ControllerAdvice
- > 설명 : @RestControllerAdvice을 이용하여 RestController에서 발생되는 에러를 핸들러 할수 있는 함수를 구현하였고
  클라이언트, 서버 오류 발생 분류에 따라 메세지를 생성하여 응답하도록 구현하였습니다.


2. request 요청에 대한 공통으로 로그 아래와 같은 포맷으로 남겨주세요.
    - 로그 포맷 : `Request: Remote IP: {}, Headers: {}, Method: {}, URI: {}, Parameter: {}` 
	- API 가 추가되도 작업이 최소화 되게 작업해주세요.
    - Headers 와 Parameter 정보는 json 형태로 로그를 남겨주세요.
- > 주 패키지 경로 : kr.co.kcp.backendcoding.work.common.interceptor
- > 주 클래스 명 : LogInterceptor
- > 설명 : HandlerInterceptor을 구현하여 request 요청에 따른 log가 기록되도록 구현하였으며 request의 content-type에 따라 분기를 나누어 구현하였습니다.
WebMvcConfigurer을 통해 InterceptorRegistry에 LogInterceptor을 추가하였습니다.  
또한 logback-spring.xml을 생성하여 프로젝트 root 경로의 log폴더에 각각 error_log, log가 남도록 하였습니다.  
  

## 문제 2

`주문 정보 조회` API 명세서(func2.md)를 바탕으로 REST API를 구현해 주세요.<br>
orders, order_menus, order_payments 3개 테이블을 조회해서 API 명세서에 맞게 개발하세요.<br>
테이블 정보는 schema.sql 파일을 참고해주세요.

### 요구사항

1. 파라미터 유효성 검증하고 적절한 에러 처리를 해주세요.
- >주 패키지 경로 : kr.co.kcp.backendcoding.work.domain.dto.request
- > 주 클래스 명 : OrderInfoRequestDto
- > 설명 : @Validated을 활용해 requestDto에 대한 유효성 검사를 실시 하였고 정규 표현식을 이용해 유효성 검사에 따른
메시지를 반환하도록 하였습니다.

2. 주문 정보 조회 결과가 없는 경우 적절한 결과코드, 메세지로 리턴해주세요.
- > 주 패키지 경로 : kr.co.kcp.backendcoding.work.repository
- > 주 클래스 명 : OrderRepository
- > 주 메소드 명 : orderInfo
- > 설명 : JPQL을 이용하여
주문 테이블 -> 주문 금액, 주문 날짜 조회<br>
주문메뉴 테이블 -> 주문 메뉴, 가격 조회<br>
주문결제 테이블 -> 결제번호, 결제수단, 결제날짜 조회<br>
List<Entity> 객체의 경우 응답을 위한 vo 객체로 변환후 공통 응답 DTO에 담아서 반환하였습니다.<br>
또한 try ~ catch 을 이용하여 조회된 결과가 없는 NoResultException 발생시 에러메시지 처리를 하였습니다. <br>

## 문제 3

`주문 예약 기능 API` 명세서(func3.md)를 바탕으로 REST API를 구현해 주세요.

### 요구사항

1. 파라미터 유효성 검증하고 적절한 에러 처리를 해주세요.
- >주 패키지 경로 : kr.co.kcp.backendcoding.work.domain.dto.request <br>
kr.co.kcp.backendcoding.work.common.config
- >주 클래스 명 : OrderReservationRequestDto, RereadableRequestWrapper
- >설명 :  @Validated을 활용해 requestDto에 대한 유효성 검사를 실시 하였고 정규 표현식을 이용해 유효성 검사에 따른
메시지를 반환하도록 하였습니다.<br>
또한 request의 content-type이 json이기에 LogInterceptor에서 요청 값을 읽은후 controller에서 데이터를 읽지 못하는 현상을 방지하기 위해<br>
HttpServletRequestWrapper을 이용하여 InputStream 으로 데이터를 읽은후 이미 읽었던 데이터로 다시 InputStream을 controller에 돌려줄수 있도록<br>
구현하였습니다 wrapper 적용시 filter에서 h2-console 요청에 따른 분기처리를 하여 h2 요청에 관한 에러도 해결하였습니다.<br>

2. 결제의 공통 기능으로 결제 예약 저장(insert) 기능과 저장 후 알림 발송 기능을 인터페이스로 생성합니다.
- >주 패키지 경로 : kr.co.kcp.backendcoding.work.service
- >주 클래스 명 : PaymentService
- >설명 :  PaymentService을 생성후 이를 구현하는 PaymentServiceImpl 클래스를 통해 결제 예약 저장, 저장 후 알림 발송 기능을 구현하였습니다.

3. 결제 타입별로 객체지향적인 설계를 적용하여 결제 타입이 추가시 최소한의 작업이 되도록 해주세요.
- >주 패키지 경로 : kr.co.kcp.backendcoding.work.domain.payment
- >주 클래스 명 : PaymentType
- >설명 : PaymentType 인터페이스와 이를 구현하는 CARD, CASH 클래스를 생성하여 객체지향적인 설계를 진행하였고 processPayment 메소드를 이용해<br>
각각의 결제타입별로 결제로직을 작성할수 있게하였으며 본 과제에서는 DB에 저장되는 PAYMENT_TYPE 문자열을 반환하도록 하였습니다.

4. order_reservations 테이블에 주문 예약 정보를 저장하고, reservation_id를 응답으로 반환합니다.
5. 결제 예약 정보를 테이블에 저장을 성공한 경우에만 예약완료 알림 발송 기능을 수행합니다.
6. 알림 발송 기능은 `{결제타입} : {reservation_id를} 예약완료` 문구를 프린트로 대체합니다.  

4,5,6 - >
- >주 패키지 경로 : kr.co.kcp.backendcoding.work.repository
- >주 클래스 명 : OrderRepository
- >주 메소드 명 : saveOrderReservation, sendNotification
- >설명 : OrderReservations Entity 객체를 생성후 em.persist(orderReservations) 을 통해 주문 예약 정보를 저장하였고<br>
try ~ catch문을 활용하여 주문 저장 에러 발생시 에러 메시지를 반환하며<br>
알림 메시지를 생성하기 위해 sendNotification 메소드에서 JPQL을 이용해 주문 예약 정보 테이블에서 예약 번호를 조회한다음 응답DTO을 생성합니다.<br>


## 문제 4

`포인트 조회` 명세서 양식(func4-1.md)으로 바탕으로 REST API를 구현해 주세요.<br>
`외부 포인트 데이터 조회 API` 명세서(func4-2.md)를 확인하여 호출 결과를 응답으로 돌려주세요.

### 요구사항

1. 클라이언트에서는 `CODE_A` 형태로 호출 받고 외부 API 호출 할때는 `A` 로 변경해서 호출해주세요.

- >주 패키지 경로 : kr.co.kcp.backendcoding.work.service
- >주 클래스 명 : PointServiceImpl
- >주 메소드 명 : pointSearch
- >설명 : split를 통해 클라이언트에서 받은 값을 변환하여 외부 API 호출을 진행 하였습니다.

2. 파라미터 유효성 검증하고 적절한 에러 처리를 해주세요.
- >주 패키지 경로 :  kr.co.kcp.backendcoding.work.domain.dto.request
- >주 클래스 명 : PointSearchRequestDto
- >설명 :  @Validated을 활용해 requestDto에 대한 유효성 검사를 실시 하였고 정규 표현식을 이용해 유효성 검사에 따른
- >메시지를 반환하도록 하였습니다.

3. 외부 포인트 조회 API 호출시 timeout 은 5초로 설정하고 timeout 에러 발생시 3번 재시도 해야됩니다.
4. 외부 API 호출 실패시 적절한 적절한 결과코드, 메세지로 리턴해주세요.  

3,4 - >
- >주 패키지 경로 : kr.co.kcp.backendcoding.work.common.config,
- >kr.co.kcp.backendcoding.work.service
- >주 클래스 명 : RestTemplateConfig, PointServiceImpl
- >설명 :  SimpleClientHttpRequestFactory을 통해 주입받은 RestTemplate의 타임아웃을 5초로 설정하였고<br>
반복문을 통해 ResourceAccessException 발생시 총 3번 재시도 하도록 구현하였고 에러 메시지 처리를 진행하였습니다.<br>
또한 application.yml에 외부 api 호출 관련 값들을 정의하였습니다.<br>

