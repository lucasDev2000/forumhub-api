package br.com.forumhub.forumhub.infra.security;

import br.com.forumhub.forumhub.domain.usuario.UsuarioRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    private final TokenService tokenService;
    private final UsuarioRepository usuarioRepository;

    public SecurityConfig(TokenService tokenService, UsuarioRepository usuarioRepository) {
        this.tokenService = tokenService;
        this.usuarioRepository = usuarioRepository;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        return http

                .csrf(csrf -> csrf.disable())

                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )

                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/login").permitAll()
                        .anyRequest().authenticated()
                )

                .addFilterBefore(
                        new SecurityFilter(tokenService, usuarioRepository),
                        UsernamePasswordAuthenticationFilter.class
                )

                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration configuration
    ) throws Exception {

        return configuration.getAuthenticationManager();
    }
}