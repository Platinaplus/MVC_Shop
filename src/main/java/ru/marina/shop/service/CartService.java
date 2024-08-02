package ru.marina.shop.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.marina.shop.entity.Cart;
import ru.marina.shop.entity.Product;
import ru.marina.shop.entity.User;
import ru.marina.shop.repository.CartRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CartService {
    private final CartRepository cartRepository;

    public List<Product> getCart(User user) {
        return cartRepository.getCartByUserId(user);
    }

    public List<Cart> getCarts(User user) {return cartRepository.getCartByUser(user);}
    
    public List<Cart> getCartsByOrder(String orderNumber) {return cartRepository.getCartByOrder(orderNumber);}

    public void editCart(User user, Product product, int quantity) {
        Cart cart = cartRepository.getCartByProduct(user, product);
        cart.setQuantity(quantity);
        cartRepository.save(cart);
    }

    public void addToCard(Cart cart) {
        cartRepository.save(cart);
    }

    public void deleteFromCart(Long id) {
        cartRepository.deleteById(id);
    }
}
