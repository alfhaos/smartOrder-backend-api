package kr.co.kcp.backendcoding.work.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "ORDERS")
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ORDER_ID")
    private String orderId;

    @Column(name = "PRODUCT_NAME")
    private String productName;
    @Column(name = "ORDER_AMOUNT")
    private int orderAmount;
    @Column(name = "ORDER_DATE")
    private Date orderDate;

    @OneToMany(mappedBy = "orders", fetch = FetchType.LAZY)
    private List<OrderMenus> orderMenus = new ArrayList<>();

    @OneToMany(mappedBy = "orders", fetch = FetchType.LAZY)
    private List<OrderPayments> orderPayments = new ArrayList<>();

    //OrderInfo에 대한 생성자
    public Orders(int orderAmount, Date orderDate) {
        this.orderAmount = orderAmount;
        this.orderDate = orderDate;
    }
}
