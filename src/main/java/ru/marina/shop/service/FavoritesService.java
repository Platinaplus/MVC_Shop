package ru.marina.shop.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.marina.shop.entity.Cart;
import ru.marina.shop.entity.Favorites;
import ru.marina.shop.entity.Product;
import ru.marina.shop.entity.User;
import ru.marina.shop.repository.CartRepository;
import ru.marina.shop.repository.FavoritesRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FavoritesService {
    private final FavoritesRepository favoritesRepository;

    public List<Product> getFavorites(User user) {
        return favoritesRepository.getFavoritesByUserId(user);
    }

    public void addToFavorites(Favorites favorite) {
        favoritesRepository.save(favorite);
    }

    public void removeFromFavorites(User user, Product product) {
        favoritesRepository.removeFromFavorites(user, product);
    }

    public void getFavoriteById(Long id) { favoritesRepository.findById(id); }
}
