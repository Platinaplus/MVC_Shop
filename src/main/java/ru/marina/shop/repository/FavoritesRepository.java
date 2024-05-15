package ru.marina.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.marina.shop.entity.Favorites;
import ru.marina.shop.entity.Product;
import ru.marina.shop.entity.User;

import java.util.List;

public interface FavoritesRepository extends JpaRepository<Favorites, Long> {

    @Query("SELECT c.product FROM Favorites c WHERE c.user = :user")
    List<Product> getFavoritesByUserId(@Param("user") User user);
}
