package ru.marina.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.marina.shop.entity.Cart;
import ru.marina.shop.entity.Product;
import ru.marina.shop.entity.User;

import java.util.List;

public interface CartRepository extends JpaRepository<Cart, Long> {

    @Query("SELECT c.product FROM Cart c WHERE c.user = :user")
    List<Product> getCartByUserId(@Param("user") User user);
}
