package com.smart.config.securityconfig;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    private CustomUserDetailsService customUserDetailsService;
    private CustomAuthenticationFaliureHandler authenticationFaliureHandler;
    
    @Autowired
    public SecurityConfig(CustomUserDetailsService customUserDetailsService) {
		super();
		this.customUserDetailsService = customUserDetailsService;
	}

	@Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf().disable()
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/login", "/register", "/h2-console/**","/resources/**","/static/css/**","/css/**","/static/jsp/**","/home").permitAll()
                .requestMatchers("/user/**").hasRole("USER")
                .anyRequest().authenticated()
                
            )
            .formLogin(form -> form
                .loginPage("/login")
                .defaultSuccessUrl("/user/index", true)
                .failureHandler(authenticationFaliureHandler)
                .permitAll()
            )
            .logout(logout -> logout.permitAll());

        // Enable H2 console access
        http.headers().frameOptions().disable();

        return http.build();
    }
	
}
