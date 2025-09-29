package ru.itmo.jpa.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import ru.itmo.jpa.config.filter.TokenAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration /*extends AbstractHttpConfigurer<SecurityConfiguration, HttpSecurity>*/ {

    private final JwtHelper jwtHelper;

    @Bean
    public SecurityFilterChain apiFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(HttpMethod.POST, "/jwt")
                        .permitAll()
                        .requestMatchers("/v3/**", "/swagger-ui/**", "/swagger-ui.html", "/api/events").permitAll()
                        .requestMatchers("/api/groups").authenticated()
                        .requestMatchers("/api/groups/**").hasAuthority("ROLE_GUEST")
//                        .requestMatchers("/api/groups/**").hasRole("GUEST")
                        .requestMatchers("/api/students").authenticated()
                        .requestMatchers("/api/students/**").hasRole("ADMIN")
                        .anyRequest().authenticated()
                )
//                .httpBasic(Customizer.withDefaults())
//                .formLogin(Customizer.withDefaults())
                .addFilterBefore(new TokenAuthenticationFilter(jwtHelper), BasicAuthenticationFilter.class)
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
        ;

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
//
//    @Bean
//    public DaoAuthenticationProvider authenticationProvider() {
//        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
//        authProvider.setUserDetailsService(userDetailsService);
//        authProvider.setPasswordEncoder(passwordEncoder());
//        return authProvider;
//    }

//    @Bean
//    public UserDetailsService userDetailsService() {
//        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
//        manager.createUser(User.withDefaultPasswordEncoder().username("user").password("password").roles("ADMIN").build());
//        return manager;
//    }
}

