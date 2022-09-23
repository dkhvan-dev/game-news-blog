package springbootcamp.mainfinalproject;

import liquibase.pro.packaged.F;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import springbootcamp.mainfinalproject.model.Feedback;
import springbootcamp.mainfinalproject.model.FeedbackStatus;
import springbootcamp.mainfinalproject.model.Role;
import springbootcamp.mainfinalproject.model.User;
import springbootcamp.mainfinalproject.service.FeedbackService;
import springbootcamp.mainfinalproject.service.RoleService;
import springbootcamp.mainfinalproject.service.UserService;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class UserTests {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleService roleService;

    @Autowired
    private UserService userService;

    @Test
    void testSignUpUser() throws ParseException {
        User user = new User();
        user.setUserEmail("test@mail.ru");
        user.setUserPassword(passwordEncoder.encode("test"));
        user.setUserFirstName("Test");
        user.setUserSurname("User");
        List<Role> roles = new ArrayList<>();
        Role role = new Role();
        role.setRoleName("ROLE_TEST");
        roles.add(role);
        user.setRoles(roles);
        User newUser = userService.signUp(user, "2000-03-20");
        Assertions.assertNotNull(user);
        Assertions.assertNotNull(newUser);
        Assertions.assertNotNull(role);
        Assertions.assertNotNull(roles);
        Assertions.assertNotNull(newUser.getUserId());
        Assertions.assertNotNull(newUser.getUserEmail());
        Assertions.assertNotNull(newUser.getUserPassword());
        Assertions.assertNotNull(newUser.getUserFirstName());
        Assertions.assertNotNull(newUser.getUserSurname());
        Assertions.assertNotNull(newUser.getUserBirthdate());
        Assertions.assertNotNull(newUser.getRoles());
        userService.deleteUser(newUser.getUserEmail());
    }
}
