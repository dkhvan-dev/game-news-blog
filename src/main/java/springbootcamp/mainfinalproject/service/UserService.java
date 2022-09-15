package springbootcamp.mainfinalproject.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.multipart.MultipartFile;
import springbootcamp.mainfinalproject.model.User;

import java.text.ParseException;

public interface UserService extends UserDetailsService {
    User getUserById(Long userId);
    String swapAvatar(MultipartFile userImageToken, User user);
    String editUser(User user, String oldPassword, String newPassword, String reNewPassword);
    User signUp(User user, String userBirthdate) throws ParseException;
    User getCurrentUser();
    boolean uploadUserImage(MultipartFile multipartFile, User user);
    void updateUserData(String firstName, String surname, String password);
}
