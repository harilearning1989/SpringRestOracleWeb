package com.web.demo.security;

import com.web.demo.services.UserDetailsServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public UserDetailsService userDetailsService() {
        return new UserDetailsServiceImpl();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());

        return authProvider;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //auth.inMemoryAuthentication().withUser("Hari").password("hari").roles("ADMIN");
        //auth.inMemoryAuthentication().withUser("Bablu").password("bablu").roles("USER");
        //auth.inMemoryAuthentication().withUser("Mouni").password("mouni").roles("USER");
        //.password("{noop}pass")
        auth.authenticationProvider(authenticationProvider());
    }
    @Bean( name = "/accessDenied" )
    public AccessDeniedHandler customAccessDenied() {
        return new CustomAccessDeniedHandler();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/").hasAnyAuthority("USER", "CREATOR", "EDITOR", "ADMIN")
                .antMatchers("/new").hasAnyAuthority("ADMIN", "CREATOR")
                .antMatchers("/edit/**").hasAnyAuthority("ADMIN", "EDITOR")
                .antMatchers("/delete/**").hasAuthority("ADMIN")
                .anyRequest().authenticated()
                .and()
                .formLogin().permitAll()
                .loginPage("/login")
                .defaultSuccessUrl("/")
                .failureUrl("/login?error=true")
                .and()
                .rememberMe()
                .key("rem-me-key")
                .rememberMeParameter("remember") // it is name of checkbox at login page
                .rememberMeCookieName("rememberlogin") // it is name of the cookie
                .tokenValiditySeconds(100) // remember for number of seconds
                //.tokenValiditySeconds(60 * 60 * 24)
                //.alwaysRemember(true)
                //.useSecureCookie(true)
                .and()
                .logout().permitAll()
                .logoutUrl("/logout")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
                .and()
                //.exceptionHandling()
                //.accessDeniedPage("/accessDenied.jsp")
                //.accessDeniedHandler(customAccessDenied())
                //.accessDeniedPage("/403")
                .exceptionHandling().accessDeniedHandler(customAccessDenied())
                .and()
                .exceptionHandling().authenticationEntryPoint(new CustomHttp403ForbiddenEntryPoint())
                .and()
                .csrf()
                .disable();
    }
}