package springbootcamp.mainfinalproject.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import springbootcamp.mainfinalproject.mapper.BlogMapper;
import springbootcamp.mainfinalproject.model.Blog;
import springbootcamp.mainfinalproject.model.Favorites;
import springbootcamp.mainfinalproject.model.User;
import springbootcamp.mainfinalproject.model.dto.BlogDto;
import springbootcamp.mainfinalproject.repository.FavoritesRepository;
import springbootcamp.mainfinalproject.service.BlogService;
import springbootcamp.mainfinalproject.service.FavoritesService;
import springbootcamp.mainfinalproject.service.UserService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FavoritesServiceImpl implements FavoritesService {
    private final FavoritesRepository favoritesRepository;
    private final UserService userService;
    private final BlogService blogService;
    private final BlogMapper blogMapper;

    @Override
    public List<Favorites> getAllFavoritesByUser(Long userId) {
        if (userService.getCurrentUser().getUserId().equals(userId)) {
            return favoritesRepository.findAllByUserUserIdOrderByBlogsBlogCreateDateDesc(userId);
        }
        return null;
    }

    @Override
    public Favorites addToFavorite(Long userId, Long blogId) {
        if (userService.getCurrentUser().getUserId().equals(userId)) {
            BlogDto blogDto = blogService.getBlogById(blogId);
            if (blogDto != null) {
                Favorites favorite = new Favorites();
                favorite.setUser(userService.getCurrentUser());
                favorite.setBlogs(blogMapper.toEntity(blogDto));
                return favoritesRepository.save(favorite);
            }
        }
        return null;
    }

    @Override
    public void deleteFromFavorite(Long userId, Long blogId) {
        Favorites favorite = favoritesRepository.findByUserUserIdAndBlogsBlogId(userId, blogId);
        if (favorite != null) {
            favoritesRepository.delete(favorite);
        }
    }
}
