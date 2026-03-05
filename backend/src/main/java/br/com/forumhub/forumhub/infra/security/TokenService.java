package br.com.forumhub.forumhub.infra.security;

import br.com.forumhub.forumhub.domain.usuario.Usuario;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TokenService {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private Long expiration;

    public String gerarToken(Usuario usuario) {

        Algorithm algoritmo = Algorithm.HMAC256(secret);

        return JWT.create()
                .withIssuer("forumhub")
                .withSubject(usuario.getLogin())
                .withExpiresAt(new Date(System.currentTimeMillis() + expiration))
                .sign(algoritmo);
    }

    public String validarToken(String token) {

        Algorithm algoritmo = Algorithm.HMAC256(secret);

        return JWT.require(algoritmo)
                .withIssuer("forumhub")
                .build()
                .verify(token)
                .getSubject();
    }
}