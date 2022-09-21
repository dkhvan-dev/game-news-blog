package springbootcamp.mainfinalproject.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.multipart.MultipartFile;
import springbootcamp.mainfinalproject.model.Role;
import springbootcamp.mainfinalproject.model.User;

import java.text.ParseException;
import java.util.List;

public interface UserService extends UserDetailsService {
    List<User> getAllUsers();
    User getUserById(Long userId);
    User getUserByEmail(String userEmail);
    String swapAvatar(MultipartFile userImageToken, User user);
    String editUser(User user, String birthdate, String oldPassword, String newPassword, String reNewPassword);
    User signUp(User user, String userBirthdate) throws ParseException;
    void deleteUser(String email);
    User getCurrentUser();
    boolean uploadUserImage(MultipartFile multipartFile, User user);
    void updateUserData(String firstName, String surname, String password);
    boolean updateUserAdmin(User user, List<Role> roles);
}
