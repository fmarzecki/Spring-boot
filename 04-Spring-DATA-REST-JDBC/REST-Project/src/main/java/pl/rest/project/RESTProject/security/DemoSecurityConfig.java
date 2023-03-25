package pl.rest.project.RESTProject.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class DemoSecurityConfig {
  
    @Bean
    public InMemoryUserDetailsManager InMemoryUserDetailsManager() {
        
    }
}
