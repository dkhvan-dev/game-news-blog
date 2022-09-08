package springbootcamp.mainfinalproject.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import springbootcamp.mainfinalproject.model.*;
import springbootcamp.mainfinalproject.service.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class AdminController {

    private final GameService gameService;
    private final NewsService newsService;
    private final BlogService blogService;
    private final ApplicationFormBloggerService applicationFormBloggerService;
    private final FeedbackService feedbackService;

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
        return "admin/adminPanel";
    }
}
