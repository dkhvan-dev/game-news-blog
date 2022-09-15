package springbootcamp.mainfinalproject.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import springbootcamp.mainfinalproject.service.FileUploadService;
import springbootcamp.mainfinalproject.service.impl.FileUploadServiceImpl;
import springbootcamp.mainfinalproject.service.impl.UserServiceImpl;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, proxyTargetClass = true)
public class SecurityConfig {

    @Bean
    public UserDetailsService userDetailsService() {
        return new UserServiceImpl();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.userDetailsService(userDetailsService()).passwordEncoder(passwordEncoder());

        http.authorizeRequests().antMatchers("/css/**", "/js/**").permitAll();

        http.formLogin()
                .loginProcessingUrl("/signIn")
                .usernameParameter("user_email")
                .passwordParameter("user_password")
                .defaultSuccessUrl("/profile")
                .failureUrl("/signIn?error")
                .loginPage("/signIn").permitAll();

        http.logout()
                .logoutUrl("/signOut")
                .logoutSuccessUrl("/signIn");

        http.csrf().disable();
        return http.build();
    }


}
