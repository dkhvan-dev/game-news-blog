package springbootcamp.mainfinalproject.controller;

import lombok.RequiredArgsConstructor;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import springbootcamp.mainfinalproject.model.*;
import springbootcamp.mainfinalproject.model.dto.BlogDto;
import springbootcamp.mainfinalproject.model.dto.NewsDto;
import springbootcamp.mainfinalproject.service.*;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class AdminController {

    private final GameService gameService;
    private final NewsService newsService;
    private final BlogService blogService;
    private final GenreService genreService;
    private final ApplicationFormBloggerService applicationFormBloggerService;
    private final ApplicationBloggerStatusService applicationBloggerStatusService;
    private final FeedbackService feedbackService;
    private final FeedbackStatusService feedbackStatusService;
    private final GamePlatformService gamePlatformService;
    private final CommentsService commentsService;
    private final UserService userService;
    private final BlogStatusService blogStatusService;
    private final RoleService roleService;

    @Value("${loadURL}")
    private String loadURL;

    @PreAuthorize("hasAnyRole('ROLE_MODERATOR', 'ROLE_ADMIN')")
    @GetMapping("/adminPanel")
    public String adminPanelPage(Model model) {
        List<User> allUsers = userService.getAllUsers();
        model.addAttribute("allUsers", allUsers);
        return "admin/adminPanel";
    }

    // GAME

    @PreAuthorize("hasAnyRole('ROLE_MODERATOR', 'ROLE_ADMIN')")
    @PostMapping("/addGame")
    public String addGame(@RequestParam(name = "gameImageToken") MultipartFile gameImage,
                          Game game,
                          GameRequirements gameRequirements,
                          GameRating gameRating) {
        if (game != null && gameImage != null && game.getGameId() == null) {
            Game newGame = gameService.addNewGame(gameImage, game, gameRequirements, gameRating);
            if (newGame != null) {
                return "redirect:/detailsGameAdmin/" + game.getGameId() + "?success";
            }
        }
        return "redirect:/adminPanel?errorAddGame";
    }

    @PreAuthorize("hasAnyRole('ROLE_MODERATOR', 'ROLE_ADMIN')")
    @GetMapping("/detailsGameAdmin/{gameId}")
    public String detailsGameAdminPage(@PathVariable(name = "gameId") Long gameId,
                                       Model model) {
        Game game = gameService.getGameById(gameId);
        if (game != null) {
            model.addAttribute("game", game);
            List<GamePlatform> allPlatforms = gamePlatformService.getAllPlatforms();
            model.addAttribute("allPlatforms", allPlatforms);
            GameRequirements gameRequirements = game.getGameRequirement();
            model.addAttribute("gameRequirements", gameRequirements);
            GameRating gameRating = game.getGameRatings();
            model.addAttribute("gameRating", gameRating);
            List<Genre> allGenres = genreService.getAllGenres();
            model.addAttribute("allGenres", allGenres);
            return "admin/detailsGameAdmin";
        }
        return "redirect:/adminPanel";
    }

    @PreAuthorize("hasAnyRole('ROLE_MODERATOR', 'ROLE_ADMIN')")
    @PostMapping("/editGame")
    public String editGame(@RequestParam(name = "gameImageToken") MultipartFile gameImageToken,
                           Game game,
                           GameRequirements gameRequirements,
                           GameRating gameRating) {
        Game editedGame = gameService.editGame(gameImageToken, game, gameRequirements, gameRating);
        return "redirect:/detailsGameAdmin/" + game.getGameId() + "?success";
    }

    @PreAuthorize("hasAnyRole('ROLE_MODERATOR', 'ROLE_ADMIN')")
    @PostMapping("/deleteGameAdmin")
    public String deleteGameAdmin(@RequestParam(name = "gameId") Long gameId) {
        Game game = gameService.getGameById(gameId);
        if (game != null) {
            gameService.deleteGame(gameId);
            return "redirect:/adminPanel?successDeleteGame";
        }
        return "redirect:/adminPanel?errorDeleteGame";
    }

    // PLATFORM
    @PreAuthorize("hasAnyRole('ROLE_MODERATOR', 'ROLE_ADMIN')")
    @PostMapping("/addPlatformAdmin")
    public String addPlatform(GamePlatform platform) {
        GamePlatform newPlatform = gamePlatformService.addPlatform(platform);
        if (newPlatform != null) {
            return "redirect:/adminPanel?successAddPlatform";
        }
        return "redirect:/adminPanel?errorAddPlatform";
    }

    @PreAuthorize("hasAnyRole('ROLE_MODERATOR', 'ROLE_ADMIN')")
    @PostMapping("/editPlatformAdmin")
    public String editPlatform(GamePlatform platform) {
        GamePlatform gamePlatform = gamePlatformService.editPlatform(platform);
        if (gamePlatform != null) {
            return "redirect:/adminPanel?successEditPlatform";
        }
        return "redirect:/adminPanel?errorEditPlatform";
    }

    @PreAuthorize("hasAnyRole('ROLE_MODERATOR', 'ROLE_ADMIN')")
    @PostMapping("/deletePlatformAdmin")
    public String deletePlatform(@RequestParam(name = "platformId") Long platformId) {
        GamePlatform platform = gamePlatformService.getPlatformById(platformId);
        if (platform != null) {
            gamePlatformService.deletePlatform(platformId);
            return "redirect:/adminPanel?successDeletePlatform";
        }
        return "redirect:/adminPanel?errorDeletePlatform";
    }

    // GENRE
    @PreAuthorize("hasAnyRole('ROLE_MODERATOR', 'ROLE_ADMIN')")
    @PostMapping("/addGenreAdmin")
    public String addGenre(Genre genre) {
        Genre newGenre = genreService.addGenre(genre);
        if (newGenre != null) {
            return "redirect:/adminPanel?successAddGenre";
        }
        return "redirect:/adminPanel?errorAddGenre";
    }

    @PreAuthorize("hasAnyRole('ROLE_MODERATOR', 'ROLE_ADMIN')")
    @PostMapping("/editGenreAdmin")
    public String editGenre(Genre genre) {
        Genre editedGenre = genreService.editGenre(genre);
        if (editedGenre != null) {
            return "redirect:/adminPanel?successEditGenre";
        }
        return "redirect:/adminPanel?errorEditGenre";
    }

    @PreAuthorize("hasAnyRole('ROLE_MODERATOR', 'ROLE_ADMIN')")
    @PostMapping("/deleteGenreAdmin")
    public String deleteGenre(@RequestParam(name = "genreId") Long genreId) {
        Genre genre = genreService.getGenreById(genreId);
        if (genre != null) {
            genreService.deleteGenre(genreId);
            return "redirect:/adminPanel?successDeleteGenre";
        }
        return "redirect:/adminPanel?errorDeleteGenre";
    }

    // NEWS
    @PreAuthorize("hasAnyRole('ROLE_MODERATOR', 'ROLE_ADMIN')")
    @PostMapping("/addNews")
    public String addNews(News news) {
        News newNews = newsService.addNews(news);
        if (newNews != null) {
            return "redirect:/detailsNewsAdmin/" + news.getNewsId();
        }
        return "redirect:/adminPanel?errorAddNews";
    }

    @PreAuthorize("hasAnyRole('ROLE_MODERATOR', 'ROLE_ADMIN')")
    @PostMapping("/deleteNewsAdmin")
    public String deleteNews(@RequestParam(name = "newsId") Long newsId) {
        newsService.deleteNews(newsId);
        return "redirect:/adminPanel";
    }

    @PreAuthorize("hasAnyRole('ROLE_MODERATOR', 'ROLE_ADMIN')")
    @GetMapping("/detailsNewsAdmin/{newsId}")
    public String detailsNewsAdminPage(@PathVariable(name = "newsId") Long newsId,
                                       Model model) {
        NewsDto news = newsService.getNewsById(newsId);
        if (news != null) {
            model.addAttribute("news", news);
            Game game = news.getGame();
            model.addAttribute("game", game);
            List<Game> allGames = gameService.getAllGames();
            model.addAttribute("allGames", allGames);
            return "moderator/detailsNewsAdmin";
        }
        return "redirect:/adminPanel";
    }

    @PreAuthorize("hasAnyRole('ROLE_MODERATOR', 'ROLE_ADMIN')")
    @PostMapping("/editNews")
    public String editNews(News news,
                           @RequestParam(name = "createNews") String newsCreateDate,
                           @RequestParam(name = "author") Long authorId) {
        News editedNews = newsService.editNews(news, newsCreateDate);
        if (editedNews != null) {
            return "redirect:/detailsNewsAdmin/" + news.getNewsId() + "?success";
        }
        return "redirect:/detailsNewsAdmin/" + news.getNewsId() + "?errorEditNews";
    }

    // BLOGS

    @PreAuthorize("hasAnyRole('ROLE_MODERATOR', 'ROLE_ADMIN')")
    @GetMapping("/detailsBlogAdmin/{blogId}")
    public String detailsBlogAdminPage(@PathVariable(name = "blogId") Long blogId,
                                       Model model) {
        BlogDto blog = blogService.getBlogById(blogId);
        if (blog != null) {
            model.addAttribute("blog", blog);
            List<Game> allGames = gameService.getAllGames();
            model.addAttribute("allGames", allGames);
            List<BlogStatus> blogStatusList = blogStatusService.getAllStatus();
            model.addAttribute("blogStatusList", blogStatusList);
            return "moderator/detailsBlogAdmin";
        }
        return "redirect:/adminPanel";
    }

    @PreAuthorize("hasAnyRole('ROLE_MODERATOR', 'ROLE_ADMIN')")
    @PostMapping("/editBlogAdmin")
    public String editBlogAdmin(Blog blog,
                                @RequestParam(name = "blogImageToken") MultipartFile blogImageToken,
                                @RequestParam(name = "createDate") String blogCreateDate,
                                @RequestParam(name = "users") Long authorId) {
        if (blog.getBlogStatus().getBlogStatusName().equals("REJECTED")) {
            blogService.deleteBlogAdmin(blog.getBlogId());
            return "redirect:/adminPanel?successRejectedBlog";
        } else {
            Blog editedBlog = blogService.editBlogAdmin(blog, blogImageToken, blogCreateDate, authorId);
            if (editedBlog != null) {
                return "redirect:/detailsBlogAdmin/" + blog.getBlogId() + "?success";
            }
        }
        return "redirect:/detailsBlogAdmin/" + blog.getBlogId() + "?errorEditBlog";
    }

    @PreAuthorize("hasAnyRole('ROLE_MODERATOR', 'ROLE_ADMIN')")
    @PostMapping("/deleteBlogAdmin")
    public String deleteBlogAdmin(@RequestParam(name = "blogId") Long blogId) {
        blogService.deleteBlogAdmin(blogId);
        return "redirect:/adminPanel?successDeleteBlog";
    }

    //APPLICATION FOR ROLE BLOGGER
    @PreAuthorize("!hasRole('ROLE_BLOGGER')")
    @PostMapping("/applicationFormBlogger")
    public String applicationFormBlogger(ApplicationFormBlogger applicationFormBlogger) {
        applicationFormBloggerService.addApplicationFormBlogger(applicationFormBlogger);
        return "redirect:/profile";
    }

    @PreAuthorize("hasAnyRole('ROLE_MODERATOR', 'ROLE_ADMIN')")
    @GetMapping("/detailsApplicationFormBloggerAdmin/{applicationFormId}")
    public String updateFormBloggerPage(@PathVariable(name = "applicationFormId") Long applicationFormId,
                                    Model model) {
        ApplicationFormBlogger applicationForm = applicationFormBloggerService.getApplicationFormById(applicationFormId);
        if (applicationForm != null) {
            model.addAttribute("applicationForm", applicationForm);
            List<ApplicationBloggerStatus> allBloggerStatus = applicationBloggerStatusService.getAllBloggerStatus();
            model.addAttribute("allBloggerStatus", allBloggerStatus);

            boolean canUpdateApplication = true;
            if (applicationForm.getApplicationBloggerStatus().getApplicationBloggerStatusId() == 2 || applicationForm.getApplicationBloggerStatus().getApplicationBloggerStatusId() == 3) {
                canUpdateApplication = false;
            }
            model.addAttribute("canUpdateApplication", canUpdateApplication);
            return "moderator/detailsApplicationFormBloggerAdmin";
        }
        return "redirect:/adminPanel?errorNotFoundApplication";
    }

    @PreAuthorize("hasAnyRole('ROLE_MODERATOR', 'ROLE_ADMIN')")
    @PostMapping("/updateFormBlogger")
    public String updateFormBlogger(ApplicationFormBlogger applicationFormBlogger,
                                    @RequestParam(name = "receiptDate") String receiptDate,
                                    @RequestParam(name = "authorId") Long authorId) {
        ApplicationFormBlogger updatedApplicationForm = applicationFormBloggerService.updateApplicationForm(applicationFormBlogger, receiptDate, authorId);
        if (updatedApplicationForm != null) {
            return "redirect:/detailsApplicationFormBloggerAdmin/" + applicationFormBlogger.getApplicationFormBloggerId() + "?success";
        }
        return "redirect:/detailsApplicationFormBloggerAdmin/" + applicationFormBlogger.getApplicationFormBloggerId() + "?errorUpdateApplication";
    }

    // Feedback
    @PreAuthorize("hasAnyRole('ROLE_MODERATOR', 'ROLE_ADMIN')")
    @GetMapping("/detailsFeedbackAdmin/{feedbackId}")
    public String detailsFeedbackPage(@PathVariable(name = "feedbackId") Long feedbackId,
                                      Model model) {
        Feedback feedback = feedbackService.getFeedbackById(feedbackId);
        if (feedback != null) {
            model.addAttribute("feedback", feedback);
            List<FeedbackStatus> allFeedbackStatus = feedbackStatusService.getAllFeedbackStatus();
            model.addAttribute("allFeedbackStatus", allFeedbackStatus);
            boolean canUpdateFeedback = true;
            if (feedback.getFeedbackStatus().getFeedbackStatusId() == 2) {
                canUpdateFeedback = false;
            }
            model.addAttribute("canUpdateFeedback", canUpdateFeedback);
            return "moderator/detailsFeedbackAdmin";
        }
        return "redirect:/adminPanel?errorFeedbackNotFound";
    }

    @PreAuthorize("hasAnyRole('ROLE_MODERATOR', 'ROLE_ADMIN')")
    @PostMapping("/editFeedback")
    public String editFeedback(Feedback feedback) {
        Feedback editedFeedback = feedbackService.editFeedback(feedback);
        if (editedFeedback != null) {
            return "redirect:/detailsFeedbackAdmin/" + feedback.getFeedbackId() + "?success";
        }
        return "redirect:/detailsFeedbackAdmin/" + feedback.getFeedbackId() + "?errorEditFeedback";
    }

    // COMMENTS
    @PreAuthorize("hasAnyRole('ROLE_MODERATOR', 'ROLE_ADMIN')")
    @PostMapping("/deleteCommentAdmin")
    public String deleteCommentAdmin(@RequestParam(name = "commentId") Long commentId) {
        Comments comment = commentsService.getCommentById(commentId);
        if (comment != null) {
            commentsService.deleteComment(commentId);
            return "redirect:/detailsBlog/" + comment.getBlog().getBlogId();
        }
        return "redirect:/detailsBlog/" + comment.getBlog().getBlogId() + "?errorCommentNotFound";
    }

    // USERS
    @PreAuthorize("hasAnyRole('ROLE_MODERATOR', 'ROLE_ADMIN')")
    @GetMapping("/detailsUserAdmin/{userId}")
    public String detailsUser(@PathVariable(name = "userId") Long userId,
                              Model model) {
        User user = userService.getUserById(userId);
        if (user != null) {
            model.addAttribute("user", user);
            Role adminRole = roleService.getRoleByName("ROLE_ADMIN");
            model.addAttribute("adminRole", adminRole);
            List<Role> allRoles = roleService.getAllRoles();
            model.addAttribute("allRoles", allRoles);
        }
        return "admin/detailsUserAdmin";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/updateUserAdmin")
    public String updateUserAdmin(@RequestParam(name = "userId") Long userId,
                                  @RequestParam(name = "roles") List<Role> roles) {
        User user = userService.getUserById(userId);
        if (user != null) {
            if (userService.updateUserAdmin(user, roles)) {
                return "redirect:/detailsUserAdmin/" + userId + "?successUpdateUser";
            }
        }
        return "redirect:/detailsUserAdmin/" + userId + "?errorUpdateUser";
    }

    // ROLE
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/addRoleAdmin")
    public String addRole(Role role) {
        Role newRole = roleService.addRole(role);
        if (newRole != null) {
            return "redirect:/adminPanel?successAddRole";
        }
        return "redirect:/adminPanel?errorAddRole";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/editRoleAdmin")
    public String editRole(Role role) {
        Role editedRole = roleService.editRole(role);
        if (editedRole != null) {
            return "redirect:/adminPanel?successEditRole";
        }
        return "redirect:/adminPanel?errorEditRole";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/deleteRoleAdmin")
    public String deleteRole(@RequestParam(name = "roleId") Long roleId) {
        Role role = roleService.getRoleById(roleId);
        if (role != null) {
            roleService.deleteRole(role.getRoleName());
            return "redirect:/adminPanel?successDeleteRole";
        }
        return "redirect:/adminPanel?errorDeleteRole";
    }

    // Load Image
    @GetMapping(value = "/getUserImage/{userImageToken}", produces = {MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE})
    public @ResponseBody byte[] getUserImage(@PathVariable(name = "userImageToken", required = false) String userImageToken) throws IOException {
        String pictureURL = loadURL + "defaultAvatar.png";
        if (userImageToken != null) {
            pictureURL = loadURL + "avatars/" + userImageToken + ".jpg";
        }
        InputStream inputStream;
        try {
            ClassPathResource resource = new ClassPathResource(pictureURL);
            inputStream = resource.getInputStream();
        } catch (Exception e) {
            pictureURL = loadURL + "defaultAvatar.png";
            ClassPathResource resource = new ClassPathResource(pictureURL);
            inputStream = resource.getInputStream();
            e.printStackTrace();
        }
        return IOUtils.toByteArray(inputStream);
    }

    @GetMapping(value = "/getGameImage/{gameImageToken}", produces = {MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE})
    public @ResponseBody byte[] getGameImage(@PathVariable(name = "gameImageToken", required = false) String gameImageToken) throws IOException {
        String pictureURL = loadURL + "games/defaultGameImage.jpg";
        if (gameImageToken != null) {
            pictureURL = loadURL + "games/" + gameImageToken + ".jpg";
        }
        InputStream inputStream;
        try {
            ClassPathResource resource = new ClassPathResource(pictureURL);
            inputStream = resource.getInputStream();
        } catch (Exception e) {
            pictureURL = loadURL + "games/defaultGameImage.jpg";
            ClassPathResource resource = new ClassPathResource(pictureURL);
            inputStream = resource.getInputStream();
            e.printStackTrace();
        }
        return IOUtils.toByteArray(inputStream);
    }

    @GetMapping(value = "/getBlogImage/{blogImageToken}", produces = {MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE})
    public @ResponseBody byte[] getBlogImage(@PathVariable(name = "blogImageToken", required = false) String blogImageToken) throws IOException {
        String pictureURL = loadURL + "games/defaultGameImage.jpg";
        if (blogImageToken != null) {
            pictureURL = loadURL + "blog/" + blogImageToken + ".jpg";
        }
        InputStream inputStream;
        try {
            ClassPathResource resource = new ClassPathResource(pictureURL);
            inputStream = resource.getInputStream();
        } catch (Exception e) {
            pictureURL = loadURL + "games/defaultGameImage.jpg";
            ClassPathResource resource = new ClassPathResource(pictureURL);
            inputStream = resource.getInputStream();
            e.printStackTrace();
        }
        return IOUtils.toByteArray(inputStream);
    }

}
