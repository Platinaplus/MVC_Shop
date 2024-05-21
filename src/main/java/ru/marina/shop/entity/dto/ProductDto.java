package ru.marina.shop.entity.dto;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import ru.marina.shop.entity.Order;
import ru.marina.shop.entity.Product;

@Setter
@Getter
@Data
public class ProductDto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long itemId;
    @OneToOne
    private Product product;
    private Integer quantity;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    private Order order;
}
