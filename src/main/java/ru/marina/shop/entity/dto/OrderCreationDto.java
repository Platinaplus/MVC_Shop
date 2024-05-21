package ru.marina.shop.entity.dto;

import lombok.*;
import ru.marina.shop.entity.Cart;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class OrderCreationDto {
    private List<Cart> cart;

    public OrderCreationDto() {
        this.cart = new ArrayList<>();
    }

    public void addCart(Cart cartItem) {
        this.cart.add(cartItem);
    }
}
