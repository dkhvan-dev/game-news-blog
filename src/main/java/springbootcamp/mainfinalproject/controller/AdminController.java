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
    private final ApplicationFormBloggerService applicationFormBloggerService;
    private final FeedbackService feedbackService;
    private final GamePlatformService gamePlatformService;
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
        return "admin/adminPanel";
    }

    @PostMapping("/addGame")
    public String addGame(@RequestParam(name = "gameImage") MultipartFile gameImage,
                          Game game) {
        if (gameImage != null && gameImage.getContentType() != null) {
            if ((gameImage.getContentType().equals("image/jpeg") || gameImage.getContentType().equals("image/png")) && game != null) {
                fileUploadService.uploadGameImage(gameImage, game);
                return "redirect:/detailsGameAdmin/" + game.getGameId();
            }
        }
        return "redirect:/addGame?error";
    }

    @GetMapping("/detailsGameAdmin/{gameId}")
    public String detailsGameAdminPage(@PathVariable(name = "gameId") Long gameId,
                                       Model model) {
        Game game = gameService.getGameById(gameId);
        if (game != null) {
            model.addAttribute("game", game);
            List<GamePlatform> allPlatforms = gamePlatformService.getAllPlatforms();
            model.addAttribute("allPlatforms", allPlatforms);
            return "admin/detailsGameAdmin";
        }
        return "redirect:/adminPanel";
    }

    @GetMapping(value = "/getGameImage/{gameImageToken}", produces = {MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE})
    public @ResponseBody byte[] getGameImage(@PathVariable(name = "gameImageToken", required = false) String gameImageToken) throws IOException {
        String pictureURL = loadURL + "defaultGameImage.jpg";
        if (gameImageToken != null) {
            pictureURL = loadURL + gameImageToken + ".jpg";
        }
        InputStream in;
        try {
            ClassPathResource resource = new ClassPathResource(pictureURL);
            in = resource.getInputStream();
        } catch (Exception e) {
            pictureURL = loadURL + "defaultGameImage.jpg";
            ClassPathResource resource = new ClassPathResource(pictureURL);
            in = resource.getInputStream();
        }
        return IOUtils.toByteArray(in);
    }



}
