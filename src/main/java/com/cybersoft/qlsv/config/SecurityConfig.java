package com.cybersoft.qlsv.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

import com.cybersoft.qlsv.security.CustomAuthenProvider;

import jakarta.servlet.DispatcherType;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean /* muốn gọi hàm chứng thực custom đã tạo chứ k phải xài mặc định của AuthenticationManager */
    public AuthenticationManager authenticate(HttpSecurity http, CustomAuthenProvider customAuthenProvider) throws Exception{

        return http.getSharedObject(AuthenticationManagerBuilder.class)
                .authenticationProvider(customAuthenProvider)
                .build();
    }
    
    @Bean
    public SecurityFilterChain filterChain (HttpSecurity http) throws Exception{
        return http
        .csrf(AbstractHttpConfigurer::disable)
        .authorizeHttpRequests(auth -> auth
            .dispatcherTypeMatchers(DispatcherType.FORWARD, DispatcherType.ERROR).permitAll()
            .requestMatchers("/css/**","/js/**","/images/**","/bootstrap/**","/plugins/**","/fonts/**","/icons/**","/less/**").permitAll()
            .requestMatchers("/**").permitAll()
            // .requestMatchers("/admin/**").hasAuthority("ADMIN")
            .anyRequest().authenticated()
        // "user-table/user-add","/task/task-add","/groupwork/groupwork-add","/role-table/role-add"
        )
       .formLogin(form -> {
            form.loginPage("/login");
            form.loginProcessingUrl("/login");
            form.usernameParameter("username")  ; // trùng với name trong form
            form.passwordParameter("password");
            form.defaultSuccessUrl("/index", true);
            form.failureUrl("/login?error");
            form.permitAll();
       })
       
         .logout(logout -> logout
             .logoutUrl("/logout")
             .logoutSuccessUrl("/login"))
        .build();
    }
}
