package springbootcamp.mainfinalproject.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import springbootcamp.mainfinalproject.model.*;
import springbootcamp.mainfinalproject.service.*;

import java.text.ParseException;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class MainController {
    private final UserService userService;
    private final GameService gameService;
    private final BlogService blogService;
    private final NewsService newsService;
    private final GamePlatformService gamePlatformService;
    private final GenreService genreService;
    private final FeedbackService feedbackService;
    private final FeedbackStatusService feedbackStatusService;

    @GetMapping("/")
    public String homePage(Model model) {
        List<Game> allGames = gameService.getAllGames();
        model.addAttribute("allGames", allGames);
        List<Blog> last3Blogs = blogService.getTop3Blogs();
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

    @GetMapping("/signIn")
    public String signInPage() {
        return "auth/auth";
    }

    @GetMapping("/signUp")
    public String signUpPage() {
        return "auth/register";
    }

    @PostMapping("/signUp")
    public String signUp(User user,
                         @RequestParam(name = "rePassword") String rePassword,
                         @RequestParam(name = "birthdate") String userBirthdate) throws ParseException {
        if (user.getUserPassword().equals(rePassword)) {
            User newUser = userService.signUp(user, userBirthdate);
            if (newUser == null) {
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
        model.addAttribute("currentUser", userService.getCurrentUser());
        return "profile";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/editUser")
    public String editUser(User user,
                           @RequestParam(name = "oldPassword") String oldPassword,
                           @RequestParam(name = "userPassword") String newPassword,
                           @RequestParam(name = "reNewPassword") String reNewPassword) {
        String param = userService.editUser(user, oldPassword, newPassword, reNewPassword);
        if (param.equals("success")) {
            userService.updateUserData(user.getUserFirstName(), user.getUserSurname(), user.getUserPassword());
        }
        return "redirect:/profile?" + param;
    }

    @GetMapping("/allGames")
    public String allGamesPage(Model model) {
        List<Game> allGames = gameService.getAllGames();
        model.addAttribute("allGames", allGames);
        List<GamePlatform> allPlatforms = gamePlatformService.getAllPlatforms();
        model.addAttribute("allPlatforms", allPlatforms);
        List<Genre> allGenres = genreService.getAllGenres();
        model.addAttribute("allGenres", allGenres);
        News lastNews = newsService.getLastNews();
        model.addAttribute("lastNews", lastNews);
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

    @GetMapping("/gamesByPlatform/{platformId}")
    public String gamesByPlatformPage(@PathVariable(name = "platformId") Long platformId,
                                      Model model) {
        List<Game> allGamesByPlatform = gameService.getGamesByPlatform(platformId);
        if (allGamesByPlatform != null) {
            model.addAttribute("selectedPlatformId", platformId);
            model.addAttribute("allGamesByPlatform", allGamesByPlatform);
            List<GamePlatform> allPlatforms = gamePlatformService.getAllPlatforms();
            model.addAttribute("allPlatforms", allPlatforms);
            List<Genre> allGenres = genreService.getAllGenres();
            model.addAttribute("allGenres", allGenres);
            News lastNewsByPlatform = newsService.getLastNewsByPlatform(platformId);
            model.addAttribute("lastNewsByPlatform", lastNewsByPlatform);
            return "gamesByPlatform";
        }
        return "redirect:/allGames";
    }

    @GetMapping("/gamesByGenre/{genreId}")
    public String gamesByGenrePage(@PathVariable(name = "genreId") Long genreId,
                                   Model model) {
        List<Game> allGamesByGenre = gameService.getGamesByGenre(genreId);
        if (allGamesByGenre != null) {
            model.addAttribute("selectedGenreId", genreId);
            model.addAttribute("allGamesByGenre", allGamesByGenre);
            List<GamePlatform> allPlatforms = gamePlatformService.getAllPlatforms();
            model.addAttribute("allPlatforms", allPlatforms);
            List<Genre> allGenres = genreService.getAllGenres();
            model.addAttribute("allGenres", allGenres);
            return "gamesByGenre";
        }
        return "redirect:/allGames";
    }

    @PostMapping("/feedback")
    public String feedback(Feedback feedback) {
        feedbackService.addFeedback(feedback);
        return "redirect:/";
    }

}
