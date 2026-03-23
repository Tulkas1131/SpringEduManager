package com.bootcamp.SpringEduManager.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Configuración de seguridad de Spring Security.
 * Define qué rutas requieren autenticación y cómo loguinarse.
 */
@Configuration
@EnableWebSecurity
@EnableMethodSecurity  // Permite @PreAuthorize en métodos
public class SecurityConfig {

    // Configura qué rutas son públicas y cuáles requieren autenticación
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http, JwtTokenProvider tokenProvider) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)  // CSRF deshabilitado (es de desarrollo)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))  // Sin sesiones, usar tokens
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/", "/login", "/dashboard", "/css/**", "/js/**", "/*.html").permitAll()  // Páginas públicas
                        .requestMatchers("/h2-console/**").permitAll()  // Permitir consola H2
                        .requestMatchers("/api/auth/login", "/api/auth/verify").permitAll()  // Login sin autenticación
                        .requestMatchers("/estudiantes", "/estudiantes/**", "/cursos", "/cursos/**").permitAll()  // Rutas MVC permitidas (validación de token en JS)
                        .requestMatchers("/api/**").authenticated()  // APIs requieren token JWT
                        .anyRequest().authenticated()  // Todo lo demás requiere autenticación
                )
                // Configuración para H2 console
                .headers(headers -> headers.frameOptions(frameOptions -> frameOptions.disable()))
                // Agrega el filtro JWT antes de UsernamePasswordAuthenticationFilter
                .addFilterBefore(new JwtAuthenticationFilter(tokenProvider), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    // Define usuarios en memoria para desarrollo
    @Bean
    public UserDetailsService userDetailsService() {
        // Usuario normal
        UserDetails user = User.builder()
                .username("user")
                .password(passwordEncoder().encode("user"))
                .roles("USER")
                .build();

        // Usuario administrador
        UserDetails admin = User.builder()
                .username("admin")
                .password(passwordEncoder().encode("admin"))
                .roles("USER", "ADMIN")
                .build();

        return new InMemoryUserDetailsManager(user, admin);
    }

    // Encriptador de contraseñas BCrypt
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // Bean para usar AuthenticationManager en los controladores
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }
}
