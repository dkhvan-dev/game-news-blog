package springbootcamp.mainfinalproject.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import springbootcamp.mainfinalproject.model.Role;
import springbootcamp.mainfinalproject.model.User;
import springbootcamp.mainfinalproject.repository.RoleRepository;
import springbootcamp.mainfinalproject.repository.UserRepository;
import springbootcamp.mainfinalproject.service.UserService;

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
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public String editUser(User user, String oldPassword, String newPassword, String reNewPassword) {
        if (newPassword.equals(reNewPassword)) {
            if (getCurrentUser().getUserId().equals(user.getUserId())) {
                if (passwordEncoder.matches(oldPassword, getCurrentUser().getUserPassword())) {
                    user.setUserPassword(passwordEncoder.encode(newPassword));
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
        Role defaultRole = roleRepository.findAllByRoleName("ROLE_USER");
        List<Role> roles = new ArrayList<>();
        roles.add(defaultRole);
        if (checkUser == null) {
            DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
            Date birthdate = format.parse(userBirthdate);
            user.setUserBirthdate(birthdate);
            user.setUserPassword(passwordEncoder.encode(user.getPassword()));
            user.setRoles(roles);
            return userRepository.save(user);
        }
        return null;
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

    public void updateUserData(String firstName, String surname, String password) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (! (authentication instanceof AnonymousAuthenticationToken)) {
            User user = (User) authentication.getPrincipal();
            user.setUserFirstName(firstName);
            user.setUserSurname(surname);
            user.setUserPassword(password);
        }
    }
}
