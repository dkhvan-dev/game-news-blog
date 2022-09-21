package springbootcamp.mainfinalproject.service.impl;

import lombok.RequiredArgsConstructor;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import springbootcamp.mainfinalproject.model.Role;
import springbootcamp.mainfinalproject.model.User;
import springbootcamp.mainfinalproject.repository.RoleRepository;
import springbootcamp.mainfinalproject.repository.UserRepository;
import springbootcamp.mainfinalproject.service.FileUploadService;
import springbootcamp.mainfinalproject.service.NewsService;
import springbootcamp.mainfinalproject.service.RoleService;
import springbootcamp.mainfinalproject.service.UserService;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleService roleService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Value("${targetURL}")
    private String targetURL;

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(Long userId) {
        User user = userRepository.findById(userId).orElse(null);
        if (user != null) {
            return user;
        }
        return null;
    }

    @Override
    public User getUserByEmail(String userEmail) {
        User user = userRepository.findAllByUserEmail(userEmail);
        if (user != null) {
            return user;
        }
        return null;
    }

    @Override
    public String swapAvatar(MultipartFile userImageToken, User user) {
        if (uploadUserImage(userImageToken, user)) {
            userRepository.save(user);
            return "successSwapAvatar";
        }
        return "errorSwapAvatar";
    }

    @Override
    public String editUser(User user, String birthdate, String oldPassword, String newPassword, String reNewPassword) {
        if (newPassword.equals(reNewPassword)) {
            if (getCurrentUser().getUserId().equals(user.getUserId())) {
                if (passwordEncoder.matches(oldPassword, getCurrentUser().getUserPassword())) {
                    DateTimeFormatter pattern = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                    LocalDate userBirthDate = LocalDate.parse(birthdate, pattern);
                    user.setUserBirthdate(userBirthDate);
                    user.setUserPassword(passwordEncoder.encode(newPassword));
                    user.setUserAvatar(getCurrentUser().getUserAvatar());
                    user.setRoles(getCurrentUser().getRoles());
                    userRepository.save(user);
                    return "success";
                } else {
                    return "oldPasswordNotSameCurrentPassword";
                }
            }
        } else {
            return "newPasswordsNotSame";
        }
        return null;
    }

    @Override
    public User signUp(User user, String userBirthdate) throws ParseException {
        User checkUser = userRepository.findAllByUserEmail(user.getUserEmail());
        Role defaultRole = roleService.getRoleByName("ROLE_USER");
        List<Role> roles = new ArrayList<>();
        roles.add(defaultRole);
        if (checkUser == null) {
            DateTimeFormatter pattern = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate birthdate = LocalDate.parse(userBirthdate, pattern);
            user.setUserBirthdate(birthdate);
            user.setUserPassword(passwordEncoder.encode(user.getPassword()));
            user.setRoles(roles);
            return userRepository.save(user);
        }
        return null;
    }

    @Override
    public void deleteUser(String email) {
        User user = userRepository.findAllByUserEmail(email);
        if (user != null) {
            userRepository.delete(user);
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findAllByUserEmail(username);
        if (user != null) {
            return user;
        }
        throw new UsernameNotFoundException("USER NOT FOUND!");
    }

    public User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (! (authentication instanceof AnonymousAuthenticationToken)) {
            User user = (User) authentication.getPrincipal();
            return user;
        }
        return null;
    }

    public boolean uploadUserImage(MultipartFile multipartFile, User user) {
        User currentUser = getCurrentUser();
        if (user.equals(currentUser)) {
            String userImageToken = DigestUtils.sha1Hex(currentUser.getUserId() + "_avatar_" + Math.random());
            String userImage = userImageToken + ".jpg";
            if (multipartFile.getContentType().equals("image/jpeg") || multipartFile.getContentType().equals("image/png")) {
                try {
                    Path path = Paths.get(targetURL + "/avatars/" + userImage);
                    byte[] bytes = multipartFile.getBytes();
                    Files.write(path, bytes);
                    user.setUserAvatar(userImageToken);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return true;
    }

    public void updateUserData(String firstName, String surname, String password) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (! (authentication instanceof AnonymousAuthenticationToken)) {
            User user = (User) authentication.getPrincipal();
            user.setUserFirstName(firstName);
            user.setUserSurname(surname);
            user.setUserPassword(password);
        }
    }

    @Override
    public boolean updateUserAdmin(User user, List<Role> roles) {
        Role moderator = roleService.getRoleByName("ROLE_MODERATOR");
        Role admin = roleService.getRoleByName("ROLE_ADMIN");
        Role banRole = roleService.getRoleByName("ROLE_BAN");
        boolean currentAdmin = false;
        boolean currentModer = false;
        User currentUser = getCurrentUser();
        for (int i = 0; i < currentUser.getRoles().size(); i++) {
            if (currentUser.getRoles().get(i).getRoleName().equals("ROLE_ADMIN")) {
                currentAdmin = true;
                break;
            }
        }
        for (int i = 0; i < currentUser.getRoles().size(); i++) {
            if (currentUser.getRoles().get(i).getRoleName().equals("ROLE_MODERATOR")) {
                currentModer = true;
                break;
            }
        }
        if (user.getRoles().contains(admin) && !roles.contains(admin)) {
            return false;
        }
        if (user.getRoles().contains(admin) && currentAdmin) {
            return false;
        }
        if (user.getRoles().contains(admin) && currentModer) {
            return false;
        }
        if (!user.getRoles().contains(admin) && roles.contains(admin)) {
            return false;
        }
        if (user.getRoles().contains(admin) && roles.contains(banRole)) {
            return false;
        }
        if (!user.getRoles().contains(admin) && currentAdmin) {
            user.setRoles(roles);
            userRepository.save(user);
            return true;
        }
        return false;
    }
}
