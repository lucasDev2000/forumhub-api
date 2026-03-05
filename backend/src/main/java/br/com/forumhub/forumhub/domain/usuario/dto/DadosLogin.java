package br.com.forumhub.forumhub.domain.usuario.dto;

import jakarta.validation.constraints.NotBlank;

public record DadosLogin(

        @NotBlank
        String login,

        @NotBlank
        String senha

) {}