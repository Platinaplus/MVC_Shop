package ru.marina.shop.entity;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@Entity
@Getter
@Setter
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long id;

    @Column(name = "order_number", nullable = false, unique = true)
    private String orderNumber;

    @Column(name = "purchase_date", nullable = false)
    private Timestamp purchaseDate;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user_id;

    @OneToMany(mappedBy = "order")
    private List<Cart> carts = new ArrayList<>();

    @Column(name="status")
    private Status status;
}
