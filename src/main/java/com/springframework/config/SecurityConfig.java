package com.springframework.config;

import com.springframework.security.PasswordEncoderFactories;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests(authorize -> {
            authorize
                    .antMatchers("/h2-console/**").permitAll() //do not use in production!
                    .antMatchers("/css/**", "/js/**").permitAll()
                    .antMatchers("/user/", "/user/login", "/user/logout/").permitAll();
        })
        .authorizeRequests()
        .anyRequest().authenticated()
        .and()
        .formLogin(loginConfigurer -> {
            loginConfigurer
                    .loginProcessingUrl("/login")
                    .loginPage("/user/login").permitAll()
                    .successForwardUrl("/user/")
                    .defaultSuccessUrl("/user/")
                    .failureUrl("/user/login/?error");
        })
            .logout(httpSecurityLogoutConfigurer -> {
                httpSecurityLogoutConfigurer
                        .logoutRequestMatcher(new AntPathRequestMatcher("/logout", "GET"))
                        .logoutSuccessUrl("/user/")
                        .permitAll();
            })
        .httpBasic()
        .and()
        .csrf().disable();

        //h2-console config
        http.headers().frameOptions().sameOrigin();

    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
}
