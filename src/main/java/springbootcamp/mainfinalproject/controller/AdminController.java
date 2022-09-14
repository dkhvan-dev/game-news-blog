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
    private final FileUploadService fileUploadService;

    @Value("${loadURL}")
    private String loadURL;

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/adminPanel")
    public String adminPanelPage(Model model) {
        List<Game> allGames = gameService.getAllGames();
        model.addAttribute("allGames", allGames);
        List<News> allNews = newsService.getAllNews();
        model.addAttribute("allNews", allNews);
        List<Blog> allBlogs = blogService.getAllBlogs();
        model.addAttribute("allBlogs", allBlogs);
        List<ApplicationFormBlogger> allApplicationForBloggerRole = applicationFormBloggerService.getAllApplications();
        model.addAttribute("allApplicationForBloggerRole", allApplicationForBloggerRole);
        List<Feedback> allFeedback = feedbackService.getAllFeedback();
        model.addAttribute("allFeedback", allFeedback);
        List<GamePlatform> allPlatforms = gamePlatformService.getAllPlatforms();
        model.addAttribute("allPlatforms", allPlatforms);
        List<Genre> allGenres = genreService.getAllGenres();
        model.addAttribute("allGenres", allGenres);
        List<Comments> allComments = commentsService.getAllComments();
        model.addAttribute("allComments", allComments);
        return "admin/adminPanel";
    }

    // GAME

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/addGame")
    public String addGame(@RequestParam(name = "gameImageToken") MultipartFile gameImage,
                          Game game,
                          GameRequirements gameRequirements,
                          GameRating gameRating) {
        if (game != null && gameImage != null && game.getGameId() == null) {
            Game newGame = gameService.addNewGame(gameImage, game, gameRequirements, gameRating);
            if (newGame != null) {
                return "redirect:/detailsGameAdmin/" + game.getGameId();
            }
        }
        return "redirect:/adminPanel?errorAddGame";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
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

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/editGame")
    public String editGame(@RequestParam(name = "gameImageToken") MultipartFile gameImageToken,
                           Game game,
                           GameRequirements gameRequirements,
                           GameRating gameRating) {
        Game editedGame = gameService.editGame(gameImageToken, game, gameRequirements, gameRating);
        return "redirect:/detailsGameAdmin/" + game.getGameId();
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/deleteGameAdmin")
    public String deleteGameAdmin(@RequestParam(name = "gameId") Long gameId) {
        gameService.deleteGame(gameId);
        return "redirect:/adminPanel";
    }

    // NEWS
    @PreAuthorize("hasRole('ROLE_MODERATOR')")
    @PostMapping("/addNews")
    public String addNews(News news) {
        News newNews = newsService.addNews(news);
        if (newNews != null) {
            return "redirect:/detailsNewsAdmin/" + news.getNewsId();
        }
        return "redirect:/adminPanel?errorAddNews";
    }

    @PreAuthorize("hasRole('ROLE_MODERATOR')")
    @PostMapping("/deleteNewsAdmin")
    public String deleteNews(@RequestParam(name = "newsId") Long newsId) {
        newsService.deleteNews(newsId);
        return "redirect:/adminPanel";
    }

    @PreAuthorize("hasRole('ROLE_MODERATOR')")
    @GetMapping("/detailsNewsAdmin/{newsId}")
    public String detailsNewsAdminPage(@PathVariable(name = "newsId") Long newsId,
                                       Model model) {
        News news = newsService.getNewsById(newsId);
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

    @PreAuthorize("hasRole('ROLE_MODERATOR')")
    @PostMapping("/editNews")
    public String editNews(News news, @RequestParam(name = "createNews") String newsCreateDate, @RequestParam(name = "author") Long authorId) {
        News editedNews = newsService.editNews(news, newsCreateDate);
        if (editedNews != null) {
            return "redirect:/detailsNewsAdmin/" + news.getNewsId() + "?success";
        }
        return "redirect:/detailsNewsAdmin/" + news.getNewsId() + "?errorEditNews";
    }

    // BLOGS

    @PreAuthorize("hasRole('ROLE_MODERATOR')")
    @GetMapping("/detailsBlogAdmin/{blogId}")
    public String detailsBlogAdminPage(@PathVariable(name = "blogId") Long blogId,
                                       Model model) {
        Blog blog = blogService.getBlogById(blogId);
        if (blog != null) {
            model.addAttribute("blog", blog);
            List<Game> allGames = gameService.getAllGames();
            model.addAttribute("allGames", allGames);
            return "moderator/detailsBlogAdmin";
        }
        return "redirect:/adminPanel";
    }

    @PreAuthorize("hasRole('ROLE_MODERATOR')")
    @PostMapping("/editBlogAdmin")
    public String editBlogAdmin(Blog blog,
                                @RequestParam(name = "blogImageToken") MultipartFile blogImageToken,
                                @RequestParam(name = "createDate") String blogCreateDate,
                                @RequestParam(name = "updateDate") String blogUpdateDate,
                                @RequestParam(name = "users") Long authorId) {
        Blog editedBlog = blogService.editBlogAdmin(blog, blogImageToken, blogCreateDate, blogUpdateDate, authorId);
        if (editedBlog != null) {
            return "redirect:/detailsBlogAdmin/" + blog.getBlogId() + "?success";
        }
        return "redirect:/detailsBlogAdmin/" + blog.getBlogId() + "?errorEditBlog";
    }

    @PreAuthorize("hasRole('ROLE_MODERATOR')")
    @PostMapping("/deleteBlogAdmin")
    public String deleteBlogAdmin(@RequestParam(name = "blogId") Long blogId) {
        blogService.deleteBlogAdmin(blogId);
        return "redirect:/adminPanel";
    }

    //APPLICATION FOR ROLE BLOGGER
    @PostMapping("/applicationFormBlogger")
    public String applicationFormBlogger(ApplicationFormBlogger applicationFormBlogger) {
        applicationFormBloggerService.addApplicationFormBlogger(applicationFormBlogger);
        return "redirect:/profile";
    }

    @PreAuthorize("hasRole('ROLE_MODERATOR')")
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
            return "admin/detailsApplicationFormBloggerAdmin";
        }
        return "redirect:/adminPanel?errorNotFoundApplication";
    }

    @PreAuthorize("hasRole('ROLE_MODERATOR')")
    @PostMapping("/updateFormBlogger")
    public String updateFormBlogger(ApplicationFormBlogger applicationFormBlogger,
                                    @RequestParam(name = "receiptDate") String receiptDate,
                                    @RequestParam(name = "updateDate") String updateDate,
                                    @RequestParam(name = "authorId") Long authorId) {
        ApplicationFormBlogger updatedApplicationForm = applicationFormBloggerService.updateApplicationForm(applicationFormBlogger, receiptDate, updateDate, authorId);
        if (updatedApplicationForm != null) {
            return "redirect:/detailsApplicationFormBloggerAdmin/" + applicationFormBlogger.getApplicationFormBloggerId() + "?success";
        }
        return "redirect:/detailsApplicationFormBloggerAdmin/" + applicationFormBlogger.getApplicationFormBloggerId() + "?errorUpdateApplication";
    }

    // Feedback
    @PreAuthorize("hasRole('ROLE_MODERATOR')")
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

    @PreAuthorize("hasRole('ROLE_MODERATOR')")
    @PostMapping("/editFeedback")
    public String editFeedback(Feedback feedback) {
        Feedback editedFeedback = feedbackService.editFeedback(feedback);
        if (editedFeedback != null) {
            return "redirect:/detailsFeedbackAdmin/" + feedback.getFeedbackId() + "?success";
        }
        return "redirect:/detailsFeedbackAdmin/" + feedback.getFeedbackId() + "?errorEditFeedback";
    }

    // COMMENTS
    @PreAuthorize("hasRole('ROLE_MODERATOR')")
    @PostMapping("/deleteCommentAdmin")
    public String deleteCommentAdmin(@RequestParam(name = "commentId") Long commentId) {
        Comments comment = commentsService.getCommentById(commentId);
        if (comment != null) {
            commentsService.deleteComment(commentId);
            return "redirect:/detailsBlog/" + comment.getBlog().getBlogId();
        }
        return "redirect:/detailsBlog/" + comment.getBlog().getBlogId() + "?errorCommentNotFound";
    }

    // Load Image
    @GetMapping(value = "/getGameImage/{gameImageToken}", produces = {MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE})
    public @ResponseBody byte[] getGameImage(@PathVariable(name = "gameImageToken", required = false) String gameImageToken) throws IOException {
        String pictureURL = loadURL + "defaultGameImage.jpg";
        if (gameImageToken != null) {
            pictureURL = loadURL + gameImageToken + ".jpg";
        }
        InputStream inputStream;
        try {
            ClassPathResource resource = new ClassPathResource(pictureURL);
            inputStream = resource.getInputStream();
        } catch (Exception e) {
            pictureURL = loadURL + "defaultGameImage.jpg";
            ClassPathResource resource = new ClassPathResource(pictureURL);
            inputStream = resource.getInputStream();
            e.printStackTrace();
        }
        return IOUtils.toByteArray(inputStream);
    }

    @GetMapping(value = "/getBlogImage/{blogImageToken}", produces = {MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE})
    public @ResponseBody byte[] getBlogImage(@PathVariable(name = "blogImageToken", required = false) String blogImageToken) throws IOException {
        String pictureURL = loadURL + "defaultGameImage.jpg";
        if (blogImageToken != null) {
            pictureURL = loadURL + blogImageToken + ".jpg";
        }
        InputStream inputStream;
        try {
            ClassPathResource resource = new ClassPathResource(pictureURL);
            inputStream = resource.getInputStream();
        } catch (Exception e) {
            pictureURL = loadURL + "defaultGameImage.jpg";
            ClassPathResource resource = new ClassPathResource(pictureURL);
            inputStream = resource.getInputStream();
            e.printStackTrace();
        }
        return IOUtils.toByteArray(inputStream);
    }

}
