package ru.marina.shop.repository;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.marina.shop.entity.Product;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> getProductsByCategory (String category);

    default List<Product> getAllProducts(Sort sort) {
        return findAll(sort);
    };
}
