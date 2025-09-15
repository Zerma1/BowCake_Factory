package fr.cepn.testspringpo84.security;

import fr.cepn.testspringpo84.security.jwt.AuthTokenFilter;
import fr.cepn.testspringpo84.security.jwt.JwtUtils;
import fr.cepn.testspringpo84.security.services.UserDetailsServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class WebSecurityConfig {

    private final UserDetailsServiceImpl userDetailsServiceImpl;

    public WebSecurityConfig(UserDetailsServiceImpl userDetailsServiceImpl) {
        this.userDetailsServiceImpl = userDetailsServiceImpl;
    }

    @Bean
    public AuthTokenFilter authTokenFilter(JwtUtils jwtUtils) {
        return new AuthTokenFilter(jwtUtils, userDetailsServiceImpl);
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(userDetailsServiceImpl);
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        return daoAuthenticationProvider;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http, AuthTokenFilter authTokenFilter) throws Exception {
        http.cors(AbstractHttpConfigurer::disable).csrf(AbstractHttpConfigurer::disable);
        http.sessionManagement(s -> s.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        http.authenticationProvider(authenticationProvider());
        http.addFilterBefore(authTokenFilter, UsernamePasswordAuthenticationFilter.class);

        http.authorizeHttpRequests(
          auth -> auth.requestMatchers("/api/v1/auth/**").permitAll()
                  .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                  .anyRequest().authenticated()
        );

        return http.build();
    }

}
