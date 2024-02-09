package com.service.hotel.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import static net.sf.jsqlparser.util.validation.metadata.NamedObject.user;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig {
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    private static String USERNAME = "test";
    private static String PASSWORD = "test";
    private static String ROLE = "ADMIN";
    private static String ROLE2 = "USER";
    private static String USERNAMEUSER = "user";
    private static String PASSWORDUSER = "user";


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.csrf(AbstractHttpConfigurer::disable)
                .cors(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests((requests)->requests
                        .requestMatchers("/hotel/list**","/hotel/get**").hasRole("USER")
                        .requestMatchers("/room/list**","/room/get**").hasRole("USER")
                        .requestMatchers("/hotel/**").hasRole("ADMIN")
                        .requestMatchers("/user/**").hasRole("ADMIN")
                        .requestMatchers("/room/**").hasRole("ADMIN")
                        .anyRequest().permitAll())
                .httpBasic(Customizer.withDefaults())
                .build();

    }

    @Bean
    public UserDetailsService userDetailsService(){
        UserDetails userDetails = User.builder()
                .username(USERNAME)
                .password(bCryptPasswordEncoder.encode(PASSWORD))
                .roles(ROLE)
                .build();
        return new InMemoryUserDetailsManager(userDetails);
   }
}
