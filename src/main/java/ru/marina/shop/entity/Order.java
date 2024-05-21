package ru.marina.shop.entity;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

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
    @JoinColumn(name="user_id")
    private User userId;

    @OneToMany
    @JoinColumn
    private List<Cart> cart = new ArrayList<>();

    @Column(name="status")
    private Status status;
}
