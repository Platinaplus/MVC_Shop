package ru.marina.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.marina.shop.entity.Product;
import ru.marina.shop.entity.User;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
