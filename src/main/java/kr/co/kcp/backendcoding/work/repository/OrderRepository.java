package kr.co.kcp.backendcoding.work.repository;

import jakarta.annotation.Nullable;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import kr.co.kcp.backendcoding.work.domain.dto.request.OrderInfoRequestDto;
import kr.co.kcp.backendcoding.work.domain.dto.request.OrderReservationRequestDto;
import kr.co.kcp.backendcoding.work.domain.dto.response.CommonResponseDto;
import kr.co.kcp.backendcoding.work.domain.dto.response.OrderInfoResponseDto;
import kr.co.kcp.backendcoding.work.domain.dto.response.OrderReservationResponseDto;
import kr.co.kcp.backendcoding.work.domain.entity.OrderMenus;
import kr.co.kcp.backendcoding.work.domain.entity.OrderPayments;
import kr.co.kcp.backendcoding.work.domain.entity.OrderReservations;
import kr.co.kcp.backendcoding.work.domain.entity.Orders;
import kr.co.kcp.backendcoding.work.domain.vo.OrderMenusVo;
import kr.co.kcp.backendcoding.work.domain.vo.OrderPaymentsVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class OrderRepository {

    private final EntityManager em;

    public OrderInfoResponseDto orderInfo(OrderInfoRequestDto orderInfoRequest) {


        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

        String orderId = orderInfoRequest.getOrderId();
        Orders orders;
        /*
        * 주문 금액, 주믄 날짜 조회
        * -> 조회된 주문 정보가 없으면 null을 반환(try ~ catch)
        * -> Orders에 생성자를 추가하여 주문 금액 과 날짜만을 통해 Orders 객체를 반환하도록 하였습니다.
        */
        try{
            orders = em.createQuery("select new kr.co.kcp.backendcoding.work.domain.entity.Orders(o.orderAmount, o.orderDate)  " +
                            "from Orders o where o.orderId = :orderId", Orders.class)
                    .setParameter("orderId", orderId)
                    .getSingleResult();
        } catch (NoResultException e){
            return null;
        }

        /*
        * 주문 메뉴 조회
        * -> OrderMenus 생성자를 추가하여 상품 이름과 상품 가격만을 통해 OrderMenus 객체를 반환하도록 하였습니다.
        * */
        List<OrderMenus> orderMenusList = em.createQuery("select new kr.co.kcp.backendcoding.work.domain.entity.OrderMenus(om.productName, om.price)" +
                "from OrderMenus om " +
                "where om.orders.orderId = :orderId", OrderMenus.class)
                .setParameter("orderId", orderId)
                .getResultList();

        List<OrderMenusVo> orderMenusVoList = new ArrayList<>();

        // 주문 메뉴 엔티티 객체 -> 주문 메뉴 vo 변환
        for (OrderMenus orderMenus : orderMenusList) {
            OrderMenusVo orderMenusVo = OrderMenusVo.builder()
                    .productName(orderMenus.getProductName())
                    .price(orderMenus.getPrice()).build();

            orderMenusVoList.add(orderMenusVo);
        }

        /*
         * 주문 결제 정보 조회
         * -> OrderPayments 생성자를 추가하여 결제번호, 결제수단, 결제날짜을 통해 OrderPayments 객체를 반환하도록 하였습니다.
         * */
        List<OrderPayments> orderPaymentsList = em.createQuery("select new kr.co.kcp.backendcoding.work.domain.entity.OrderPayments(op.paymentId, op.paymentMethod, op.paymentDate)" +
                "from OrderPayments op " +
                "where op.orders.orderId = :orderId", OrderPayments.class)
                .setParameter("orderId", orderId)
                .getResultList();

        List<OrderPaymentsVo> orderPaymentsVoList = new ArrayList<>();

        // 주문 결제 정보 엔티티 객체 -> 주문 결제 정보 vo 변환
        for (OrderPayments orderPayments : orderPaymentsList) {
            OrderPaymentsVo orderMenusVo = OrderPaymentsVo.builder()
                            .paymentId(orderPayments.getPaymentId())
                            .paymentMethod(orderPayments.getPaymentMethod())
                            .paymentDate(simpleDateFormat.format(orderPayments.getPaymentDate()))
                            .build();

            orderPaymentsVoList.add(orderMenusVo);
        }

        // Builder 패턴을 이용하여 DTO 객체 RETURN
        return OrderInfoResponseDto.builder()
                .orderAmount(orders.getOrderAmount())
                .orderDate(simpleDateFormat.format(orders.getOrderDate()))
                .orderMenus(orderMenusVoList)
                .orderPayments(orderPaymentsVoList)
                .build();
    }
    /*
     * 주문 예약 정보 저장
     */
    public void saveOrderReservation(OrderReservationRequestDto orderReservationRequestDto) {

        OrderReservations orderReservations = OrderReservations.builder()
                .reservationId(orderReservationRequestDto.getReservationId())
                .paymentType(orderReservationRequestDto.getPaymentType())
                .storeCode(orderReservationRequestDto.getShopCode())
                .orderAmount(orderReservationRequestDto.getOrderAmount())
                .discountAmount(orderReservationRequestDto.getDiscountAmount())
                .paymentAmount(orderReservationRequestDto.getPaymentAmount())
                .build();

        em.persist(orderReservations);
    }

    /*
    * 저장된 주문 예약 정보를 조회후 알림 메세지 응답
    * */
    public CommonResponseDto sendNotification(String reservationId) {

        CommonResponseDto commonResponseDto = new CommonResponseDto();

        try{
            OrderReservations orderReservations = em.createQuery("select ov " +
                            "from OrderReservations ov " +
                            "where ov.reservationId = :reservationId", OrderReservations.class)
                    .setParameter("reservationId", reservationId)
                    .getSingleResult();

            // 공통응답 데이터 생성
            commonResponseDto.setCode(0);
            commonResponseDto.setMessage("조회된 주문 예약 정보 아이디 입니다.");

            // 응답 DTO 생성
            OrderReservationResponseDto orderReservationResponseDto = new OrderReservationResponseDto(reservationId);

            commonResponseDto.setData(orderReservationResponseDto);

            return commonResponseDto;
        } catch (NoResultException exception){
            
            commonResponseDto.setCode(1);
            commonResponseDto.setMessage("조회된 주문 예약 정보가 없습니다.");

            return commonResponseDto;
        }

    }
}