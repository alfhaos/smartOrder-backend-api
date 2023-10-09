package kr.co.kcp.backendcoding.work.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "ORDER_RESERVATIONS")
@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderReservations {

    @Id
    @Column(name = "RESERVATION_ID")
    private String reservationId;

    @Column(name = "PAYMENT_TYPE")
    private String paymentType;

    @Column(name = "STORE_CODE")
    private String storeCode;

    @Column(name = "ORDER_AMOUNT")
    private int orderAmount;

    @Column(name = "DISCOUNT_AMOUNT")
    private int discountAmount;

    @Column(name = "PAYMENT_AMOUNT")
    private int paymentAmount;

}
