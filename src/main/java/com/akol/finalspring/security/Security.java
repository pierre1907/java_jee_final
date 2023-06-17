package com.akol.finalspring.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class Security {

    //Gestion des authentifications mémoire
    @Bean
    public UserDetailsService userDetailsService(PasswordEncoder encoder){
        UserDetails admin1 = User.withUsername("admin1")
                .password(encoder.encode("1234"))
                .roles("ADMIN")
                .build();
        UserDetails user1 = User.withUsername("user1")
                .password(encoder.encode("1234"))
                .roles("USER")
                .build();
        return new InMemoryUserDetailsManager(admin1, user1);
    }

    //Gestion des autorisations
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests()
                .requestMatchers("/categorie/liste").hasAnyRole("ADMIN", "USER")
                .requestMatchers("/categorie/gestion").hasAnyRole("ADMIN","USER")
                .requestMatchers("/categorie/add").hasRole("ADMIN")
                .requestMatchers("/categorie/save").hasRole("ADMIN")
                .requestMatchers("/categorie/supprimer/**").hasRole("ADMIN")
                .requestMatchers("/categorie/modifier/**").hasRole("ADMIN")
                .requestMatchers("/categorie/update").hasRole("ADMIN")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .defaultSuccessUrl("/", true)
                .and()
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .and()
                .exceptionHandling()
                .accessDeniedPage("/error");
        return http.build();
    }

    //Gestion de l'encodage et du cryptage
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}