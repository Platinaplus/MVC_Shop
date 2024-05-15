package ru.marina.shop.entity.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Setter
@Getter
public class ProductDto {
    private Long itemId;
    private String name;
    private double price;
    private Integer quantity;
}
