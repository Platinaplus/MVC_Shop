package ru.marina.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.marina.shop.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
