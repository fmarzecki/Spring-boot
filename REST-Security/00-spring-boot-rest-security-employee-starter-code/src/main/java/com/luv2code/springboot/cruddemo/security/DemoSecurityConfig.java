package com.luv2code.springboot.cruddemo.security;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class DemoSecurityConfig {
  
    //add support for JDBC, so we have acces to roles, passwords, usernames
    @Bean
    public UserDetailsManager userDetailsManager(DataSource dataSource) {
        // tell spring security to use JDBC authentication with our data source
        return new JdbcUserDetailsManager(dataSource);
    }
    
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http.authorizeHttpRequests(configurer ->
            configurer
                    .requestMatchers(HttpMethod.GET, "/api/employees").hasRole("EMPLOYEE")
                    .requestMatchers(HttpMethod.GET, "/api/employees/**").hasRole("EMPLOYEE")
                    .requestMatchers(HttpMethod.POST, "/api/employees").hasRole("MANAGER")
                    .requestMatchers(HttpMethod.PUT, "/api/employees").hasRole("MANAGER")
                    .requestMatchers(HttpMethod.DELETE, "/api/employees/**").hasRole("ADMIN")
        );

        // use HTTP Basic Authentication
        http.httpBasic();

        // disable CSRF
        // not required for stateless REST APIs
        http.csrf().disable();

        return http.build();
    }

    // @Bean
    // public InMemoryUserDetailsManager userDetailsManager() {

    //     UserDetails john = User.builder()
    //             .username("john")
    //             .password("{noop}test123")
    //             .roles("")
    //             .build();

    //     UserDetails mary = User.builder()
    //             .username("mary")
    //             .password("{noop}test123")
    //             .roles("EMPLOYEE","MANAGER")
    //             .build();

    //     UserDetails susan = User.builder()
    //             .username("susan")
    //             .password("{noop}test123")
    //             .roles("EMPLOYEE", "MANAGER", "ADMIN")
    //             .build();

    // return new InMemoryUserDetailsManager(john, mary, susan);
    // }

}
