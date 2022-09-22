package springbootcamp.mainfinalproject.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import springbootcamp.mainfinalproject.mapper.BlogMapper;
import springbootcamp.mainfinalproject.model.*;
import springbootcamp.mainfinalproject.model.dto.BlogDto;
import springbootcamp.mainfinalproject.model.dto.NewsDto;
import springbootcamp.mainfinalproject.service.*;

import java.text.ParseException;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class MainController {
    private final UserService userService;
    private final RoleService roleService;
    private final GameService gameService;
    private final BlogService blogService;
    private final NewsService newsService;
    private final GamePlatformService gamePlatformService;
    private final GenreService genreService;
    private final FeedbackService feedbackService;
    private final FeedbackStatusService feedbackStatusService;
    private final CommentsService commentsService;
    private final FavoritesService favoritesService;
    private final BlogMapper blogMapper;
    private final FileUploadService fileUploadService;

    @GetMapping("/")
    public String homePage(Model model) {
        List<NewsDto> last3News = newsService.getLatest3News();
        model.addAttribute("last3News", last3News);
        return "index";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/footer")
    public String footerPage(Model model) {
        List<FeedbackStatus> allFeedbackStatus = feedbackStatusService.getAllFeedbackStatus();
        model.addAttribute("allFeedbackStatus", allFeedbackStatus);
        return "toInsert/footer";
    }

    @GetMapping("/signIn")
    public String signInPage() {
        return "auth/auth";
    }

    @PostMapping("/signUp")
    public String signUp(User user,
                         @RequestParam(name = "rePassword") String rePassword,
                         @RequestParam(name = "birthdate") String userBirthdate) throws ParseException {
        if (user.getUserPassword().equals(rePassword)) {
            User newUser = userService.signUp(user, userBirthdate);
            if (newUser != null) {
                return "redirect:/profile";
            } else {
                return "redirect:/signUp?userExist";
            }
        }
        return "redirect:/signUp?passwordsNotSame";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/profile")
    public String profilePage(Model model) {
        Role banRole = roleService.getRoleByName("ROLE_BAN");
        model.addAttribute("banRole", banRole);
        model.addAttribute("currentUser", userService.getCurrentUser());
        return "profile";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/swapUserAvatar")
    public String swapUserAvatar(@RequestParam(name = "userImageToken") MultipartFile userImageToken) {

        String param = userService.swapAvatar(userImageToken, userService.getCurrentUser());
        return "redirect:/profile?" + param;
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/editUser")
    public String editUser(User user,
                           @RequestParam(name = "birthDate") String birthdate,
                           @RequestParam(name = "oldPassword") String oldPassword,
                           @RequestParam(name = "userPassword") String newPassword,
                           @RequestParam(name = "reNewPassword") String reNewPassword) {
        String param = userService.editUser(user, birthdate, oldPassword, newPassword, reNewPassword);
        if (param.equals("success")) {
            userService.updateUserData(user.getUserFirstName(), user.getUserSurname(), user.getUserPassword());
        }
        return "redirect:/profile?" + param;
    }

    @GetMapping("/allGames/keyword")
    public String allGamesByKeyword() {
        return "allGames";
    }

    @GetMapping("/detailsGame/{gameId}")
    public String detailsGamePage(@PathVariable(name = "gameId") Long gameId,
                                  Model model) {
        Game game = gameService.getGameById(gameId);
        if (gameId != null) {
            model.addAttribute("game", game);
            return "detailsGame";
        }
        return "redirect:/allGames";
    }

    @GetMapping("/gamesByPlatform/{platformId}/keyword")
    public String gamesByPlatformPage(@PathVariable(name = "platformId") Long platformId,
                                      Model model) {
        List<Game> allGamesByPlatform = gameService.getGamesByPlatform(platformId);
        if (allGamesByPlatform != null) {
            return "gamesByPlatform";
        }
        return "redirect:/allGames";
    }

    @GetMapping("/gamesByGenre/{genreId}/keyword")
    public String gamesByGenrePage(@PathVariable(name = "genreId") Long genreId,
                                   @RequestParam(name = "keyword") String keyword,
                                   Model model) {
        List<Game> allGamesByGenre = gameService.getAllGamesByGenreByKeyword(genreId, keyword);
        if (allGamesByGenre != null) {
            return "gamesByGenre";
        }
        return "redirect:/allGames";
    }

    @PostMapping("/sendFeedback")
    public String feedback(Feedback feedback) {
        feedbackService.addFeedback(feedback);
        return "redirect:/";
    }

    @GetMapping("/allNews")
    public String allNewsPage(Model model) {
        return "allNews";
    }

    @GetMapping("/newsByGame/{gameId}")
    public String newsByGamePage(@PathVariable(name = "gameId") Long gameId,
                                     Model model) {
        Game game = gameService.getGameById(gameId);
        if (game != null) {
            model.addAttribute("game", game);
            return "newsByGame";
        }
        return "redirect:/allNews?errorGameNotFound";
    }

    @GetMapping("/newsByPlatform/{platformId}")
    public String newsByPlatformPage(@PathVariable(name = "platformId") Long platformId,
                                  Model model) {
        GamePlatform platform = gamePlatformService.getPlatformById(platformId);
        if (platform != null) {
            model.addAttribute("platform", platform);
            return "newsByPlatform";
        }
        return "redirect:/allNews?errorPlatformNotFound";
    }

    @GetMapping("/newsByGenre/{genreId}")
    public String newsByGenrePage(@PathVariable(name = "genreId") Long genreId,
                                  Model model) {
        Genre genre = genreService.getGenreById(genreId);
        if (genre != null) {
            model.addAttribute("genre", genre);
            return "newsByGenre";
        }
        return "redirect:/allNews?errorGenreNotFound";
    }

    @GetMapping("/detailsNews/{newsId}")
    public String detailsNewsPage(@PathVariable(name = "newsId") Long newsId,
                                  Model model) {
        NewsDto news = newsService.getNewsById(newsId);
        if (news != null) {
            model.addAttribute("news", news);
            return "detailsNews";
        }
        return "redirect:/allNews?errorNewsNotFound";
    }

    @GetMapping("/allBlogs")
    public String allBlogsPage(Model model) {
        return "allBlogs";
    }

    @GetMapping("/blogsByGame/{gameId}")
    public String blogsByGamePage(@PathVariable(name = "gameId") Long gameId,
                                 Model model) {
        Game game = gameService.getGameById(gameId);
        if (game != null) {
            model.addAttribute("game", game);
            return "blogsByGame";
        }
        return "redirect:/allNews?errorGameNotFound";
    }

    @GetMapping("/blogsByPlatform/{platformId}")
    public String blogsByPlatformPage(@PathVariable(name = "platformId") Long platformId,
                                     Model model) {
        GamePlatform platform = gamePlatformService.getPlatformById(platformId);
        if (platform != null) {
            model.addAttribute("platform", platform);
            return "blogsByPlatform";
        }
        return "redirect:/allNews?errorPlatformNotFound";
    }

    @GetMapping("/blogsByGenre/{genreId}")
    public String blogsByGenrePage(@PathVariable(name = "genreId") Long genreId,
                                  Model model) {
        Genre genre = genreService.getGenreById(genreId);
        if (genre != null) {
            model.addAttribute("genre", genre);
            return "blogsByGenre";
        }
        return "redirect:/allNews?errorGenreNotFound";
    }

    @GetMapping("/detailsBlog/{blogId}")
    public String detailsBlogsPage(@PathVariable(name = "blogId") Long blogId,
                                  Model model) {
        BlogDto blog = blogService.getBlogById(blogId);
        if (blog != null) {
            model.addAttribute("blog", blog);
            if (userService.getCurrentUser() != null) {
                List<Favorites> favorites = favoritesService.getAllFavoritesByUser(userService.getCurrentUser().getUserId());
                boolean inFavorite = false;
                for (int i = 0; i < favorites.size(); i++) {
                    if (favorites.get(i).getBlogs().getBlogId().equals(blog.getBlogId())) {
                        inFavorite = true;
                        break;
                    }
                }
                model.addAttribute("inFavorite", inFavorite);
                model.addAttribute("currentUser", userService.getCurrentUser());
                model.addAttribute("userEmail", userService.getCurrentUser().getUserEmail());
            }
            List<Comments> allComments = commentsService.getAllCommentsByBlog(blogId);
            model.addAttribute("allComments", allComments);
            return "detailsBlog";
        }
        return "redirect:/allBlogs?errorBlogNotFound";
    }

    @PreAuthorize("hasRole('ROLE_BLOGGER') && !hasRole('ROLE_BAN')")
    @GetMapping("/addBlog")
    public String addBlogPage(Model model) {
        List<Game> allGames = gameService.getAllGames();
        model.addAttribute("allGames", allGames);
        return "addBlog";
    }

    @PreAuthorize("hasRole('ROLE_BLOGGER') && !hasRole('ROLE_BAN')")
    @PostMapping("/addBlog")
    public String addBlog(Blog blog,
                          @RequestParam(name = "blogImageToken") MultipartFile blogImageToken) {
        Blog newBlog = blogService.addBlog(blog, blogImageToken);
        if (newBlog != null) {
            return "redirect:/addBlog?success";
        }
        return "redirect:/addBlog?errorAddBlog";
    }

    // FAVORITES
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/myFavorites")
    public String myFavoritesPage(Model model) {
        List<Favorites> allFavorites = favoritesService.getAllFavoritesByUser(userService.getCurrentUser().getUserId());
        model.addAttribute("allFavorites", allFavorites);
        return "myFavorites";
    }

    @PreAuthorize("isAuthenticated() && !hasRole('ROLE_BAN')")
    @PostMapping("/addToFavorites")
    public String addBlogToFavorites(@RequestParam(name = "blogId") Long blogId) {
        Favorites newFavorite = favoritesService.addToFavorite(userService.getCurrentUser().getUserId(), blogId);
        if (newFavorite != null) {
            return "redirect:/detailsBlog/" + blogId + "?successAddToFavorites";
        }
        return "redirect:/detailsBlog/" + blogId + "?errorAddToFavorites";
    }

    @PreAuthorize("isAuthenticated() && !hasRole('ROLE_BAN')")
    @PostMapping("/deleteFromFavorites")
    public String deleteBlogFromFavorites(@RequestParam(name = "blogId") Long blogId) {
        BlogDto blogDto = blogService.getBlogById(blogId);
        if (blogDto != null) {
            favoritesService.deleteFromFavorite(userService.getCurrentUser().getUserId(), blogId);
            return "redirect:/detailsBlog/" + blogId + "?successRemoveFromFavorites";
        }
        return "redirect:/detailsBlog/" + blogId + "?errorRemoveFromFavorites";
    }

}
