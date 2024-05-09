package ru.marina.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.marina.shop.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
