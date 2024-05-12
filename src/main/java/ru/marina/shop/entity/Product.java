package ru.marina.shop.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Data
@Entity
@Getter
@Setter
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_id")
    private Long itemId;

    @NotEmpty(message = "The name is required")
    @Column(name = "name", nullable = false)
    private String name;

    @Min(0)
    @Column(name = "price", nullable = false)
    private double price;

    @NotEmpty(message = "The category is required")
    @Column(name = "category", nullable = false)
    private String category;

    @Column(name = "image")
    private String image;

    @Column(name = "length")
    private String length;

    @Column(name = "width")
    private String width;

    @Column(name = "material")
    private String material;
}
