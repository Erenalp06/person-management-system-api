package com.teksen.personmanagementsystem.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Role;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private static final String ENDPOINT = "/api/v*/person/**";

    @Bean
    public UserDetailsManager userDetailsManager(DataSource dataSource){
        JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);
        jdbcUserDetailsManager.setUsersByUsernameQuery("select username, password, enabled from users where username = ?");
        jdbcUserDetailsManager.setAuthoritiesByUsernameQuery("select username, role from authorities where username = ?");

        return jdbcUserDetailsManager;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.authorizeHttpRequests(configurer ->
                configurer
                        .requestMatchers(HttpMethod.GET, "/api/v*/person").hasAnyRole(ROLE.ADMIN.name(), ROLE.MODERATOR.name(), ROLE.USER.name())
                        .requestMatchers(HttpMethod.GET, ENDPOINT).hasAnyRole(ROLE.ADMIN.name(), ROLE.MODERATOR.name())
                        .requestMatchers(HttpMethod.PUT, ENDPOINT).hasAnyRole(ROLE.ADMIN.name(), ROLE.MODERATOR.name())
                        .requestMatchers(HttpMethod.POST, ENDPOINT).hasAnyRole(ROLE.ADMIN.name())
                        .requestMatchers(HttpMethod.DELETE, ENDPOINT).hasAnyRole(ROLE.ADMIN.name())
                        .requestMatchers(HttpMethod.GET, "/swagger-ui/**", "/swagger-ui.html", "/v3/api-docs/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/v*/user/**").hasAnyRole(ROLE.ADMIN.name())
                        .requestMatchers(HttpMethod.PUT, "/api/v*/user/**").hasAnyRole(ROLE.ADMIN.name())
                        .requestMatchers(HttpMethod.POST, "/api/v*/user/**").hasAnyRole(ROLE.ADMIN.name())
                        .requestMatchers(HttpMethod.DELETE, "/api/v*/user/**").hasAnyRole(ROLE.ADMIN.name())
                        .requestMatchers(HttpMethod.GET, "/api/v*/authority/**").hasAnyRole(ROLE.ADMIN.name())
                        .requestMatchers(HttpMethod.PUT, "/api/v*/authority/**").hasAnyRole(ROLE.ADMIN.name())
                        .requestMatchers(HttpMethod.DELETE, "/api/v*/authority/**").hasAnyRole(ROLE.ADMIN.name())
                        .requestMatchers(HttpMethod.POST, "/api/v*/authority/**").hasAnyRole(ROLE.ADMIN.name())
                        .requestMatchers(HttpMethod.GET, "/actuator/**").hasAnyRole(ROLE.ADMIN.name())


        );

        http.httpBasic(Customizer.withDefaults());

        http.csrf(AbstractHttpConfigurer::disable);

        return http.build();
    }
}
