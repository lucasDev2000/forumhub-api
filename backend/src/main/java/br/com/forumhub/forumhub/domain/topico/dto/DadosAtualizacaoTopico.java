package br.com.forumhub.forumhub.domain.topico.dto;

import jakarta.validation.constraints.NotBlank;

public record DadosAtualizacaoTopico(
        @NotBlank(message = "Título é obrigatório")
        String titulo,

        @NotBlank(message = "Mensagem é obrigatória")
        String mensagem,

        @NotBlank(message = "Autor é obrigatório")
        String autor,

        @NotBlank(message = "Curso é obrigatório")
        String curso
) {}