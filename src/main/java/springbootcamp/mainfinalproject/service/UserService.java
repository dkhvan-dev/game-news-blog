package springbootcamp.mainfinalproject.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import springbootcamp.mainfinalproject.model.User;

import java.text.ParseException;

public interface UserService extends UserDetailsService {
    String editUser(User user, String oldPassword, String newPassword, String reNewPassword);
    User signUp(User user, String userBirthdate) throws ParseException;
    User getCurrentUser();
    void updateUserData(String firstName, String surname, String password);
}
