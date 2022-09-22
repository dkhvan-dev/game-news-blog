package springbootcamp.mainfinalproject.service;

import springbootcamp.mainfinalproject.model.Favorites;

import java.util.List;

public interface FavoritesService {
    List<Favorites> getAllFavoritesByUser(Long userId);
    Favorites addToFavorite(Long userId, Long blogId);
    void deleteFromFavorite(Long userId, Long blogId);
}
