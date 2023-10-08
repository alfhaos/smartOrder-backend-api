package kr.co.kcp.backendcoding.work.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Table(name = "order_payments")
@Getter
@Entity
@NoArgsConstructor
public class OrderPayments {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "payment_id", nullable = false)
    private Long paymentId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ORDER_ID")
    private Orders orders;

    @Column(name = "PAYMENT_METHOD")
    private String paymentMethod;

    @Column(name = "PAYMENT_DATE")
    private Date paymentDate;

    public void addOrders(Orders orders){
        this.orders = orders;
        orders.getOrderPayments().add(this);
    }

    public OrderPayments(Long paymentId, String paymentMethod, Date paymentDate) {
        this.paymentId = paymentId;
        this.paymentMethod = paymentMethod;
        this.paymentDate = paymentDate;
    }
}
