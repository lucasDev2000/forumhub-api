package br.com.forumhub.forumhub.controller;

import br.com.forumhub.forumhub.domain.usuario.Usuario;
import br.com.forumhub.forumhub.domain.usuario.dto.DadosLogin;
import br.com.forumhub.forumhub.infra.security.TokenService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
public class AuthController {

    private final AuthenticationManager manager;
    private final TokenService tokenService;

    public AuthController(AuthenticationManager manager, TokenService tokenService) {
        this.manager = manager;
        this.tokenService = tokenService;
    }

    @PostMapping
    public ResponseEntity login(@RequestBody DadosLogin dados) {

        var authenticationToken =
                new UsernamePasswordAuthenticationToken(
                        dados.login(),
                        dados.senha()
                );

        var authentication = manager.authenticate(authenticationToken);

        var usuario = (Usuario) authentication.getPrincipal();

        var token = tokenService.gerarToken(usuario);

        return ResponseEntity.ok(token);
    }
}