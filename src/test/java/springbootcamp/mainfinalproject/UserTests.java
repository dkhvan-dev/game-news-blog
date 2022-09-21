package springbootcamp.mainfinalproject;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import springbootcamp.mainfinalproject.model.Role;
import springbootcamp.mainfinalproject.model.RoleColor;
import springbootcamp.mainfinalproject.model.User;
import springbootcamp.mainfinalproject.service.RoleColorService;
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

    @Autowired
    private RoleColorService roleColorService;

    @Test
    void testSignUpUser() throws ParseException {
        User user = new User();
        user.setUserEmail("test@mail.ru");
        user.setUserPassword(passwordEncoder.encode("test"));
        user.setUserFirstName("Test");
        user.setUserSurname("User");
        RoleColor roleColor = new RoleColor();
        roleColor.setRoleColor(("#000"));
        List<Role> roles = new ArrayList<>();
        Role role = new Role();
        role.setRoleName("ROLE_TEST");
        role.setRoleColor(roleColor);
        roles.add(role);
        user.setRoles(roles);
        User newUser = userService.signUp(user, "2000-03-20");
        Assertions.assertNotNull(user);
        Assertions.assertNotNull(newUser);
        Assertions.assertNotNull(roleColor);
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

//    @Test
//    void testEditUser() throws ParseException {
//        User user = new User();
//        user.setUserEmail("test@mail.ru");
//        user.setUserPassword(passwordEncoder.encode("test"));
//        user.setUserFirstName("Test");
//        user.setUserSurname("User");
//        RoleColor roleColor = new RoleColor();
//        roleColor.setRoleColor(("#000"));
//        List<Role> roles = new ArrayList<>();
//        Role role = new Role();
//        role.setRoleName("ROLE_TEST");
//        role.setRoleColor(roleColor);
//        roles.add(role);
//        user.setRoles(roles);
//        User newUser = userService.signUp(user, "2000-03-20");
//        String editedUserSuccess = userService.editUser(newUser, "2022-05-13", "test", "test2", "test2");
//        String editedUserOldPasswordWrong = userService.editUser(newUser, "2022-05-13", "asd", "test2", "test2");
//        String editedUserNewPasswordsNotSame = userService.editUser(newUser, "2022-05-13", "test", "test1", "test2");
//        String editedUserNull = userService.editUser(null, "2022-05-13", "test", "test2", "test2");
//        Assertions.assertNotNull(newUser);
//        Assertions.assertNotNull(roleColor);
//        Assertions.assertNotNull(role);
//        Assertions.assertNotNull(roles);
//        Assertions.assertNotNull(newUser.getUserId());
//        Assertions.assertNotNull(newUser.getUserEmail());
//        Assertions.assertNotNull(newUser.getUserPassword());
//        Assertions.assertNotNull(newUser.getUserFirstName());
//        Assertions.assertNotNull(newUser.getUserSurname());
//        Assertions.assertNotNull(newUser.getUserBirthdate());
//        Assertions.assertNotNull(newUser.getRoles());
//        Assertions.assertEquals(editedUserSuccess, "success");
//        Assertions.assertEquals(editedUserOldPasswordWrong, "oldPasswordNotSameCurrentPassword");
//        Assertions.assertEquals(editedUserNewPasswordsNotSame, "newPasswordsNotSame");
//        Assertions.assertNotNull(editedUserNull);
//        userService.deleteUser(newUser.getUserEmail());
//    }
}
