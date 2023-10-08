package kr.co.kcp.backendcoding.work.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "ORDER_MENUS")
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class OrderMenus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ORDER_ID")
    private Orders orders;

    @Column(name = "PRODUCT_NAME")
    private String productName;

    @Column(name = "PRICE")
    private int price;

    public OrderMenus(String productName, int price) {
        this.productName = productName;
        this.price = price;
    }

    public void addOrders(Orders orders){
        this.orders = orders;
        orders.getOrderMenus().add(this);
    }
}
