package ru.marina.shop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import ru.marina.shop.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
    @Query(value="select to_char(purchase_date, 'Month') as p_date, count(*) as count from orders group by p_date", nativeQuery = true)
    List<Object[]> results();
}
