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

    public Double getSum(OrderCreationDto order) {
        final double[] sum = {0.0};

        order.getCart().forEach(cart -> sum[0] += (cart.getProduct().getPrice()*cart.getQuantity()));

        return sum[0];
    }
}
