package br.com.forumhub.forumhub.infra.security;

import br.com.forumhub.forumhub.domain.usuario.UsuarioRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService implements UserDetailsService {

    private final UsuarioRepository repository;

    public AuthenticationService(UsuarioRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        return repository.findByLogin(username);
    }
}