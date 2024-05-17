package ru.marina.shop.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.marina.shop.entity.Favorites;
import ru.marina.shop.entity.Product;
import ru.marina.shop.entity.User;

import java.util.List;

public interface FavoritesRepository extends JpaRepository<Favorites, Long> {

    @Query("SELECT f.product FROM Favorites f WHERE f.user = :user")
    List<Product> getFavoritesByUserId(@Param("user") User user);

    @Transactional
    @Modifying
    @Query("DELETE FROM Favorites f WHERE f.user = :user AND f.product = :product")
    void removeFromFavorites (@Param("user") User user, @Param("product") Product product);
}
