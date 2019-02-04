package com.codesample.demo.config;

import com.codesample.demo.filter.CustomCorsFilter;
import com.codesample.demo.mapper.UserMapper;
import com.codesample.demo.filter.AuthorizationFilter;
import com.codesample.demo.security.CustomAuthenticationEntryPoint;
import com.codesample.demo.service.SecurityService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.channel.ChannelProcessingFilter;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private UserMapper userMapper;
    private SecurityService securityService;
    private CustomCorsFilter customCorsFilter;

    public SecurityConfig(
                           SecurityService securityService,
                           CustomCorsFilter customCorsFilter,
                           UserMapper userMapper) {
        this.securityService = securityService;
        this.userMapper = userMapper;
        this.customCorsFilter = customCorsFilter;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.antMatcher("/**")
                .exceptionHandling().authenticationEntryPoint(new CustomAuthenticationEntryPoint())
                .and().cors()
                .and().csrf().disable()
                .authorizeRequests()
                .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                .antMatchers("/auth/login").permitAll()
                .anyRequest().authenticated()
                .and()
                .addFilterBefore(customCorsFilter, ChannelProcessingFilter.class)
                .addFilter(new AuthorizationFilter(authenticationManager(), securityService, userMapper));;
    }

    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return (obj) -> null;
    }
}
