package com.ift.cloud.zuul.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.session.FindByIndexNameSessionRepository;
import org.springframework.session.Session;
import org.springframework.session.security.SpringSessionBackedSessionRegistry;

/**
 * SpringSecurity相关配置
 *
 * @author liufei
 * @date 2020/6/21
 */
@Configuration
public class SecurityConfig<S extends Session> extends WebSecurityConfigurerAdapter {

    /**
     * 认证失败处理器
     */
    private final AuthenticationFailureHandler authenticationFailureHandler;

    /**
     * 无权限访问处理器
     */
    private final AccessDeniedHandler accessDeniedHandler;

    /**
     * 未登录端点
     */
    private final AuthenticationEntryPoint authenticationEntryPoint;

    /**
     * 登录成功处理器
     */
    private final AuthenticationSuccessHandler authenticationSuccessHandler;

    /**
     * 注销成功处理器
     */
    private final LogoutSuccessHandler logoutSuccessHandler;

    private final FindByIndexNameSessionRepository<S> sessionRepository;

    public SecurityConfig(AuthenticationFailureHandler authenticationFailureHandler,
                          AccessDeniedHandler accessDeniedHandler,
                          AuthenticationEntryPoint authenticationEntryPoint,
                          AuthenticationSuccessHandler authenticationSuccessHandler,
                          LogoutSuccessHandler logoutSuccessHandler, FindByIndexNameSessionRepository<S> sessionRepository) {
        this.authenticationFailureHandler = authenticationFailureHandler;
        this.accessDeniedHandler = accessDeniedHandler;
        this.authenticationEntryPoint = authenticationEntryPoint;
        this.authenticationSuccessHandler = authenticationSuccessHandler;
        this.logoutSuccessHandler = logoutSuccessHandler;
        this.sessionRepository = sessionRepository;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().mvcMatchers("/js/**").mvcMatchers("/css/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers(HttpMethod.GET, "/consumer/admin/**").hasRole("ADMIN")
                .antMatchers("/consumer/user/**").hasAnyRole("USER")
                .anyRequest().authenticated()
                .and()
                .exceptionHandling()
                .accessDeniedHandler(accessDeniedHandler)
                .authenticationEntryPoint(authenticationEntryPoint)
                .and()
                .formLogin()
                .loginPage("/login.html")
                .loginProcessingUrl("/login")
                .failureHandler(authenticationFailureHandler)
                .successHandler(authenticationSuccessHandler)
                .permitAll()
                .and()
                .logout()
                .logoutUrl("/api/logout")
                .logoutSuccessHandler(logoutSuccessHandler)
                .permitAll()
                .and()
                .csrf().disable()
                .sessionManagement()
                .maximumSessions(2)
                .sessionRegistry(sessionRegistry());
    }

    @Bean
    public SpringSessionBackedSessionRegistry<S> sessionRegistry() {
        return new SpringSessionBackedSessionRegistry<>(this.sessionRepository);
    }

}
