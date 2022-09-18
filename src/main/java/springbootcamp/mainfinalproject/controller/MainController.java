package springbootcamp.mainfinalproject.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
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
    private final FileUploadService fileUploadService;

    @GetMapping("/")
    public String homePage(Model model) {
        List<Game> allGames = gameService.getAllGames();
        model.addAttribute("allGames", allGames);
        List<NewsDto> last3News = newsService.getLatest3News();
        model.addAttribute("last3News", last3News);
        List<BlogDto> last3Blogs = blogService.getTop3Blogs();
        model.addAttribute("last3Blogs", last3Blogs);
        List<Game> top5Games = gameService.getTop5Games();
        model.addAttribute("top5Games", top5Games);
        return "index";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/footer")
    public String footerPage(Model model) {
        List<FeedbackStatus> allFeedbackStatus = feedbackStatusService.getAllFeedbackStatus();
        model.addAttribute("allFeedbackStatus", allFeedbackStatus);
        return "toInsert/footer";
    }

    @PreAuthorize("!hasRole('ROLE_BAN')")
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

    @PreAuthorize("isAuthenticated() && !hasRole('ROLE_BAN')")
    @GetMapping("/profile")
    public String profilePage(Model model) {
        model.addAttribute("currentUser", userService.getCurrentUser());
        return "profile";
    }

    @PreAuthorize("isAuthenticated() && !hasRole('ROLE_BAN')")
    @PostMapping("/swapUserAvatar")
    public String swapUserAvatar(@RequestParam(name = "userImageToken") MultipartFile userImageToken) {

        String param = userService.swapAvatar(userImageToken, userService.getCurrentUser());
        return "redirect:/profile?" + param;
    }

    @PreAuthorize("isAuthenticated() && !hasRole('ROLE_BAN')")
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
            List<Game> allGames = gameService.getAllGames();
            model.addAttribute("allGames", allGames);
            List<GamePlatform> allPlatforms = gamePlatformService.getAllPlatforms();
            model.addAttribute("allPlatforms", allPlatforms);
            List<Genre> allGenres = genreService.getAllGenres();
            model.addAttribute("allGenres", allGenres);
            model.addAttribute("selectedGameId", gameId);
            List<NewsDto> allNewsByGame = newsService.getAllNewsByGame(gameId);
            model.addAttribute("allNewsByGame", allNewsByGame);
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
            List<Game> allGames = gameService.getAllGames();
            model.addAttribute("allGames", allGames);
            List<GamePlatform> allPlatforms = gamePlatformService.getAllPlatforms();
            model.addAttribute("allPlatforms", allPlatforms);
            List<Genre> allGenres = genreService.getAllGenres();
            model.addAttribute("allGenres", allGenres);
            model.addAttribute("selectedPlatformId", platformId);
            List<NewsDto> allNewsByPlatform = newsService.getAllNewsByPlatform(platformId);
            model.addAttribute("allNewsByPlatform", allNewsByPlatform);
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
            List<Game> allGames = gameService.getAllGames();
            model.addAttribute("allGames", allGames);
            List<GamePlatform> allPlatforms = gamePlatformService.getAllPlatforms();
            model.addAttribute("allPlatforms", allPlatforms);
            List<Genre> allGenres = genreService.getAllGenres();
            model.addAttribute("allGenres", allGenres);
            model.addAttribute("selectedGenreId", genreId);
            List<NewsDto> allNewsByGenre = newsService.getAllNewsByGenre(genreId);
            model.addAttribute("allNewsByGenre", allNewsByGenre);
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
        List<BlogDto> allBlogs = blogService.getAllBlogs();
        model.addAttribute("allBlogs", allBlogs);
        List<Game> allGames = gameService.getAllGames();
        model.addAttribute("allGames", allGames);
        List<GamePlatform> allPlatforms = gamePlatformService.getAllPlatforms();
        model.addAttribute("allPlatforms", allPlatforms);
        List<Genre> allGenres = genreService.getAllGenres();
        model.addAttribute("allGenres", allGenres);
        return "allBlogs";
    }

    @GetMapping("/blogsByGame/{gameId}")
    public String blogsByGamePage(@PathVariable(name = "gameId") Long gameId,
                                 Model model) {
        Game game = gameService.getGameById(gameId);
        if (game != null) {
            model.addAttribute("game", game);
            List<Game> allGames = gameService.getAllGames();
            model.addAttribute("allGames", allGames);
            List<GamePlatform> allPlatforms = gamePlatformService.getAllPlatforms();
            model.addAttribute("allPlatforms", allPlatforms);
            List<Genre> allGenres = genreService.getAllGenres();
            model.addAttribute("allGenres", allGenres);
            model.addAttribute("selectedGameId", gameId);
            List<BlogDto> allBlogsByGame = blogService.getAllBlogsByGame(gameId);
            model.addAttribute("allBlogsByGame", allBlogsByGame);
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
            List<Game> allGames = gameService.getAllGames();
            model.addAttribute("allGames", allGames);
            List<GamePlatform> allPlatforms = gamePlatformService.getAllPlatforms();
            model.addAttribute("allPlatforms", allPlatforms);
            List<Genre> allGenres = genreService.getAllGenres();
            model.addAttribute("allGenres", allGenres);
            model.addAttribute("selectedPlatformId", platformId);
            List<BlogDto> allBlogsByPlatform = blogService.getAllBlogsByPlatform(platformId);
            model.addAttribute("allBlogsByPlatform", allBlogsByPlatform);
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
            List<Game> allGames = gameService.getAllGames();
            model.addAttribute("allGames", allGames);
            List<GamePlatform> allPlatforms = gamePlatformService.getAllPlatforms();
            model.addAttribute("allPlatforms", allPlatforms);
            List<Genre> allGenres = genreService.getAllGenres();
            model.addAttribute("allGenres", allGenres);
            model.addAttribute("selectedGenreId", genreId);
            List<BlogDto> allBlogsByGenre = blogService.getAllBlogsByGenre(genreId);
            model.addAttribute("allBlogsByGenre", allBlogsByGenre);
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
            List<Comments> allComments = commentsService.getAllComments();
            model.addAttribute("allComments", allComments);
            return "detailsBlog";
        }
        return "redirect:/allBlogs?errorBlogNotFound";
    }

    @PreAuthorize("hasRole('ROLE_BLOGGER')")
    @GetMapping("/addBlog")
    public String addBlogPage(Model model) {
        List<Game> allGames = gameService.getAllGames();
        model.addAttribute("allGames", allGames);
        return "addBlog";
    }

    @PreAuthorize("hasRole('ROLE_BLOGGER')")
    @PostMapping("/addBlog")
    public String addBlog(Blog blog,
                          @RequestParam(name = "blogImageToken") MultipartFile blogImageToken) {
        Blog newBlog = blogService.addBlog(blog, blogImageToken);
        if (newBlog != null) {
            return "redirect:/addBlog?success";
        }
        return "redirect:/addBlog?errorAddBlog";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/addComment")
    public String addComment(Comments comment, @RequestParam(name = "blogId") Long blogId) {
        commentsService.addComment(comment, blogId);
        return "redirect:/detailsBlog/" + comment.getBlog().getBlogId();
    }

    @PostMapping("/deleteComment")
    public String deleteComment(@RequestParam(name = "commentId") Long commentId) {
        Comments comment = commentsService.getCommentById(commentId);
        if (comment != null) {
            commentsService.deleteComment(commentId);
            return "redirect:/detailsBlog/" + comment.getBlog().getBlogId();
        }
        return "redirect:/detailsBlog/" + comment.getBlog().getBlogId() + "?errorCommentNotFound";
    }
}
